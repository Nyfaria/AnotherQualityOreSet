package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.ItemInit;
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

public class OreCollection {
    private RegistryObject<DropExperienceBlock> ore;
    private RegistryObject<Item> rawOre;
    private RegistryObject<Block> rawOreBlock;
    private RegistryObject<Item> ingot;
    private RegistryObject<Item> nugget;
    private RegistryObject<Block> block;
    private RegistryObject<ArmorItem> helmet;
    private RegistryObject<ArmorItem> chestplate;
    private RegistryObject<ArmorItem> leggings;
    private RegistryObject<ArmorItem> boots;
    private RegistryObject<SwordItem> sword;
    private RegistryObject<PickaxeItem> pickaxe;
    private RegistryObject<AxeItem> axe;
    private RegistryObject<HoeItem> hoe;
    private RegistryObject<ShovelItem> shovel;
    private String name;

    private OreCollection(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> rawOre, RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Block> block, RegistryObject<ArmorItem> helmet, RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings, RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword, RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe, RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel) {
        this.ore = ore;
        this.rawOre = rawOre;
        this.rawOreBlock = rawOreBlock;
        this.ingot = ingot;
        this.nugget = nugget;
        this.block = block;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
        this.pickaxe = pickaxe;
        this.axe = axe;
        this.hoe = hoe;
        this.shovel = shovel;
        this.name = name;
    }

    public static OreCollection of(AQOArmoMaterials material, Tier toolTier) {
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
                ItemInit.ITEMS.register(material.getName() + "_sword", () -> new SwordItem(toolTier, 0, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_pickaxe", () -> new PickaxeItem(toolTier, 0, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_axe", () -> new AxeItem(toolTier, 0, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_hoe", () -> new HoeItem(toolTier, 0, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_shovel", () -> new ShovelItem(toolTier, 0, 0, ItemInit.getItemProperties()))
        );
    }

    public static OreCollection of(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> raw_ore, RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Block> block, RegistryObject<ArmorItem> helmet, RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings, RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword, RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe, RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel) {
        return new OreCollection(name, ore, raw_ore, rawOreBlock, ingot, nugget, block, helmet, chestplate, leggings, boots, sword, pickaxe, axe, hoe, shovel);
    }

    public RegistryObject<DropExperienceBlock> getOre() {
        return ore;
    }

    public RegistryObject<Item> getRawOre() {
        return rawOre;
    }

    public RegistryObject<Block> getRawOreBlock() {
        return rawOreBlock;
    }

    public RegistryObject<Item> getIngot() {
        return ingot;
    }

    public RegistryObject<Item> getNugget() {
        return nugget;
    }

    public RegistryObject<Block> getBlock() {
        return block;
    }

    public RegistryObject<ArmorItem> getHelmet() {
        return helmet;
    }

    public RegistryObject<ArmorItem> getChestplate() {
        return chestplate;
    }

    public RegistryObject<ArmorItem> getLeggings() {
        return leggings;
    }

    public RegistryObject<ArmorItem> getBoots() {
        return boots;
    }

    public RegistryObject<SwordItem> getSword() {
        return sword;
    }

    public RegistryObject<PickaxeItem> getPickaxe() {
        return pickaxe;
    }

    public RegistryObject<AxeItem> getAxe() {
        return axe;
    }

    public RegistryObject<HoeItem> getHoe() {
        return hoe;
    }

    public RegistryObject<ShovelItem> getShovel() {
        return shovel;
    }

    public String getName() {
        return name;
    }
}
