package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import com.nyfaria.anotherqualityoreset.api.ClientUtils;
import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

import java.util.stream.Stream;

public class ItemInit {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MODID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MODID);
    public static final RegistryObject<Item> TELOS_SCRAP = ITEMS.register("telos_scrap", () -> new Item(getItemProperties()));
    public static final RegistryObject<Item> TELOS_HELMET = ITEMS.register("telos_helmet", () -> new AQOArmorItem(AQOArmoMaterials.TELOS, ArmorItem.Type.HELMET, getItemProperties(), () -> new MobEffectInstance(MobEffects.WATER_BREATHING)));
    public static final RegistryObject<Item> TELOS_CHESTPLATE = ITEMS.register("telos_chestplate", () -> new AQOArmorItem(AQOArmoMaterials.TELOS, ArmorItem.Type.CHESTPLATE, getItemProperties(), () -> new MobEffectInstance(MobEffects.WATER_BREATHING)));
    public static final RegistryObject<Item> TELOS_LEGGINGS = ITEMS.register("telos_leggings", () -> new AQOArmorItem(AQOArmoMaterials.TELOS, ArmorItem.Type.LEGGINGS, getItemProperties(), () -> new MobEffectInstance(MobEffects.WATER_BREATHING)));
    public static final RegistryObject<Item> TELOS_BOOTS = ITEMS.register("telos_boots", () -> new AQOArmorItem(AQOArmoMaterials.TELOS, ArmorItem.Type.BOOTS, getItemProperties(), () -> new MobEffectInstance(MobEffects.WATER_BREATHING)));
    public static final RegistryObject<Item> TELOS_SWORD = ITEMS.register("telos_sword", () -> new SwordItem(AQOToolTiers.TELOS, 3, -2.4f, getItemProperties()));
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
                        OreCollection.ROD.forEach(rod -> output.accept(new ItemStack(rod.get())));
                        output.acceptAll(
                                Stream.of(
                                        TELOS_HELMET,
                                        TELOS_CHESTPLATE,
                                        TELOS_LEGGINGS,
                                        TELOS_BOOTS,
                                        TELOS_SWORD,
                                        TELOS_SCRAP,
                                        BlockInit.DEEPSLATE_EASIUM_ORE
                                ).map(RegistryObject::get).map(ItemStack::new).toList()
                        );
                    }
            ).title(Component.translatable("itemGroup." + Constants.MODID + ".creative_mode_tab")
            )
            .build());

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void loadClass() {
    }
}
