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
                        BlockInit.EASIUM_ORE.block(),
                        BlockInit.EASIUM_ORE.ore(),
                        BlockInit.EASIUM_ORE.rawOreBlock(),
                        BlockInit.MEDIUM_ORE.block(),
                        BlockInit.MEDIUM_ORE.ore(),
                        BlockInit.MEDIUM_ORE.rawOreBlock(),
                        BlockInit.HARDIUM_ORE.block(),
                        BlockInit.HARDIUM_ORE.ore(),
                        BlockInit.HARDIUM_ORE.rawOreBlock()
                ).map(Supplier::get)
                .forEach(this::simpleBlock);
        simpleBlock(BlockInit.DEEPSLATE_EASIUM_ORE.get());
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
