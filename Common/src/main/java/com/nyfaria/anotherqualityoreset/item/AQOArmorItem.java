package com.nyfaria.anotherqualityoreset.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.config.CommonConfig;
import net.minecraft.Util;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.EnumMap;
import java.util.UUID;

public class AQOArmorItem extends ArmorItem {
    private static final EnumMap<Type,UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(Type.class), ($$0) -> {
        $$0.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        $$0.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        $$0.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        $$0.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    private Multimap<Attribute, AttributeModifier> defaultModifiers = null;

    public AQOArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot $$0) {
        if(!($$0 == this.type.getSlot())) return ImmutableMultimap.of();
        if (this.defaultModifiers == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> $$3 = ImmutableMultimap.builder();
            UUID $$4 = (UUID) ARMOR_MODIFIER_UUID_PER_TYPE.get(this.getType());
            $$3.put(Attributes.ARMOR, new AttributeModifier($$4, "Armor modifier", (double) this.getDefense(), AttributeModifier.Operation.ADDITION));
            $$3.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier($$4, "Armor toughness", (double) this.getToughness(), AttributeModifier.Operation.ADDITION));
            $$3.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier($$4, "Armor knockback resistance", (double) this.getKnockBackResistance(), AttributeModifier.Operation.ADDITION));
            this.defaultModifiers = $$3.build();
        }
        return this.defaultModifiers;
    }

    @Override
    public int getDefense() {
        if (CommonConfig.CONFIG_SPEC.isLoaded()) {
            if (AQOArmoMaterials.EASIUM == getMaterial()) {
                return CommonConfig.INSTANCE.easiumDefense.get().get(this.getType().ordinal());
            }
            if (AQOArmoMaterials.MEDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.mediumDefense.get().get(this.getType().ordinal());
            }
            if (AQOArmoMaterials.HARDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.hardiumDefense.get().get(this.getType().ordinal());
            }
        }
        return super.getDefense();
    }

    @Override
    public int getEnchantmentValue() {
        if (CommonConfig.CONFIG_SPEC.isLoaded()) {
            if (AQOArmoMaterials.EASIUM == getMaterial()) {
                return CommonConfig.INSTANCE.easiumEnchantability.get();
            }
            if (AQOArmoMaterials.MEDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.mediumEnchantability.get();
            }
            if (AQOArmoMaterials.HARDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.hardiumEnchantability.get();
            }
        }
        return super.getEnchantmentValue();
    }

    @Override
    public float getToughness() {
        if (CommonConfig.CONFIG_SPEC.isLoaded()) {
            if (AQOArmoMaterials.EASIUM == getMaterial()) {
                return CommonConfig.INSTANCE.easiumToughness.get().floatValue();
            }
            if (AQOArmoMaterials.MEDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.mediumToughness.get().floatValue();
            }
            if (AQOArmoMaterials.HARDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.hardiumToughness.get().floatValue();
            }
        }
        return super.getToughness();
    }

    public double getKnockBackResistance() {
        if (CommonConfig.CONFIG_SPEC.isLoaded()) {
            if (AQOArmoMaterials.EASIUM == getMaterial()) {
                return CommonConfig.INSTANCE.easiumKnockbackResistance.get();
            }
            if (AQOArmoMaterials.MEDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.mediumKnockbackResistance.get();
            }
            if (AQOArmoMaterials.HARDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.hardiumKnockbackResistance.get();
            }
        }
        return 0.0;
    }

}
