package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagInit {
    public static final TagKey<Block> END_ORE_REPLACEABLES = blockTag("end_ore_replaceables");
    public static final TagKey<Block> NETHER_ORE_REPLACEABLES = blockTag("nether_ore_replaceables");
    public static final TagKey<Block> NEEDS_TOOL_LEVEL_4 = TagKey.create(Registries.BLOCK,new ResourceLocation("fabric", "needs_tool_level_4"));

    public static TagKey<Block> blockTag(String path) {
        return TagKey.create(Registries.BLOCK,new ResourceLocation(Constants.MODID, path));
    }

    public static void loadClass() {
    }
}
