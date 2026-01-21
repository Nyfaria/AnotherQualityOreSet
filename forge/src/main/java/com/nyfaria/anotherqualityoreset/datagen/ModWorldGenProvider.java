package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.TagInit;
import com.nyfaria.anotherqualityoreset.init.WorldGenInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModWorldGenProvider::biomeModifiers)
            .add(Registries.CONFIGURED_FEATURE, ModWorldGenProvider::configuredFeature)
            .add(Registries.PLACED_FEATURE, ModWorldGenProvider::placedFeatures);
    private static final ResourceKey<BiomeModifier> EASIUM = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "easium_ore"));
    private static final ResourceKey<BiomeModifier> MEDIUM = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "medium_ore"));
    private static final ResourceKey<BiomeModifier> HARDIUM = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Constants.MODID, "hardium_ore"));

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Constants.MODID));
    }

    public static void biomeModifiers(BootstapContext<BiomeModifier> context) {
        context.register(EASIUM,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(WorldGenInit.EASIUM_ORE_PLACED),
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(WorldGenInit.DEEPSLATE_EASIUM_ORE_PLACED)
                ), GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(MEDIUM,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_NETHER), HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(WorldGenInit.MEDIUM_ORE_PLACED)
                ), GenerationStep.Decoration.UNDERGROUND_ORES)
        );
        context.register(HARDIUM,
                new ForgeBiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_END), HolderSet.direct(
                        context.lookup(Registries.PLACED_FEATURE).getOrThrow(WorldGenInit.HARDIUM_ORE_PLACED)
                ), GenerationStep.Decoration.UNDERGROUND_ORES)
        );
    }

    public static void configuredFeature(BootstapContext<ConfiguredFeature<?, ?>> context) {
        context.register(WorldGenInit.EASIUM_ORE, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.EASIUM_ORE.ore().get().defaultBlockState())),
                                15,
                                0
                        )
                )
        );
        context.register(WorldGenInit.DEEPSLATE_EASIUM_ORE, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.DEEPSLATE_EASIUM_ORE.get().defaultBlockState())),
                                15,
                                0
                        )
                )
        );
        context.register(WorldGenInit.MEDIUM_ORE, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(new TagMatchTest(TagInit.NETHER_ORE_REPLACEABLES), BlockInit.MEDIUM_ORE.ore().get().defaultBlockState())),
                                15,
                                0
                        )
                )
        );
        context.register(WorldGenInit.HARDIUM_ORE, new ConfiguredFeature<>(
                        Feature.ORE,
                        new OreConfiguration(
                                List.of(OreConfiguration.target(new TagMatchTest(TagInit.END_ORE_REPLACEABLES), BlockInit.HARDIUM_ORE.ore().get().defaultBlockState())),
                                15,
                                0
                        )
                )
        );

    }

    public static void placedFeatures(BootstapContext<PlacedFeature> context) {
        context.register(WorldGenInit.EASIUM_ORE_PLACED, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(WorldGenInit.EASIUM_ORE).get(),
                        List.of(
                                CountPlacement.of(UniformInt.of(4,10)),
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(0),VerticalAnchor.absolute(256)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(WorldGenInit.DEEPSLATE_EASIUM_ORE_PLACED, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(WorldGenInit.DEEPSLATE_EASIUM_ORE).get(),
                        List.of(
                                CountPlacement.of(UniformInt.of(3,7)),
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(0)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(WorldGenInit.MEDIUM_ORE_PLACED, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(WorldGenInit.MEDIUM_ORE).get(),
                        List.of(
                                CountPlacement.of(UniformInt.of(2,5)),
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(0),VerticalAnchor.absolute(128)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
        context.register(WorldGenInit.HARDIUM_ORE_PLACED, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).get(WorldGenInit.HARDIUM_ORE).get(),
                        List.of(
                                CountPlacement.of(UniformInt.of(0,3)),
                                HeightRangePlacement.uniform(VerticalAnchor.absolute(0),VerticalAnchor.absolute(128)),
                                InSquarePlacement.spread(),
                                BiomeFilter.biome()
                        )
                )
        );
    }


    public static BiomeGenerationSettings.PlainBuilder baseSettings(BootstapContext<Biome> context) {
        return new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
                .addFeature(GenerationStep.Decoration.LAKES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND).get())
                .addFeature(GenerationStep.Decoration.LAKES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.LAKE_LAVA_SURFACE).get())
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.AMETHYST_GEODE).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIRT).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRAVEL).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRANITE_UPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GRANITE_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIORITE_UPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIORITE_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_ANDESITE_UPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_ANDESITE_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_TUFF).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COAL_UPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COAL_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_UPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_MIDDLE).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_IRON_SMALL).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GOLD).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_GOLD_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_REDSTONE).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_REDSTONE_LOWER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND_LARGE).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_DIAMOND_BURIED).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_LAPIS).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_LAPIS_BURIED).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(OrePlacements.ORE_COPPER).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.UNDERWATER_MAGMA).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_SAND).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_CLAY).get())
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.DISK_GRAVEL).get())
                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.SPRING_WATER).get())
                .addFeature(GenerationStep.Decoration.FLUID_SPRINGS, context.lookup(Registries.PLACED_FEATURE).get(MiscOverworldPlacements.SPRING_LAVA).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(CavePlacements.GLOW_LICHEN).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.FOREST_FLOWERS).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.FLOWER_DEFAULT).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_GRASS_FOREST).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.BROWN_MUSHROOM_NORMAL).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.RED_MUSHROOM_NORMAL).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_SUGAR_CANE).get())
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, context.lookup(Registries.PLACED_FEATURE).get(VegetationPlacements.PATCH_PUMPKIN).get());
    }
}
