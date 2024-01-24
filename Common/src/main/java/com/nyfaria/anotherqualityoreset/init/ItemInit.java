package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.ClientUtils;
import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemInit {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(Constants.MODID, () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .icon(ClientUtils::getTabIconItem)
            .displayItems(
                    (itemDisplayParameters, output) -> {
                        OreCollection.ORE.forEach(ore -> output.accept(new ItemStack(ore.get())));
                        OreCollection.RAW_ORE.forEach(rawOre -> output.accept(new ItemStack(rawOre.get())));
                        OreCollection.RAW_ORE_BLOCK.forEach(rawOreBlock -> output.accept(new ItemStack(rawOreBlock.get())));
                        OreCollection.INGOT.forEach(ingot -> output.accept(new ItemStack(ingot.get())));
                        OreCollection.NUGGET.forEach(nugget -> output.accept(new ItemStack(nugget.get())));
                        OreCollection.BLOCK.forEach(block -> output.accept(new ItemStack(block.get())));
                        OreCollection.HELMET.forEach(helmet -> output.accept(new ItemStack(helmet.get())));
                        OreCollection.CHESTPLATE.forEach(chestplate -> output.accept(new ItemStack(chestplate.get())));
                        OreCollection.LEGGINGS.forEach(leggings -> output.accept(new ItemStack(leggings.get())));
                        OreCollection.BOOTS.forEach(boots -> output.accept(new ItemStack(boots.get())));
                        OreCollection.SWORD.forEach(sword -> output.accept(new ItemStack(sword.get())));
                        OreCollection.PICKAXE.forEach(pickaxe -> output.accept(new ItemStack(pickaxe.get())));
                        OreCollection.AXE.forEach(axe -> output.accept(new ItemStack(axe.get())));
                        OreCollection.HOE.forEach(hoe -> output.accept(new ItemStack(hoe.get())));
                        OreCollection.SHOVEL.forEach(shovel -> output.accept(new ItemStack(shovel.get())));
                        OreCollection.PAXEL.forEach(paxel -> output.accept(new ItemStack(paxel.get())));
                        OreCollection.HAMMER.forEach(hammer -> output.accept(new ItemStack(hammer.get())));
                        OreCollection.TREE_AXE.forEach(treeAxe -> output.accept(new ItemStack(treeAxe.get())));
                    }).title(Component.translatable("itemGroup." + Constants.MODID + ".creative_mode_tab"))
            .build());
    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void loadClass() {
    }
}
