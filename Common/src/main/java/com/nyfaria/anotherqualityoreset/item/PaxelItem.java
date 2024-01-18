package com.nyfaria.anotherqualityoreset.item;

import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import com.nyfaria.anotherqualityoreset.init.TagInit;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;

public class PaxelItem extends DiggerItem {
    public PaxelItem(float damage, float attackSpeed, AQOToolTiers tier, Properties properties) {
        super(damage, attackSpeed, tier, TagInit.MINEABLE_WITH_PAXEL ,properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return Items.DIAMOND_AXE.useOn(context);
    }

}
