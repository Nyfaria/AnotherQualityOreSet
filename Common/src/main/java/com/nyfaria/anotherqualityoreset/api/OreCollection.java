package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.ItemInit;
import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import com.nyfaria.anotherqualityoreset.item.HammerItem;
import com.nyfaria.anotherqualityoreset.item.PaxelItem;
import com.nyfaria.anotherqualityoreset.item.TreeAxeItem;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record OreCollection(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> rawOre,
                            RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget,
                            RegistryObject<Block> block, RegistryObject<ArmorItem> helmet,
                            RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings,
                            RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword,
                            RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe,
                            RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel,
                            RegistryObject<PaxelItem> paxel, RegistryObject<HammerItem> hammer,
                            RegistryObject<TreeAxeItem> treeAxe, RegistryObject<Item> rod) {

    public static List<RegistryObject<DropExperienceBlock>> ORE = new ArrayList<>();
    public static List<RegistryObject<Item>> RAW_ORE = new ArrayList<>();
    public static List<RegistryObject<Block>> RAW_ORE_BLOCK = new ArrayList<>();
    public static List<RegistryObject<Item>> INGOT = new ArrayList<>();
    public static List<RegistryObject<Item>> NUGGET = new ArrayList<>();
    public static List<RegistryObject<Block>> BLOCK = new ArrayList<>();
    public static List<RegistryObject<ArmorItem>> HELMET = new ArrayList<>();
    public static List<RegistryObject<ArmorItem>> CHESTPLATE = new ArrayList<>();
    public static List<RegistryObject<ArmorItem>> LEGGINGS = new ArrayList<>();
    public static List<RegistryObject<ArmorItem>> BOOTS = new ArrayList<>();
    public static List<RegistryObject<SwordItem>> SWORD = new ArrayList<>();
    public static List<RegistryObject<PickaxeItem>> PICKAXE = new ArrayList<>();
    public static List<RegistryObject<AxeItem>> AXE = new ArrayList<>();
    public static List<RegistryObject<HoeItem>> HOE = new ArrayList<>();
    public static List<RegistryObject<ShovelItem>> SHOVEL = new ArrayList<>();
    public static List<RegistryObject<PaxelItem>> PAXEL = new ArrayList<>();
    public static List<RegistryObject<HammerItem>> HAMMER = new ArrayList<>();
    public static List<RegistryObject<TreeAxeItem>> TREE_AXE = new ArrayList<>();
    public static List<RegistryObject<Item>> ROD = new ArrayList<>();

    public static OreCollection of(AQOArmoMaterials material, AQOToolTiers toolTier, Supplier<MobEffectInstance>... effects) {
        return OreCollection.of(
                material.getName(),
                BlockInit.registerBlock(material.getName() + "_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(material.getMimicBlock()), material.getOreXp())),
                ItemInit.ITEMS.register("raw_" + material.getName(), () -> new Item(ItemInit.getItemProperties())),
                BlockInit.registerBlock("raw_" + material.getName() + "_block", () -> new Block(BlockBehaviour.Properties.copy(material.getMimicBlock()))),
                ItemInit.ITEMS.register(material.getName() + "_ingot", () -> new Item(ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_nugget", () -> new Item(ItemInit.getItemProperties())),
                BlockInit.registerBlock(material.getName() + "_block", () -> new Block(BlockBehaviour.Properties.copy(material.getMimicBlock()))),
                ItemInit.ITEMS.register(material.getName() + "_helmet", () -> new AQOArmorItem(material, ArmorItem.Type.HELMET, ItemInit.getItemProperties(),effects)),
                ItemInit.ITEMS.register(material.getName() + "_chestplate", () -> new AQOArmorItem(material, ArmorItem.Type.CHESTPLATE, ItemInit.getItemProperties(),effects)),
                ItemInit.ITEMS.register(material.getName() + "_leggings", () -> new AQOArmorItem(material, ArmorItem.Type.LEGGINGS, ItemInit.getItemProperties(),effects)),
                ItemInit.ITEMS.register(material.getName() + "_boots", () -> new AQOArmorItem(material, ArmorItem.Type.BOOTS, ItemInit.getItemProperties(),effects)),
                ItemInit.ITEMS.register(material.getName() + "_sword", () -> new SwordItem(toolTier, 3, -2.4f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_pickaxe", () -> new PickaxeItem(toolTier, 1, -2.8f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_axe", () -> new AxeItem(toolTier, 5, -3, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_hoe", () -> new HoeItem(toolTier, -3, 0, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_shovel", () -> new ShovelItem(toolTier, 1.5f, -3.0f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_paxel", () -> new PaxelItem(5, -2.8f, toolTier, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_hammer", () -> new HammerItem(toolTier, 5, -2.8f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_tree_axe", () -> new TreeAxeItem(toolTier, 5, -3f, ItemInit.getItemProperties())),
                ItemInit.ITEMS.register(material.getName() + "_rod", () -> new Item(ItemInit.getItemProperties()))
        );
    }

    public static OreCollection of(String name, RegistryObject<DropExperienceBlock> ore, RegistryObject<Item> raw_ore, RegistryObject<Block> rawOreBlock, RegistryObject<Item> ingot, RegistryObject<Item> nugget, RegistryObject<Block> block, RegistryObject<ArmorItem> helmet, RegistryObject<ArmorItem> chestplate, RegistryObject<ArmorItem> leggings, RegistryObject<ArmorItem> boots, RegistryObject<SwordItem> sword, RegistryObject<PickaxeItem> pickaxe, RegistryObject<AxeItem> axe, RegistryObject<HoeItem> hoe, RegistryObject<ShovelItem> shovel, RegistryObject<PaxelItem> paxel, RegistryObject<HammerItem> hammer, RegistryObject<TreeAxeItem> treeAxe, RegistryObject<Item> rod) {
        ORE.add(ore);
        RAW_ORE.add(raw_ore);
        RAW_ORE_BLOCK.add(rawOreBlock);
        INGOT.add(ingot);
        NUGGET.add(nugget);
        BLOCK.add(block);
        HELMET.add(helmet);
        CHESTPLATE.add(chestplate);
        LEGGINGS.add(leggings);
        BOOTS.add(boots);
        SWORD.add(sword);
        PICKAXE.add(pickaxe);
        AXE.add(axe);
        HOE.add(hoe);
        SHOVEL.add(shovel);
        PAXEL.add(paxel);
        HAMMER.add(hammer);
        TREE_AXE.add(treeAxe);
        ROD.add(rod);
        return new OreCollection(name, ore, raw_ore, rawOreBlock, ingot, nugget, block, helmet, chestplate, leggings, boots, sword, pickaxe, axe, hoe, shovel, paxel, hammer, treeAxe, rod);
    }

}
