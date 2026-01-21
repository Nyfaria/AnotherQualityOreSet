package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WorldGenInit {
    public static final RegistrationProvider<PlacedFeature> FEATURES = RegistrationProvider.get(Registries.PLACED_FEATURE, Constants.MODID);
    public static final ResourceKey<ConfiguredFeature<?,?>> EASIUM_ORE = FeatureUtils.createKey(Constants.MODID + ":easium_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> DEEPSLATE_EASIUM_ORE = FeatureUtils.createKey(Constants.MODID + ":deepslate_easium_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> MEDIUM_ORE = FeatureUtils.createKey(Constants.MODID + ":medium_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> HARDIUM_ORE = FeatureUtils.createKey(Constants.MODID + ":hardium_ore");

    public static final ResourceKey<PlacedFeature> EASIUM_ORE_PLACED = PlacementUtils.createKey(Constants.MODID + ":easium_ore");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_EASIUM_ORE_PLACED = PlacementUtils.createKey(Constants.MODID + ":deepslate_easium_ore");
    public static final ResourceKey<PlacedFeature> MEDIUM_ORE_PLACED = PlacementUtils.createKey(Constants.MODID + ":medium_ore");
    public static final ResourceKey<PlacedFeature> HARDIUM_ORE_PLACED = PlacementUtils.createKey(Constants.MODID + ":hardium_ore");

    public static void loadClass() {
    }
}
