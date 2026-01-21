package com.nyfaria.anotherqualityoreset.api.tree.builder.position;

import com.nyfaria.anotherqualityoreset.api.tree.builder.ToAnalyzePos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Collection;

public interface IPositionFetcher{
	Collection<ToAnalyzePos> getPositions(Level world, BlockPos originPos, ToAnalyzePos parent);
}
