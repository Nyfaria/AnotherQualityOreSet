package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.ItemInit;
import com.nyfaria.anotherqualityoreset.item.HammerItem;
import com.nyfaria.anotherqualityoreset.item.PaxelItem;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public record OreCollection(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> rawOre, RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Block> block, RegistryObject<ArmorItem> helmet, RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings, RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword, RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe, RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel, RegistryObject<PaxelItem> paxel, RegistryObject<HammerItem> hammer) {

    public static OreCollection of(AQOArmoMaterials material, AQOToolTiers toolTier) {
        return new OreCollection(
                material.getName(),
                BlockInit.registerBlock(material.getName() + "_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(material.getMimicBlock()), material.getOreXp())),
                ItemInit.ITEMS.register("raw_" + material.getName(), () -> new Item(ItemInit.getItemProperties())),
                BlockInit.registerBlock("raw_" + material.getName() + "_block", () -> new Block(BlockBehaviour.Properties.copy(material.getMimicBlock()))),
                ItemInit.ITEMS.register(material.getName() + "_ingot", () -> new Item(ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_nugget", () -> new Item(ItemInit.getItemProperties())),
                BlockInit.registerBlock(material.getName() + "_block", () -> new Block(BlockBehaviour.Properties.copy(material.getMimicBlock()))),
                ItemInit.ITEMS.register(material.getName() + "_helmet", () -> new ArmorItem(material, ArmorItem.Type.HELMET, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_chestplate", () -> new ArmorItem(material, ArmorItem.Type.CHESTPLATE, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_leggings", () -> new ArmorItem(material, ArmorItem.Type.LEGGINGS, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_boots", () -> new ArmorItem(material, ArmorItem.Type.BOOTS, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_sword", () -> new SwordItem(toolTier, 3, -2.4f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_pickaxe", () -> new PickaxeItem(toolTier, 1, -2.8f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_axe", () -> new AxeItem(toolTier, 5, -3, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_hoe", () -> new HoeItem(toolTier, -3, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_shovel", () -> new ShovelItem(toolTier, 1.5f, -3.0f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_paxel", () -> new PaxelItem(5, -2.8f, toolTier, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_hammer", () -> new HammerItem(toolTier, 5, -2.8f, ItemInit.getItemProperties()))
        );
    }

    public static OreCollection of(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> raw_ore, RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Block> block, RegistryObject<ArmorItem> helmet, RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings, RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword, RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe, RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel, RegistryObject<PaxelItem> paxel,RegistryObject<HammerItem> hammer){
        return new OreCollection(name, ore, raw_ore, rawOreBlock, ingot, nugget, block, helmet, chestplate, leggings, boots, sword, pickaxe, axe, hoe, shovel, paxel, hammer);
    }

}
