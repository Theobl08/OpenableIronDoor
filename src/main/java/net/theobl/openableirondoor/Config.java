package net.theobl.openableirondoor;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = OpenableIronDoor.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.EnumValue<ModBehaviour> MOD_BEHAVIOR = BUILDER.comment("Define the mod behavior:\n" +
                    "TAG means that door under the \"metal_doors_openable_by_hand\" tag and trapdoor under the \"metal_trapdoors_openable_by_hand\" tag can be open by hand\n" +
                    "ALL means that all metal doors (including modded ones) can be opened by hand")
                    .defineEnum("modBehaviour", ModBehaviour.TAG);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static Enum<ModBehaviour> modBehaviour;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        modBehaviour = MOD_BEHAVIOR.get();
    }

    public enum ModBehaviour {
        TAG,
        ALL
    }
}
