package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.api.tree.breaking.ITreeBreakingHandler;
import com.nyfaria.anotherqualityoreset.api.tree.breaking.InstantaneousTreeBreakingHandler;
import com.nyfaria.anotherqualityoreset.api.tree.builder.TreeBuilder;
import com.nyfaria.anotherqualityoreset.api.tree.builder.TreeTooBigException;
import com.nyfaria.anotherqualityoreset.item.TreeAxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

import java.util.concurrent.atomic.AtomicBoolean;

import static net.minecraft.world.InteractionHand.MAIN_HAND;

public class TreeAxeEvents {
    public static boolean canPlayerBreakTree(@Nonnull Player player, BlockPos pos) {
        Item heldItem = player.getItemInHand(MAIN_HAND).getItem();
        return (heldItem instanceof TreeAxeItem);
    }
    public static boolean breakTree(Player player, BlockPos thePos, BlockState state){
        AtomicBoolean shouldCancel = new AtomicBoolean(false);
        if (canPlayerBreakTree(player, thePos) && player.level() instanceof Level) {
            try {
                TreeBuilder.getTree(player.level(), thePos, state).ifPresent(tree -> {
                    shouldCancel.set(getBreakingHandler().breakTree(player, tree));
                });
            } catch (TreeTooBigException ignored) {
            }
        }
        return shouldCancel.get();
    }
    public static ITreeBreakingHandler getBreakingHandler() {
        return InstantaneousTreeBreakingHandler.getInstance();
    }

}
