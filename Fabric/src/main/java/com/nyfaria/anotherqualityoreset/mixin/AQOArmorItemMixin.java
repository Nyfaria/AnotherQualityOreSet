package com.nyfaria.anotherqualityoreset.mixin;

import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AQOArmorItem.class)
public class AQOArmorItemMixin extends ArmorItem implements FabricElytraItem {

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
}
