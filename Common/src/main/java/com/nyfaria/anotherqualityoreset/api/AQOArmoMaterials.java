package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public enum AQOArmoMaterials implements ArmorMaterial {
    EASIUM("easium", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(BlockInit.EASIUM_ORE.getIngot().get()), Blocks.IRON_ORE, UniformInt.of(0,0)),
    MEDIUM("medium", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> Ingredient.of(BlockInit.MEDIUM_ORE.getIngot().get()), Blocks.DIAMOND_ORE, UniformInt.of(2,5)),
    HARDIUM("hardium", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.of(BlockInit.HARDIUM_ORE.getIngot().get()), Blocks.ANCIENT_DEBRIS, UniformInt.of(6,9));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final BlockBehaviour mimicBlock;
    private final IntProvider oreXp;

    private AQOArmoMaterials(String name, int durabilityMultiplier, int[] slotProtection, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier repairIngredient, BlockBehaviour mimicBlock, IntProvider oreXp) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtection;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue(repairIngredient);
        this.mimicBlock = mimicBlock;
        this.oreXp = oreXp;
    }

    public int getDurabilityForSlot(EquipmentSlot $$0) {
        return HEALTH_PER_SLOT[$$0.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot $$0) {
        return this.slotProtections[$$0.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
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
