package com.nyfaria.anotherqualityoreset.mixin;

import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.item.AQOArmorItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void getMaxDamage(int amount, RandomSource random, ServerPlayer serverPlayer, CallbackInfoReturnable<Boolean> cir) {
        if (getItem() instanceof AQOArmorItem item && item.isWearingSet(serverPlayer) && item.getMaterial() == AQOArmoMaterials.TELOS) {
            cir.setReturnValue(false);
        }
    }
}
