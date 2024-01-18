package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum AQOArmoMaterials implements ArmorMaterial {
    EASIUM("easium", 15, Util.make(new EnumMap<>(ArmorItem.Type.class),
            (map) -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(BlockInit.EASIUM_ORE.ingot().get()), Blocks.DIAMOND_ORE, UniformInt.of(0, 0)),
    MEDIUM("medium", 33, Util.make(new EnumMap<>(ArmorItem.Type.class),
            (map) -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(BlockInit.MEDIUM_ORE.ingot().get()), Blocks.ANCIENT_DEBRIS, UniformInt.of(2, 5)),
    HARDIUM("hardium", 37, Util.make(new EnumMap<>(ArmorItem.Type.class),
            (map) -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
            }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(BlockInit.HARDIUM_ORE.ingot().get()), Blocks.NETHERITE_BLOCK, UniformInt.of(6, 9));

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final BlockBehaviour mimicBlock;
    private final IntProvider oreXp;
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap(ArmorItem.Type.class), ($$0) -> {
        $$0.put(ArmorItem.Type.BOOTS, 13);
        $$0.put(ArmorItem.Type.LEGGINGS, 15);
        $$0.put(ArmorItem.Type.CHESTPLATE, 16);
        $$0.put(ArmorItem.Type.HELMET, 11);
    });

    AQOArmoMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier repairIngredient, BlockBehaviour mimicBlock, IntProvider oreXp) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue(repairIngredient);
        this.mimicBlock = mimicBlock;
        this.oreXp = oreXp;
    }


    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient) this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public BlockBehaviour getMimicBlock() {
        return mimicBlock;
    }

    public IntProvider getOreXp() {
        return oreXp;
    }
}
