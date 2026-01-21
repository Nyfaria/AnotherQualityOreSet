package com.nyfaria.anotherqualityoreset.api.tree;

import com.nyfaria.anotherqualityoreset.api.tree.utils.TreePartType;
import net.minecraft.core.BlockPos;

public class TreePart{
	private final BlockPos blockPos;
	private final TreePartType treePartType;
	private final int sequence;
	
	public TreePart(BlockPos blockPos, TreePartType treePartType, int sequence){
		this.blockPos = blockPos;
		this.treePartType = treePartType;
		this.sequence = sequence;
	}
	
	public BlockPos getBlockPos(){
		return blockPos;
	}
	
	public int getSequence(){
		return sequence;
	}
	
	public TreePartType getTreePartType(){
		return treePartType;
	}
}
