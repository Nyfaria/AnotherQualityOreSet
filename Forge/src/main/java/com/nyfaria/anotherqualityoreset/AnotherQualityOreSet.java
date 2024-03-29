package com.nyfaria.anotherqualityoreset;

import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import com.nyfaria.anotherqualityoreset.config.CommonConfig;
import com.nyfaria.anotherqualityoreset.datagen.ModBlockStateProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModItemModelProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModLangProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModLootTableProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModRecipeProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModSoundProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModTagProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModWorldGenProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;
import java.util.Set;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnotherQualityOreSet {
    public static Set<ToolAction> PAXEL_ACTIONS = Set.of(
            ToolActions.AXE_DIG,
            ToolActions.AXE_STRIP,
            ToolActions.AXE_SCRAPE,
            ToolActions.AXE_WAX_OFF,
            ToolActions.PICKAXE_DIG
    );

    public AnotherQualityOreSet() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG_SPEC);
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        TierSortingRegistry.registerTier(AQOToolTiers.EASIUM, new ResourceLocation(Constants.MODID, "easium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON,
                        Tiers.DIAMOND,
                        Tiers.NETHERITE
                ),
                List.of(
                )
        );
        TierSortingRegistry.registerTier(AQOToolTiers.MEDIUM, new ResourceLocation(Constants.MODID, "medium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON,
                        Tiers.DIAMOND,
                        Tiers.NETHERITE,
                        AQOToolTiers.EASIUM
                ),
                List.of(
                )
        );
        TierSortingRegistry.registerTier(AQOToolTiers.HARDIUM, new ResourceLocation(Constants.MODID, "hardium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON,
                        Tiers.DIAMOND,
                        Tiers.NETHERITE,
                        AQOToolTiers.EASIUM,
                        AQOToolTiers.MEDIUM
                ),
                List.of(
                )
        );

    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        PackOutput packOutput = event.getGenerator().getPackOutput();
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();
        generator.addProvider(includeServer, new ModRecipeProvider(packOutput));
        generator.addProvider(includeServer, new ModLootTableProvider(packOutput));
        generator.addProvider(includeServer, new ModSoundProvider(packOutput, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModBlockTags<>(packOutput, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModItemTags(packOutput, event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeServer, new ModWorldGenProvider(packOutput, event.getLookupProvider()));
        generator.addProvider(includeClient, new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModLangProvider(packOutput));
    }

}