package com.nyfaria.anotherqualityoreset.mixin;

import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public class InventoryMixin {
    @Shadow @Final public NonNullList<ItemStack> armor;

    @Shadow @Final public Player player;

    @Inject(method = "tick", at = @At("TAIL"))
    public void onArmorTick(CallbackInfo ci) {
        armor.stream().filter(itemStack -> itemStack.getItem() instanceof AQOArmorItem).forEach(
                itemStack -> ((AQOArmorItem) itemStack.getItem()).onAQOArmorTick(itemStack, player, player.level())
        );
    }
}
