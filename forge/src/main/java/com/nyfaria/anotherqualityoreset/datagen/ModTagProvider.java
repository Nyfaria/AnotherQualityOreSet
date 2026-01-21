package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.TagInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModTagProvider {

    public static class ModItemTags extends TagsProvider<Item> {

        public ModItemTags(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

        }

        public void populateTag(TagKey<Item> tag, Supplier<Item>... items){
            for (Supplier<Item> item : items) {
                tag(tag).add(ForgeRegistries.ITEMS.getResourceKey(item.get()).get());
            }
        }
    }

    public static class ModBlockTags<T extends Block> extends TagsProvider<Block> {

        public ModBlockTags(PackOutput pGenerator, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registries.BLOCK, p_256513_, Constants.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
//            populateTag(TagInit.END_ORE_REPLACEABLES, () -> Blocks.END_STONE);
//            populateTag(TagInit.NETHER_ORE_REPLACEABLES, () -> Blocks.NETHERRACK);
            populateTag(BlockTags.MINEABLE_WITH_PICKAXE,
                    () -> BlockInit.EASIUM_ORE.ore().get(),
                    () -> BlockInit.EASIUM_ORE.block().get(),
                    () -> BlockInit.EASIUM_ORE.rawOreBlock().get(),
                    () -> BlockInit.MEDIUM_ORE.ore().get(),
                    () -> BlockInit.MEDIUM_ORE.block().get(),
                    () -> BlockInit.MEDIUM_ORE.rawOreBlock().get(),
                    () -> BlockInit.HARDIUM_ORE.ore().get(),
                    () -> BlockInit.HARDIUM_ORE.block().get(),
                    () -> BlockInit.HARDIUM_ORE.rawOreBlock().get()
            );

            tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).addTag(TagInit.NEEDS_TOOL_LEVEL_4);
            populateTag(TagInit.NEEDS_TOOL_LEVEL_4,
                    ()->BlockInit.EASIUM_ORE.block().get());
            populateTag(TagInit.NEEDS_TOOL_LEVEL_5,
                    ()->BlockInit.MEDIUM_ORE.block().get());
            populateTag(TagInit.NEEDS_TOOL_LEVEL_6,
                    ()->BlockInit.MEDIUM_ORE.block().get());
            populateTag(TagInit.NEEDS_TOOL_LEVEL_5,
                    ()->BlockInit.HARDIUM_ORE.block().get());

            tag(TagInit.MINEABLE_WITH_PAXEL).addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE);
        }

        public  <T extends Block>void populateTag(TagKey<Block> tag, Supplier<?>... items){
            for (Supplier<?> item : items) {
                tag(tag).add(ForgeRegistries.BLOCKS.getResourceKey((Block)item.get()).get());
            }
        }
    }
}
