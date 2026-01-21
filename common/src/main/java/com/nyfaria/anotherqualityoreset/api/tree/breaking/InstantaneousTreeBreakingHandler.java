package com.nyfaria.anotherqualityoreset.api.tree.breaking;

import com.nyfaria.anotherqualityoreset.api.tree.Tree;
import com.nyfaria.anotherqualityoreset.api.tree.TreePart;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.Comparator;

import static net.minecraft.stats.Stats.ITEM_USED;
import static net.minecraft.world.InteractionHand.MAIN_HAND;

public class InstantaneousTreeBreakingHandler implements ITreeBreakingHandler{
	private static InstantaneousTreeBreakingHandler INSTANCE;
	
	@Override
	public boolean breakTree(Player player, Tree tree){
		if(!destroy(tree, player, player.getItemInHand(MAIN_HAND))){
			return false;
		}
		return true;
	}

	
	private boolean destroy(@Nonnull Tree tree, @Nonnull Player player, @Nonnull ItemStack tool){
		Level world = tree.getWorld();
		int breakableCount = tree.getBreakableCount();
		int damageMultiplicand = 0;
		int toolUsesLeft =  Integer.MAX_VALUE;

		double rawWeightedUsesLeft = toolUsesLeft - 1;

			if(breakableCount >= rawWeightedUsesLeft){
				rawWeightedUsesLeft = Math.ceil(rawWeightedUsesLeft) - 1;
			}

		
		int brokenCount = tree.getBreakableParts().stream()
				.sorted(Comparator.comparingInt(TreePart::getSequence).reversed())
				.limit((int) rawWeightedUsesLeft)
				.map(TreePart::getBlockPos)
				.mapToInt(logBlockPos -> {
					BlockState logState = world.getBlockState(logBlockPos);
					player.awardStat(ITEM_USED.get(logState.getBlock().asItem()));
					logState.getBlock().playerDestroy(world, player, logBlockPos, logState, world.getBlockEntity(logBlockPos), tool);
					world.removeBlock(logBlockPos, false);
					return 1;
				})
				.sum();
		
		int toolDamage = 0 - 1;

			forceBreakDecayLeaves(tree, world);

		return true;
	}
	
	private static void forceBreakDecayLeaves(@Nonnull Tree tree, Level world){
		int radius = 5;
	}
	
	public static InstantaneousTreeBreakingHandler getInstance(){
		if(INSTANCE == null){
			INSTANCE = new InstantaneousTreeBreakingHandler();
		}
		return INSTANCE;
	}
}
