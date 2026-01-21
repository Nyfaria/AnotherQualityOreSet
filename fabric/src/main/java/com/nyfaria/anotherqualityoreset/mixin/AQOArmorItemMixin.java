package com.nyfaria.anotherqualityoreset.mixin;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(AQOArmorItem.class)
public abstract class AQOArmorItemMixin extends ArmorItem implements GeoItem, FabricElytraItem {
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public AQOArmorItemMixin(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public boolean useCustomElytra(LivingEntity entity, ItemStack chestStack, boolean tickElytra) {
        if(entity instanceof Player player) {
            if(((AQOArmorItem)(Object)this).isWearingSet(player) && getMaterial() == AQOArmoMaterials.TELOS){
                if(tickElytra){
                    doVanillaElytraTick(player, chestStack);
                }
                return true;
            }
        }
        return false;
    }
    @Override
    public void doVanillaElytraTick(LivingEntity entity, ItemStack chestStack) {
        int nextRoll = entity.getFallFlyingTicks() + 1;

        if (!entity.level().isClientSide && nextRoll % 10 == 0) {
            entity.gameEvent(GameEvent.ELYTRA_GLIDE);
        }
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public HumanoidModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<LivingEntity> original) {
                if(this.renderer == null) // Important that we do this. If we just instantiate  it directly in the field it can cause incompatibilities with some mods.
                    this.renderer = new GeoArmorRenderer<>(new DefaultedItemGeoModel<>(new ResourceLocation(Constants.MODID, "armor/" + ((AQOArmorItem)(Object)this).getMaterial().getName().toLowerCase())));

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }


}
