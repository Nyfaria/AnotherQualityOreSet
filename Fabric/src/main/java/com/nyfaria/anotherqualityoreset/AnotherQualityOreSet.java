package com.nyfaria.anotherqualityoreset;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.WorldGenInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.impl.client.item.group.FabricCreativeGuiComponents;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AnotherQualityOreSet implements ModInitializer {
    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricItemGroupBuilder.build(new ResourceLocation(Constants.MODID,"creative_mode_tab"), () -> new ItemStack(BlockInit.EASIUM_ORE.getIngot().get()));
    @Override
    public void onInitialize() {
        CommonClass.init();
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(Constants.MODID, "easium_ore")));
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(Constants.MODID, "medium_ore")));
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, new ResourceLocation(Constants.MODID, "hardium_ore")));
    }
}
