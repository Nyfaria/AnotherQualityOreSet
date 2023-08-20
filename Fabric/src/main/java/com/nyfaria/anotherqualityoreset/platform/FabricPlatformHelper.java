package com.nyfaria.anotherqualityoreset.platform;

import com.nyfaria.anotherqualityoreset.AnotherQualityOreSet;
import com.nyfaria.anotherqualityoreset.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public CreativeModeTab getCreativeModeTab() {
        return AnotherQualityOreSet.CREATIVE_MODE_TAB;
    }
}
