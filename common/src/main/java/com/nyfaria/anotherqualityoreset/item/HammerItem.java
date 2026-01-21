package com.nyfaria.anotherqualityoreset.item;

import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class HammerItem extends PickaxeItem {
    public final AQOToolTiers tier;
    public HammerItem(AQOToolTiers tier, int $$1, float $$2, Properties $$3) {
        super(tier, $$1, $$2, $$3);
        this.tier = tier;
    }

    public double getRadius(Player player, BlockPos thePos) {
        return tier.getBreakRadius();
    }
}
