package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.TagInit;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModTagProvider {

    public static class ModItemTags extends TagsProvider<Item> {

        public ModItemTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registry.ITEM, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags() {

        }

        public void populateTag(TagKey<Item> tag, Supplier<Item>... items) {
            for (Supplier<Item> item : items) {
                tag(tag).add(item.get());
            }
        }
    }

    public static class ModBlockTags<T extends Block> extends TagsProvider<Block> {

        public ModBlockTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registry.BLOCK, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags() {
//            populateTag(TagInit.END_ORE_REPLACEABLES, () -> Blocks.END_STONE);
//            populateTag(TagInit.NETHER_ORE_REPLACEABLES, () -> Blocks.NETHERRACK);
            populateTag(BlockTags.MINEABLE_WITH_PICKAXE,
                    () -> BlockInit.EASIUM_ORE.getOre().get(),
                    () -> BlockInit.EASIUM_ORE.getBlock().get(),
                    () -> BlockInit.EASIUM_ORE.getRawOreBlock().get(),
                    () -> BlockInit.MEDIUM_ORE.getOre().get(),
                    () -> BlockInit.MEDIUM_ORE.getBlock().get(),
                    () -> BlockInit.MEDIUM_ORE.getRawOreBlock().get(),
                    () -> BlockInit.HARDIUM_ORE.getOre().get(),
                    () -> BlockInit.HARDIUM_ORE.getBlock().get(),
                    () -> BlockInit.HARDIUM_ORE.getRawOreBlock().get()
            );
            populateTag(BlockTags.NEEDS_IRON_TOOL,
                    ()->BlockInit.EASIUM_ORE.getBlock().get());
            populateTag(BlockTags.NEEDS_DIAMOND_TOOL,
                    ()->BlockInit.MEDIUM_ORE.getBlock().get());
            populateTag(TagInit.NEEDS_TOOL_LEVEL_4,
                    ()->BlockInit.HARDIUM_ORE.getBlock().get());

        }

        public void populateTag(TagKey<Block> tag, Supplier<Block>... items) {
            for (Supplier<Block> item : items) {
                tag(tag).add(item.get());
            }
        }
    }
}
