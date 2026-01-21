package com.nyfaria.anotherqualityoreset.event;

import com.nyfaria.anotherqualityoreset.api.HammerEvents;
import com.nyfaria.anotherqualityoreset.api.TreeAxeEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {
    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        HammerEvents.startBreak(event.getFace());
    }

    @SubscribeEvent
    public static void onBlockBreakEvent(@Nonnull BlockEvent.BreakEvent event) {
        if (!event.isCanceled() && !event.getLevel().isClientSide()) {
            BlockPos thePos = event.getPos();
            HammerEvents.breakBlock((ServerPlayer) event.getPlayer(), thePos, event.getPlayer().getMainHandItem());
            TreeAxeEvents.breakTree((ServerPlayer) event.getPlayer(), thePos, event.getState());
        }
    }
}
