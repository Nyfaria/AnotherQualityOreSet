package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class ClientUtils {
    public static ItemStack getTabIconItem() {
        if (Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getGameTime() % 120 < 40) {
                return new ItemStack(BlockInit.EASIUM_ORE.ore().get());
            } else if (Minecraft.getInstance().level.getGameTime() % 120 < 80) {
                return new ItemStack(BlockInit.MEDIUM_ORE.ore().get());
            } else {
                return new ItemStack(BlockInit.HARDIUM_ORE.ore().get());
            }
        }
        return new ItemStack(BlockInit.EASIUM_ORE.ore().get());
    }
}
