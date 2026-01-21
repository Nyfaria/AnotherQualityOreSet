package com.nyfaria.anotherqualityoreset.mixin;

import com.nyfaria.anotherqualityoreset.AnotherQualityOreSet;
import com.nyfaria.anotherqualityoreset.item.PaxelItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PaxelItem.class)
public abstract class PaxelMixin extends DiggerItem {

    public PaxelMixin(float pAttackDamageModifier, float pAttackSpeedModifier, Tier pTier, TagKey<Block> pBlocks, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, pBlocks, pProperties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return AnotherQualityOreSet.PAXEL_ACTIONS.contains(toolAction);
    }
}
