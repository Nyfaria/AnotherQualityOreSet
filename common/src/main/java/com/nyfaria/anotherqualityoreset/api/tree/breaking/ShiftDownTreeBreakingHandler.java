package com.nyfaria.anotherqualityoreset.api.tree.breaking;

import com.nyfaria.anotherqualityoreset.api.tree.Tree;
import com.nyfaria.anotherqualityoreset.api.tree.TreePart;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

import static net.minecraft.stats.Stats.ITEM_USED;
import static net.minecraft.world.InteractionHand.MAIN_HAND;

public class ShiftDownTreeBreakingHandler implements ITreeBreakingHandler {
    private static ShiftDownTreeBreakingHandler INSTANCE;

    @Override
    public boolean breakTree(Player player, Tree tree) {
        destroy(tree, player, player.getItemInHand(MAIN_HAND));
        return true;
    }

    private void destroy(@Nonnull Tree tree, @Nonnull Player player, @Nonnull ItemStack tool) {
        Level world = tree.getWorld();
        int damageMultiplicand = 0;
        int toolUsesLeft = tool.isDamageableItem() ? (tool.getMaxDamage() - tool.getDamageValue()) : Integer.MAX_VALUE;

        if (toolUsesLeft <= damageMultiplicand) {
            player.sendSystemMessage(Component.translatable("chat.fallingtree.prevented_break_tool"));
            return;
        }

        tree.getLastSequencePart()
                .map(TreePart::getBlockPos)
                .ifPresent(logBlock -> {
                    final BlockState logState = world.getBlockState(logBlock);
                    player.awardStat(ITEM_USED.get(logState.getBlock().asItem()));
                    logState.getBlock().playerDestroy(world, player, tree.getHitPos(), logState, world.getBlockEntity(logBlock), tool);
                    world.removeBlock(logBlock, false);
                });

    }

    public static ShiftDownTreeBreakingHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShiftDownTreeBreakingHandler();
        }
        return INSTANCE;
    }
}
