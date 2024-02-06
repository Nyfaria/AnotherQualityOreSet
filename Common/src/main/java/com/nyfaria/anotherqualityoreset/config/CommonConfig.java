package com.nyfaria.anotherqualityoreset.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CommonConfig {
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final CommonConfig INSTANCE;

    static {
        Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        CONFIG_SPEC = pair.getRight();
        INSTANCE = pair.getLeft();
    }

    public final ForgeConfigSpec.ConfigValue<List<Integer>> easiumDefense;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> mediumDefense;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> hardiumDefense;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> telosDefense;
    public final ForgeConfigSpec.DoubleValue easiumToughness;
    public final ForgeConfigSpec.DoubleValue mediumToughness;
    public final ForgeConfigSpec.DoubleValue hardiumToughness;
    public final ForgeConfigSpec.DoubleValue telosToughness;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> easiumDurability;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> mediumDurability;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> hardiumDurability;
    public final ForgeConfigSpec.ConfigValue<List<Integer>> telosDurability;
    public final ForgeConfigSpec.IntValue easiumEnchantability;
    public final ForgeConfigSpec.IntValue mediumEnchantability;
    public final ForgeConfigSpec.IntValue hardiumEnchantability;
    public final ForgeConfigSpec.IntValue telosEnchantability;
    public final ForgeConfigSpec.DoubleValue easiumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue mediumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue hardiumKnockbackResistance;
    public final ForgeConfigSpec.DoubleValue telosKnockbackResistance;



    private CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("generation");
        easiumDefense = builder.comment("Easium Armor Defense").define("easiumDefense", List.of(2, 4, 5, 2));
        mediumDefense = builder.comment("Medium Armor Defense").define("mediumDefense", List.of(3, 6, 8, 3));
        hardiumDefense = builder.comment("Hardium Armor Defense").define("hardiumDefense", List.of(3, 6, 8, 3));
        telosDefense = builder.comment("Telos Armor Defense").define("telosDefense", List.of(3, 6, 8, 3));
        easiumToughness = builder.comment("Easium Armor Toughness").defineInRange("easiumToughness", 0.0, 0.0, 10.0);
        mediumToughness = builder.comment("Medium Armor Toughness").defineInRange("mediumToughness", 2.0, 0.0, 10.0);
        hardiumToughness = builder.comment("Hardium Armor Toughness").defineInRange("hardiumToughness", 3.0, 0.0, 10.0);
        telosToughness = builder.comment("Telos Armor Toughness").defineInRange("telosToughness", 4.0, 0.0, 10.0);
        easiumDurability = builder.comment("Easium Armor Durability").define("easiumDurability", List.of(15, 5, 6, 2));
        mediumDurability = builder.comment("Medium Armor Durability").define("mediumDurability", List.of(33, 6, 8, 3));
        hardiumDurability = builder.comment("Hardium Armor Durability").define("hardiumDurability", List.of(37, 6, 8, 3));
        telosDurability = builder.comment("Telos Armor Durability").define("telosDurability", List.of(37, 6, 8, 3));
        easiumEnchantability = builder.comment("Easium Armor Enchantability").defineInRange("easiumEnchantability", 9, 0, 100);
        mediumEnchantability = builder.comment("Medium Armor Enchantability").defineInRange("mediumEnchantability", 10, 0, 100);
        hardiumEnchantability = builder.comment("Hardium Armor Enchantability").defineInRange("hardiumEnchantability", 15, 0, 100);
        telosEnchantability = builder.comment("Telos Armor Enchantability").defineInRange("telosEnchantability", 15, 0, 100);
        easiumKnockbackResistance = builder.comment("Easium Armor Knockback Resistance").defineInRange("easiumKnockbackResistance", 0.0, 0.0, 1.0);
        mediumKnockbackResistance = builder.comment("Medium Armor Knockback Resistance").defineInRange("mediumKnockbackResistance", 0.0, 0.0, 1.0);
        hardiumKnockbackResistance = builder.comment("Hardium Armor Knockback Resistance").defineInRange("hardiumKnockbackResistance", 0.1, 0.0, 1.0);
        telosKnockbackResistance = builder.comment("Telos Armor Knockback Resistance").defineInRange("telosKnockbackResistance", 0.1, 0.0, 1.0);
        builder.pop();
    }
}
