package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WorldGenInit {
    public static final RegistrationProvider<ConfiguredFeature<?,?>> CONFIGURED_FEATURES = RegistrationProvider.get(Registries.CONFIGURED_FEATURE, Constants.MODID);
    public static final RegistrationProvider<PlacedFeature> FEATURES = RegistrationProvider.get(Registries.PLACED_FEATURE, Constants.MODID);
//    public static final RegistryObject<ConfiguredFeature<?,?>> EASIUM_ORE = CONFIGURED_FEATURES.register("easium_ore", () ->new ConfiguredFeature
//            (Feature.ORE, new OreConfiguration(
//                    OreFeatures.STONE_ORE_REPLACEABLES,
//                    BlockInit.EASIUM_ORE.getOre().get().defaultBlockState(),
//                    5)));
//    public static final RegistryObject<ConfiguredFeature<?,?>> MEDIUM_ORE = CONFIGURED_FEATURES.register("medium_ore", () ->new ConfiguredFeature
//            (Feature.ORE, new OreConfiguration(
//                    OreFeatures.STONE_ORE_REPLACEABLES,
//                    BlockInit.EASIUM_ORE.getOre().get().defaultBlockState(),
//                    1)));
//    public static final RegistryObject<ConfiguredFeature<?,?>> HARDIUM_ORE = CONFIGURED_FEATURES.register("hardium_ore", () ->new ConfiguredFeature
//            (Feature.ORE, new OreConfiguration(
//                    new TagMatchTest(TagInit.END_ORE_REPLACEABLES),
//                    BlockInit.EASIUM_ORE.getOre().get().defaultBlockState(),
//                    1)));

//    public static final RegistryObject<PlacedFeature> EASIUM_ORE_PLACED = FEATURES.register("easium_ore", () ->new PlacedFeature(
//            EASIUM_ORE.asHolder(),
//            Arrays.asList(
//                    CountPlacement.of(UniformInt.of(20,40)),
//                    InSquarePlacement.spread(),
//                    BiomeFilter.biome(),
//                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(256)))));
//    public static final RegistryObject<PlacedFeature> MEDIUME_ORE_PLACED = FEATURES.register("medium_ore", () ->new PlacedFeature(
//            MEDIUM_ORE.asHolder(),
//            Arrays.asList(
//                    CountPlacement.of(UniformInt.of(10,25)),
//                    InSquarePlacement.spread(),
//                    BiomeFilter.biome(),
//                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));
//    public static final RegistryObject<PlacedFeature> HARDIUM_ORE_PLACED = FEATURES.register("hardium_ore", () ->new PlacedFeature(
//            HARDIUM_ORE.asHolder(),
//            Arrays.asList(
//                    CountPlacement.of(10),
//                    InSquarePlacement.spread(),
//                    BiomeFilter.biome(),
//                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(128)))));

    public static void loadClass() {
    }
}
