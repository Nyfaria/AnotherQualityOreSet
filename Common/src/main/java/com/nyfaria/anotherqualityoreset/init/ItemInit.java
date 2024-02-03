package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.platform.Services;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import com.nyfaria.anotherqualityoreset.Constants;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemInit {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, Constants.MODID);

    public static Item.Properties getItemProperties() {
        return new Item.Properties().tab(Services.PLATFORM.getCreativeModeTab());
    }

    public static void loadClass() {
    }
}
