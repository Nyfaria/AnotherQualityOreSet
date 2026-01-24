package com.nyfaria.anotherqualityoreset.item;

import com.google.common.collect.*;
import com.nyfaria.anotherqualityoreset.*;
import com.nyfaria.anotherqualityoreset.api.*;
import com.nyfaria.anotherqualityoreset.config.*;
import net.minecraft.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.*;
import org.jetbrains.annotations.*;
import software.bernie.geckolib.animatable.*;
import software.bernie.geckolib.core.animatable.instance.*;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.util.*;

import java.util.*;
import java.util.function.*;

public class AQOArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(Type.class), (typeMap) -> {
        typeMap.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        typeMap.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        typeMap.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        typeMap.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
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
                return CommonConfig.INSTANCE.getEasiumDefense(this.getType());
            }
            if (AQOArmoMaterials.MEDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.getMediumDefense(this.getType());
            }
            if (AQOArmoMaterials.HARDIUM == getMaterial()) {
                return CommonConfig.INSTANCE.getHardiumDefense(this.getType());
            }
            if (AQOArmoMaterials.TELOS == getMaterial()) {
                return CommonConfig.INSTANCE.getTelosDefense(this.getType());
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
            return isWearingSet(player) && (getMaterial() == AQOArmoMaterials.TELOS || getMaterial() == AQOArmoMaterials.HARDIUM);
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
        if (canHaveSetBonus(null) && level != null && level.isClientSide) {
            ChatFormatting color = isWearingSet(ClientUtils2.getClientPlayer()) ? ChatFormatting.GREEN : ChatFormatting.GRAY;
            tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.tooltip").withStyle(color));
            effects.forEach(effect -> tooltip.add(Component.literal("   ").append(effect.get().getEffect().getDisplayName().copy().withStyle(color))));
            if (getMaterial() != AQOArmoMaterials.MEDIUM) {
                tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.elytra_tooltip").withStyle(color));
                if (getMaterial() == AQOArmoMaterials.TELOS) {
                    tooltip.add(Component.translatable("item." + Constants.MODID + ".aqo_armor_item.durability").withStyle(color));
                }
            }
        }
    }


    public Supplier<Object> getRenderProvider() {
        return null;
    }

    public void createRenderer(Consumer<Object> consumer) {
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
