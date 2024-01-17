package com.nyfaria.anotherqualityoreset;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AnotherQualityOreSet implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "easium_ore")));
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "medium_ore")));
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Constants.MODID, "hardium_ore")));
    }
}
