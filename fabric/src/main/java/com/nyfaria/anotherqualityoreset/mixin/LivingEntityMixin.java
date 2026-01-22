package com.nyfaria.anotherqualityoreset.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.*;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import com.nyfaria.anotherqualityoreset.item.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack getItemInHand(InteractionHand hand);

    @WrapMethod(method="swing(Lnet/minecraft/world/InteractionHand;Z)V")
    private void anotherqualityoreset$preventSwingOnClient(InteractionHand hand, boolean updateSelf, Operation<Void> original) {
        ItemStack stack = this.getItemInHand(hand);
        if(stack.getItem() instanceof TelosSwordItem tsi) {
            if (stack.isEmpty() || !tsi.onEntitySwing(stack, (LivingEntity) (Object) this)) {
                original.call(hand, updateSelf);
            }
        }
    }
}
