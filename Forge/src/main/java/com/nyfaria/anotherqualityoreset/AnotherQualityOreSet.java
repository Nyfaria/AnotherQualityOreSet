package com.nyfaria.anotherqualityoreset;

import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import com.nyfaria.anotherqualityoreset.datagen.ModBlockStateProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModItemModelProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModLangProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModLootTableProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModRecipeProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModSoundProvider;
import com.nyfaria.anotherqualityoreset.datagen.ModTagProvider;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod(Constants.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnotherQualityOreSet {

    public AnotherQualityOreSet() {

        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        TierSortingRegistry.registerTier(AQOToolTiers.EASIUM, new ResourceLocation(Constants.MODID, "easium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON
                ),
                List.of(
                )
        );
        TierSortingRegistry.registerTier(AQOToolTiers.MEDIUM, new ResourceLocation(Constants.MODID, "medium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON,
                        Tiers.DIAMOND
                        ),
                List.of(
                )
        );
        TierSortingRegistry.registerTier(AQOToolTiers.HARDIUM, new ResourceLocation(Constants.MODID, "hardium"),
                List.of(
                        Tiers.STONE,
                        Tiers.IRON,
                        Tiers.DIAMOND,
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
        generator.addProvider(includeServer, new ModTagProvider.ModBlockTags<>(packOutput,event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModItemTags(packOutput,event.getLookupProvider(), existingFileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(includeClient, new ModLangProvider(packOutput));
    }

}