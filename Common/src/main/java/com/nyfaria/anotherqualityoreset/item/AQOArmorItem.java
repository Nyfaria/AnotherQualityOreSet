package com.nyfaria.anotherqualityoreset.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.api.ClientUtils;
import com.nyfaria.anotherqualityoreset.config.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class AQOArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(Type.class), ($$0) -> {
        $$0.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        $$0.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        $$0.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        $$0.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    private final List<Supplier<MobEffectInstance>> effects;
    private Multimap<Attribute, AttributeModifier> defaultModifiers = null;

    public AQOArmorItem(ArmorMaterial armorMaterial, Type type, Properties properties, Supplier<MobEffectInstance>... effects) {
        super(armorMaterial, type, properties);
        this.effects = List.of(effects);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot $$0) {
        if (!($$0 == this.type.getSlot())) return ImmutableMultimap.of();
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
    public boolean canBeDepleted() {
        return super.canBeDepleted();
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
            if (AQOArmoMaterials.TELOS == getMaterial()) {
                return CommonConfig.INSTANCE.telosDefense.get().get(this.getType().ordinal());
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
            if (AQOArmoMaterials.TELOS == getMaterial()) {
                return CommonConfig.INSTANCE.telosEnchantability.get();
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
            if (AQOArmoMaterials.TELOS == getMaterial()) {
                return CommonConfig.INSTANCE.telosToughness.get().floatValue();
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
            if (AQOArmoMaterials.TELOS == getMaterial()) {
                return CommonConfig.INSTANCE.telosKnockbackResistance.get();
            }
        }
        return 0.0;
    }

    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player player)
            return isWearingSet(player) && getMaterial() == AQOArmoMaterials.TELOS;
        return false;
    }


    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                entity.gameEvent(GameEvent.ELYTRA_GLIDE);
            }
        }
        return true;
    }

    public boolean isWearingSet(Player player) {
        return isWearingSet(player.getInventory().armor);
    }


    public boolean isWearingSet(NonNullList<ItemStack> armor) {
        for (ItemStack stack : armor) {
            Item item = stack.getItem();
            // If the armor isn't an instance of us or its armor type is different, not our set.
            if (!(item instanceof AQOArmorItem genericArmorItem) || !genericArmorItem.getMaterial().equals(this.getMaterial()))
                return false;
        }
        return true;
    }

    public void onAQOArmorTick(ItemStack itemStack, Player player, Level level) {
        if (!level.isClientSide && canHaveSetBonus(player) && (this.type.getSlot() == EquipmentSlot.HEAD && level.getGameTime() % 20L == 0L && isWearingSet(player))) {
            this.applySetBonus(player);
        }
    }

    protected void applySetBonus(Player player) {
        for (Supplier<MobEffectInstance> effect : effects) {
            // Only create a new instance when we need to. If the player already has it, then ours will just be copied
            player.addEffect(player.hasEffect(effect.get().getEffect()) ? effect.get() : new MobEffectInstance(effect.get()));
        }
    }


    protected boolean canHaveSetBonus(Player player) {
        return !this.effects.isEmpty();
    }



    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag context) {
        super.appendHoverText(stack, level, tooltip, context);
        if(canHaveSetBonus(null)){
            ChatFormatting color = isWearingSet(ClientUtils.getClientPlayer()) ? ChatFormatting.GREEN : ChatFormatting.GRAY;
            tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.tooltip").withStyle(color));
            effects.forEach(effect -> {
                tooltip.add(Component.literal("   ").append(effect.get().getEffect().getDisplayName().copy().withStyle(color)));
            });
            if (getMaterial() != AQOArmoMaterials.MEDIUM) {
                tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.elytra_tooltip").withStyle(color));
                if(getMaterial() == AQOArmoMaterials.TELOS){
                    tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.durability").withStyle(color));
                }
            }
        }
    }






    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
