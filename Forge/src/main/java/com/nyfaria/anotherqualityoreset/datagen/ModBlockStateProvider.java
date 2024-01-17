package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Stream.of(
        //
        //         )
        //         .map(Supplier::get)
        //         .forEach(this::simpleCubeBottomTopBlockState);
        //
        Stream.of(
                        BlockInit.EASIUM_ORE.getBlock(),
                        BlockInit.EASIUM_ORE.getOre(),
                        BlockInit.EASIUM_ORE.getRawOreBlock(),
                        BlockInit.MEDIUM_ORE.getBlock(),
                        BlockInit.MEDIUM_ORE.getOre(),
                        BlockInit.MEDIUM_ORE.getRawOreBlock(),
                        BlockInit.HARDIUM_ORE.getBlock(),
                        BlockInit.HARDIUM_ORE.getOre(),
                        BlockInit.HARDIUM_ORE.getRawOreBlock()
                ).map(Supplier::get)
                .forEach(this::simpleBlock);

    }

    protected void simpleCubeBottomTopBlockState(Block block) {
        simpleBlock(block, blockCubeTopModel(block));
    }

    protected BlockModelBuilder blockCubeTopModel(Block block) {
        String name = getName(block);
        return models().cubeBottomTop(name, modLoc("block/" + name + "_side"), modLoc("block/" + name + "_bottom"), modLoc("block/" + name + "_top"));
    }

    protected String getName(Block item) {
        return ForgeRegistries.BLOCKS.getKey(item).getPath();
    }
}
