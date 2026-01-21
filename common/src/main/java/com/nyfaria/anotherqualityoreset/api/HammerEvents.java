package com.nyfaria.anotherqualityoreset.api;

import com.nyfaria.anotherqualityoreset.item.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class HammerEvents {
    public static Direction face = Direction.NORTH;
    static boolean doIt = true;

    public static void startBreak(Direction face) {
        HammerEvents.face = face;
    }
    public static void breakBlock(ServerPlayer player, BlockPos thePos, ItemStack pStack) {
        if (pStack.getItem() instanceof HammerItem giantPickaxe) {
            Level pLevel = player.level();
            double radius = giantPickaxe.getRadius(player, thePos) - 1;
            if (doIt) {
                for (int x = -(int) Math.floor(radius / 2); x <= (int) Math.ceil(radius / 2); x++) {
                    for (int y = -(int) Math.floor(radius / 2); y <= (int) Math.ceil(radius / 2); y++) {
                        doIt = false;
                        BlockPos pos = (thePos
                                .relative(face.getAxis() == Direction.Axis.X ? Direction.UP : face.getAxis() == Direction.Axis.Z ? Direction.UP : Direction.NORTH, x)
                                .relative(face.getAxis() == Direction.Axis.X ? Direction.NORTH : Direction.WEST, y)
                        );
                        ItemStack itemStack = new ItemStack(pStack.getItem());
                        Block block = pLevel.getBlockState(pos).getBlock();
                        if (itemStack.getItem().isCorrectToolForDrops(pLevel.getBlockState(pos)) || !pLevel.getBlockState(pos).requiresCorrectToolForDrops()) {
                            block.playerDestroy(pLevel, player, pos, pLevel.getBlockState(pos), pLevel.getBlockEntity(pos), itemStack);
                            pLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                        }
                        doIt = true;

                    }
                }
            }
        }
    }
}
