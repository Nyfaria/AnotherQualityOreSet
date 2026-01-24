package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.*;
import net.minecraft.client.*;
import net.minecraft.world.item.*;

public class ClientUtils {
    public static ItemStack getTabIconItem() {
        if (Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getGameTime() % 120 < 40) {
                return new ItemStack(BlockInit.EASIUM_ORE.sword().get());
            } else if (Minecraft.getInstance().level.getGameTime() % 120 < 80) {
                return new ItemStack(BlockInit.MEDIUM_ORE.sword().get());
            } else {
                return new ItemStack(BlockInit.HARDIUM_ORE.sword().get());
            }
        }
        return new ItemStack(ItemInit.TELOS_SWORD.get());
    }


}
