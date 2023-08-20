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
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab(Constants.MODID + ".creative_mode_tab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockInit.EASIUM_ORE.getIngot().get());
        }
    };

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
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeServer, new ModRecipeProvider(generator));
        generator.addProvider(includeServer, new ModLootTableProvider(generator));
        generator.addProvider(includeServer, new ModSoundProvider(generator, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModBlockTags(generator, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModItemTags(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModLangProvider(generator));
    }

}