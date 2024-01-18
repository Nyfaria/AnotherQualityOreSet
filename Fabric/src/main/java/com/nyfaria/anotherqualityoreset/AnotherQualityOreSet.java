package com.nyfaria.anotherqualityoreset;

import com.nyfaria.anotherqualityoreset.api.HammerEvents;
import com.nyfaria.anotherqualityoreset.init.WorldGenInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.levelgen.GenerationStep;

public class AnotherQualityOreSet implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                WorldGenInit.EASIUM_ORE_PLACED);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                WorldGenInit.DEEPSLATE_EASIUM_ORE_PLACED);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                WorldGenInit.MEDIUM_ORE_PLACED);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                WorldGenInit.HARDIUM_ORE_PLACED);
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {

            return true;
        });
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (world.isClientSide) return InteractionResult.sidedSuccess(true);
            HammerEvents.startBreak(direction);
            return InteractionResult.sidedSuccess(true);
        });
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if(world.isClientSide)return;
            HammerEvents.breakBlock((ServerPlayer)player, pos, player.getMainHandItem());
        });
    }
}
