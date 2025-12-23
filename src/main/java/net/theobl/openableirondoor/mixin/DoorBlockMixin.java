package net.theobl.openableirondoor.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.theobl.openableirondoor.Config;
import net.theobl.openableirondoor.tags.OIDTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.DoorBlock.OPEN;

@Mixin(DoorBlock.class)
public abstract class DoorBlockMixin {
    @Shadow
    public abstract boolean isOpen(BlockState pState);

    @Shadow
    protected abstract void playSound(@Nullable Entity source, Level level, BlockPos pos, boolean isOpening);

    @Inject(method = "useWithoutItem", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void openableMetalDoors(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if(openableIronDoor$canBeOpened(state)) {
            state = state.cycle(OPEN);
            level.setBlock(pos, state, Block.UPDATE_CLIENTS | Block.UPDATE_IMMEDIATE);
            this.playSound(player, level, pos, state.getValue(OPEN));
            level.gameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Unique
    private boolean openableIronDoor$canBeOpened(BlockState state) {
        return (Config.modBehaviour.ordinal() == Config.ModBehaviour.ALL.ordinal()) ||
                (Config.modBehaviour.ordinal() == Config.ModBehaviour.TAG.ordinal() && state.is(OIDTags.Blocks.METAL_DOORS_OPENABLE_BY_HAND));
    }
}
