package net.theobl.openableirondoor.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
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

@Mixin(TrapDoorBlock.class)
public abstract class TrapDoorBlockMixin {
    @Shadow
    protected abstract void toggle(BlockState state, Level level, BlockPos pos, @Nullable Player player);

    @Inject(method = "useWithoutItem", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void openableMetalTrapDoors(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if(openableIronDoor$canBeOpened(state)) {
            this.toggle(state, level, pos, player);
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }

    @Unique
    private boolean openableIronDoor$canBeOpened(BlockState state) {
        return (Config.modBehaviour.ordinal() == Config.ModBehaviour.ALL.ordinal()) ||
                (Config.modBehaviour.ordinal() == Config.ModBehaviour.TAG.ordinal() && state.is(OIDTags.Blocks.METAL_TRAPDOORS_OPENABLE_BY_HAND));
    }
}
