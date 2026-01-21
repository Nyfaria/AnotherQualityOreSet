package com.nyfaria.anotherqualityoreset.api.tree.breaking;

import com.nyfaria.anotherqualityoreset.api.tree.Tree;
import net.minecraft.world.entity.player.Player;

public interface ITreeBreakingHandler{
	boolean breakTree(Player player, Tree tree);
}
