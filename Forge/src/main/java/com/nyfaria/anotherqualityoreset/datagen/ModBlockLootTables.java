package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;

import java.util.Set;
import java.util.stream.Stream;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    protected void generate() {
        this.getBlockStream().filter(this::shouldDropSelf).forEach(this::dropSelf);
        this.add(BlockInit.EASIUM_ORE.getOre().get(), (block) -> createOreDrop(block, BlockInit.EASIUM_ORE.getRawOre().get()));
        this.add(BlockInit.MEDIUM_ORE.getOre().get(), (block) -> createOreDrop(block, BlockInit.MEDIUM_ORE.getRawOre().get()));
        this.add(BlockInit.HARDIUM_ORE.getOre().get(), (block) -> createOreDrop(block, BlockInit.HARDIUM_ORE.getRawOre().get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.getBlockStream().toList();
    }

    protected Stream<Block> getBlockStream() {
        return BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get);
    }

    protected boolean shouldDropSelf(Block block) {
        return shouldGenerateLoot(block);
    }

    protected boolean shouldGenerateLoot(Block block) {
        return block.asItem() != Items.AIR && !(block instanceof DropExperienceBlock);
    }

}
