package com.nyfaria.anotherqualityoreset.platform;

import com.nyfaria.anotherqualityoreset.AnotherQualityOreSet;
import com.nyfaria.anotherqualityoreset.platform.services.IPlatformHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public CreativeModeTab getCreativeModeTab() {
        return AnotherQualityOreSet.CREATIVE_MODE_TAB;
    }
}