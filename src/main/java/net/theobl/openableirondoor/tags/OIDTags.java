package net.theobl.openableirondoor.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.theobl.openableirondoor.OpenableIronDoor;

public class OIDTags {
    public static class Blocks {
        public static TagKey<Block> METAL_DOORS_OPENABLE_BY_HAND = createTag("metal_doors_openable_by_hand");
        public static TagKey<Block> METAL_TRAPDOORS_OPENABLE_BY_HAND = createTag("metal_trapdoors_openable_by_hand");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(OpenableIronDoor.MODID, name));
        }
    }
}
