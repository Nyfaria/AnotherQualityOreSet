package com.nyfaria.anotherqualityoreset.config;

import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final CommonConfig INSTANCE;

    static {
        Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        CONFIG_SPEC = pair.getRight();
        INSTANCE = pair.getLeft();
    }

    public final ForgeConfigSpec.IntValue easiumDefenseHelmet;
    public final ForgeConfigSpec.IntValue easiumDefenseChestplate;
    public final ForgeConfigSpec.IntValue easiumDefenseLeggings;
    public final ForgeConfigSpec.IntValue easiumDefenseBoots;
    public final ForgeConfigSpec.IntValue mediumDefenseHelmet;
    public final ForgeConfigSpec.IntValue mediumDefenseChestplate;
    public final ForgeConfigSpec.IntValue mediumDefenseLeggings;
    public final ForgeConfigSpec.IntValue mediumDefenseBoots;
    public final ForgeConfigSpec.IntValue hardiumDefenseHelmet;
    public final ForgeConfigSpec.IntValue hardiumDefenseChestplate;
    public final ForgeConfigSpec.IntValue hardiumDefenseLeggings;
    public final ForgeConfigSpec.IntValue hardiumDefenseBoots;
    public final ForgeConfigSpec.IntValue telosDefenseHelmet;
    public final ForgeConfigSpec.IntValue telosDefenseChestplate;
    public final ForgeConfigSpec.IntValue telosDefenseLeggings;
    public final ForgeConfigSpec.IntValue telosDefenseBoots;
    public final ForgeConfigSpec.DoubleValue easiumToughness;
    public final ForgeConfigSpec.DoubleValue mediumToughness;
    public final ForgeConfigSpec.DoubleValue hardiumToughness;
    public final ForgeConfigSpec.DoubleValue telosToughness;
    public final ForgeConfigSpec.IntValue easiumDurabilityHelmet;
    public final ForgeConfigSpec.IntValue easiumDurabilityChestplate;
    public final ForgeConfigSpec.IntValue easiumDurabilityLeggings;
    public final ForgeConfigSpec.IntValue easiumDurabilityBoots;
    public final ForgeConfigSpec.IntValue mediumDurabilityHelmet;
    public final ForgeConfigSpec.IntValue mediumDurabilityChestplate;
    public final ForgeConfigSpec.IntValue mediumDurabilityLeggings;
    public final ForgeConfigSpec.IntValue mediumDurabilityBoots;
    public final ForgeConfigSpec.IntValue hardiumDurabilityHelmet;
    public final ForgeConfigSpec.IntValue hardiumDurabilityChestplate;
    public final ForgeConfigSpec.IntValue hardiumDurabilityLeggings;
    public final ForgeConfigSpec.IntValue hardiumDurabilityBoots;
    public final ForgeConfigSpec.IntValue telosDurabilityHelmet;
    public final ForgeConfigSpec.IntValue telosDurabilityChestplate;
    public final ForgeConfigSpec.IntValue telosDurabilityLeggings;
    public final ForgeConfigSpec.IntValue telosDurabilityBoots;
    public final ForgeConfigSpec.IntValue easiumEnchantability;
    public final ForgeConfigSpec.IntValue mediumEnchantability;
    public final ForgeConfigSpec.IntValue hardiumEnchantability;
    public final ForgeConfigSpec.IntValue telosEnchantability;
    public final ForgeConfigSpec.DoubleValue easiumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue mediumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue hardiumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue telosKnockbackResistance;

    public int getEasiumDefense(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> easiumDefenseHelmet.get();
            case CHESTPLATE -> easiumDefenseChestplate.get();
            case LEGGINGS -> easiumDefenseLeggings.get();
            case BOOTS -> easiumDefenseBoots.get();
        };
    }

    public int getMediumDefense(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> mediumDefenseHelmet.get();
            case CHESTPLATE -> mediumDefenseChestplate.get();
            case LEGGINGS -> mediumDefenseLeggings.get();
            case BOOTS -> mediumDefenseBoots.get();
        };
    }

    public int getHardiumDefense(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> hardiumDefenseHelmet.get();
            case CHESTPLATE -> hardiumDefenseChestplate.get();
            case LEGGINGS -> hardiumDefenseLeggings.get();
            case BOOTS -> hardiumDefenseBoots.get();
        };
    }

    public int getTelosDefense(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> telosDefenseHelmet.get();
            case CHESTPLATE -> telosDefenseChestplate.get();
            case LEGGINGS -> telosDefenseLeggings.get();
            case BOOTS -> telosDefenseBoots.get();
        };
    }

    public int getEasiumDurability(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> easiumDurabilityHelmet.get();
            case CHESTPLATE -> easiumDurabilityChestplate.get();
            case LEGGINGS -> easiumDurabilityLeggings.get();
            case BOOTS -> easiumDurabilityBoots.get();
        };
    }

    public int getMediumDurability(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> mediumDurabilityHelmet.get();
            case CHESTPLATE -> mediumDurabilityChestplate.get();
            case LEGGINGS -> mediumDurabilityLeggings.get();
            case BOOTS -> mediumDurabilityBoots.get();
        };
    }

    public int getHardiumDurability(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> hardiumDurabilityHelmet.get();
            case CHESTPLATE -> hardiumDurabilityChestplate.get();
            case LEGGINGS -> hardiumDurabilityLeggings.get();
            case BOOTS -> hardiumDurabilityBoots.get();
        };
    }

    public int getTelosDurability(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> telosDurabilityHelmet.get();
            case CHESTPLATE -> telosDurabilityChestplate.get();
            case LEGGINGS -> telosDurabilityLeggings.get();
            case BOOTS -> telosDurabilityBoots.get();
        };
    }



    private CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("generation");
        easiumDefenseHelmet = builder.defineInRange("easiumDefenseHelmet", 2, 0, 100);
        easiumDefenseChestplate = builder.defineInRange("easiumDefenseChestplate", 4, 0, 100);
        easiumDefenseLeggings = builder.defineInRange("easiumDefenseLeggings", 5, 0, 100);
        easiumDefenseBoots = builder.defineInRange("easiumDefenseBoots", 2, 0, 100);
        mediumDefenseHelmet = builder.defineInRange("mediumDefenseHelmet", 3, 0, 100);
        mediumDefenseChestplate = builder.defineInRange("mediumDefenseChestplate", 6, 0, 100);
        mediumDefenseLeggings = builder.defineInRange("mediumDefenseLeggings", 8, 0, 100);
        mediumDefenseBoots = builder.defineInRange("mediumDefenseBoots", 3, 0, 100);
        hardiumDefenseHelmet = builder.defineInRange("hardiumDefenseHelmet", 3, 0, 100);
        hardiumDefenseChestplate = builder.defineInRange("hardiumDefenseChestplate", 6, 0, 100);
        hardiumDefenseLeggings = builder.defineInRange("hardiumDefenseLeggings", 8, 0, 100);
        hardiumDefenseBoots = builder.defineInRange("hardiumDefenseBoots", 3, 0, 100);
        telosDefenseHelmet = builder.defineInRange("telosDefenseHelmet", 3, 0, 100);
        telosDefenseChestplate = builder.defineInRange("telosDefenseChestplate", 6, 0, 100);
        telosDefenseLeggings = builder.defineInRange("telosDefenseLeggings", 8, 0, 100);
        telosDefenseBoots = builder.defineInRange("telosDefenseBoots", 3, 0, 100);
        easiumToughness = builder.defineInRange("easiumToughness", 0.0, 0.0, 10.0);
        mediumToughness = builder.defineInRange("mediumToughness", 2.0, 0.0, 10.0);
        hardiumToughness = builder.defineInRange("hardiumToughness", 3.0, 0.0, 10.0);
        telosToughness = builder.defineInRange("telosToughness", 4.0, 0.0, 10.0);
        easiumDurabilityHelmet = builder.defineInRange("easiumDurabilityHelmet", 15, 0, 10000);
        easiumDurabilityChestplate = builder.defineInRange("easiumDurabilityChestplate", 5, 0, 10000);
        easiumDurabilityLeggings = builder.defineInRange("easiumDurabilityLeggings", 6, 0, 10000);
        easiumDurabilityBoots = builder.defineInRange("easiumDurabilityBoots", 2, 0, 10000);
        mediumDurabilityHelmet = builder.defineInRange("mediumDurabilityHelmet", 33, 0, 10000);
        mediumDurabilityChestplate = builder.defineInRange("mediumDurabilityChestplate", 6, 0, 10000);
        mediumDurabilityLeggings = builder.defineInRange("mediumDurabilityLeggings", 8, 0, 10000);
        mediumDurabilityBoots = builder.defineInRange("mediumDurabilityBoots", 3, 0, 10000);
        hardiumDurabilityHelmet = builder.defineInRange("hardiumDurabilityHelmet", 37, 0, 10000);
        hardiumDurabilityChestplate = builder.defineInRange("hardiumDurabilityChestplate", 6, 0, 10000);
        hardiumDurabilityLeggings = builder.defineInRange("hardiumDurabilityLeggings", 8, 0, 10000);
        hardiumDurabilityBoots = builder.defineInRange("hardiumDurabilityBoots", 3, 0, 10000);
        telosDurabilityHelmet = builder.defineInRange("telosDurabilityHelmet", 37, 0, 10000);
        telosDurabilityChestplate = builder.defineInRange("telosDurabilityChestplate", 6, 0, 10000);
        telosDurabilityLeggings = builder.defineInRange("telosDurabilityLeggings", 8, 0, 10000);
        telosDurabilityBoots = builder.defineInRange("telosDurabilityBoots", 3, 0, 10000);
        easiumEnchantability = builder.defineInRange("easiumEnchantability", 9, 0, 100);
        mediumEnchantability = builder.defineInRange("mediumEnchantability", 10, 0, 100);
        hardiumEnchantability = builder.defineInRange("hardiumEnchantability", 15, 0, 100);
        telosEnchantability = builder.defineInRange("telosEnchantability", 15, 0, 100);
        easiumKnockbackResistance = builder.defineInRange("easiumKnockbackResistance", 0.0, 0.0, 1.0);
        mediumKnockbackResistance = builder.defineInRange("mediumKnockbackResistance", 0.0, 0.0, 1.0);
        hardiumKnockbackResistance = builder.defineInRange("hardiumKnockbackResistance", 0.1, 0.0, 1.0);
        telosKnockbackResistance = builder.defineInRange("telosKnockbackResistance", 0.1, 0.0, 1.0);
        builder.pop();
    }
}
