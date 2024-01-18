package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.init.BlockInit;
import com.nyfaria.anotherqualityoreset.init.TagInit;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum AQOToolTiers implements Tier {
    EASIUM(3, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(BlockInit.EASIUM_ORE.ingot().get()), TagInit.NEEDS_TOOL_LEVEL_4,3),
    MEDIUM(4, 1561, 8.0F, 3.0F, 10, () -> Ingredient.of(BlockInit.MEDIUM_ORE.ingot().get()), TagInit.NEEDS_TOOL_LEVEL_5,5),
    HARDIUM(5, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(BlockInit.HARDIUM_ORE.ingot().get()), TagInit.NEEDS_TOOL_LEVEL_6,7);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final TagKey<Block> tag;
    private final int breakRadius;

    AQOToolTiers(int $$0, int $$1, float $$2, float $$3, int $$4, Supplier $$5, TagKey<Block>tag, int breakRadius) {
        this.level = $$0;
        this.uses = $$1;
        this.speed = $$2;
        this.damage = $$3;
        this.enchantmentValue = $$4;
        this.repairIngredient = new LazyLoadedValue($$5);
        this.tag = tag;
        this.breakRadius = breakRadius;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
    @Nullable
    public TagKey<Block> getTag() { return tag; }

    public int getBreakRadius() {
        return breakRadius;
    }
}
