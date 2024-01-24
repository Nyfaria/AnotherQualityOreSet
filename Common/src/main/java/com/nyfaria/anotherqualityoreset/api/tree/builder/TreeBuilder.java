package com.nyfaria.anotherqualityoreset.api.tree.builder;

import com.nyfaria.anotherqualityoreset.api.tree.Tree;
import com.nyfaria.anotherqualityoreset.api.tree.builder.position.BasicPositionFetcher;
import com.nyfaria.anotherqualityoreset.api.tree.builder.position.IPositionFetcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.nyfaria.anotherqualityoreset.api.tree.utils.FallingTreeUtils.*;
import static com.nyfaria.anotherqualityoreset.api.tree.utils.TreePartType.*;
import static java.util.Optional.empty;

public class TreeBuilder{
	private static final EnumSet<Direction> ALL_DIRECTIONS = EnumSet.allOf(Direction.class);
	
	public static Optional<Tree> getTree(Level world, BlockPos originPos, BlockState state) throws TreeTooBigException{
		Block originBlock = state.getBlock();
		if(!isLogBlock(originBlock)){
			return empty();
		}
		
		int maxLogCount = Integer.MAX_VALUE;
		Queue<ToAnalyzePos> toAnalyzePos = new PriorityQueue<>();
		Set<ToAnalyzePos> analyzedPos = new HashSet<>();
		Tree tree = new Tree(world, originPos);
		toAnalyzePos.add(new ToAnalyzePos(getFirstPositionFetcher(), originPos, originBlock, originPos, originBlock, LOG, 0));
		
		Predicate<BlockPos> boundingBoxSearch = getBoundingBoxSearch(originPos);
		Predicate<Block> adjacentPredicate = getAdjacentPredicate();
		
		try{
			while(!toAnalyzePos.isEmpty()){
				ToAnalyzePos analyzingPos = toAnalyzePos.remove();
				tree.addPart(analyzingPos.toTreePart());
				analyzedPos.add(analyzingPos);

				tree.getLogCount();

				Collection<ToAnalyzePos> potentialPositions = analyzingPos.getPositionFetcher().getPositions(world, originPos, analyzingPos);
				Collection<ToAnalyzePos> nextPositions = filterPotentialPos(boundingBoxSearch, adjacentPredicate, world, originPos, originBlock, analyzingPos, potentialPositions, analyzedPos);
				
				nextPositions.removeAll(analyzedPos);
				nextPositions.removeAll(toAnalyzePos);
				toAnalyzePos.addAll(nextPositions);
			}
		}
		catch(AbortSearchException e){
			return Optional.empty();
		}

			int aroundRequired = 1;
			if(tree.getTopMostLog()
					.map(topLog -> getLeavesAround(world, topLog) < aroundRequired)
					.orElse(true)){
				return empty();
			}

		
		return Optional.of(tree);
	}
	
	private static Predicate<Block> getAdjacentPredicate() {
		Collection<Block> adjacentBlocksBase;
		adjacentBlocksBase = new HashSet<>();
		adjacentBlocksBase.add(Blocks.AIR);
		adjacentBlocksBase.addAll(BuiltInRegistries.BLOCK.getTag(BlockTags.LEAVES).get().stream().toList().stream().map(block->block.value()).toList());
		adjacentBlocksBase.addAll(BuiltInRegistries.BLOCK.getTag(BlockTags.LOGS).get().stream().toList().stream().map(block->block.value()).toList());
		return block -> true;


	}
	
	private static Predicate<BlockPos> getBoundingBoxSearch(BlockPos originPos){
		int radius = -1;
		return pos -> true;

	}
	
	private static IPositionFetcher getFirstPositionFetcher(){
		return BasicPositionFetcher.getInstance();
	}
	
	private static Collection<ToAnalyzePos> filterPotentialPos(Predicate<BlockPos> boundingBoxSearch,
			Predicate<Block> adjacentPredicate,
			Level world,
			BlockPos originPos,
			Block originBlock,
			ToAnalyzePos parent,
			Collection<ToAnalyzePos> potentialPos,
			Collection<ToAnalyzePos> analyzedPos){
		return potentialPos.stream()
				.filter(pos -> !analyzedPos.contains(pos))
				.filter(pos -> shouldIncludeInChain(boundingBoxSearch, originPos, originBlock, parent, pos))
				.filter(pos -> EnumSet.allOf(Direction.class).stream()
						.map(direction -> pos.getCheckPos().relative(direction))
						.map(world::getBlockState)
						.map(BlockState::getBlock)
						.allMatch(adjacentPredicate))
				.collect(Collectors.toList());
	}
	
	private static long getLeavesAround(Level world, BlockPos blockPos){
		return ALL_DIRECTIONS.stream()
				.map(blockPos::relative)
				.filter(testPos -> {
					Block block = world.getBlockState(testPos).getBlock();
					return isLeafBlock(block) || isNetherWartOrShroomlight(block) || isLeafNeedBreakBlock(block);
				})
				.count();
	}
	
	private static boolean shouldIncludeInChain(Predicate<BlockPos> boundingBoxSearch, BlockPos originPos, Block originBlock, ToAnalyzePos parent, ToAnalyzePos check){
		if(parent.getTreePartType() == LOG && isSameTree(originBlock, check) && boundingBoxSearch.test(check.getCheckPos())){
			return true;
		}
			if(check.getTreePartType() == NETHER_WART){
				BlockPos checkBlockPos = check.getCheckPos();
				int dx = Math.abs(originPos.getX() - checkBlockPos.getX());
				int dz = Math.abs(originPos.getZ() - checkBlockPos.getZ());
				return dx <= 4 && dz <= 4;
			}

		return check.getTreePartType() == LEAF_NEED_BREAK;
	}
	
	private static boolean isSameTree(Block parentLogBlock, ToAnalyzePos check){
			return check.getTreePartType() == LOG;

	}
}
