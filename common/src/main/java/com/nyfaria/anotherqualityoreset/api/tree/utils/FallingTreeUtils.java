package com.nyfaria.anotherqualityoreset.api.tree.utils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.empty;
import static net.minecraft.tags.BlockTags.*;
import static net.minecraft.world.level.block.Blocks.SHROOMLIGHT;

public class FallingTreeUtils {
	public static Set<Item> getAsItems(Collection<? extends String> names){
		return names.stream()
				.filter(Objects::nonNull)
				.filter(val -> !val.isEmpty())
				.flatMap(FallingTreeUtils::getItem)
				.filter(Objects::nonNull)
				.collect(toSet());

	}
	
	@Nonnull
	public static Stream<Item> getItem(String name){
		try{
			boolean isTag = name.startsWith("#");
			if(isTag){
				name = name.substring(1);
			}
			ResourceLocation resourceLocation = new ResourceLocation(name);
			if(isTag){
				TagKey<Item> itemTagKey = TagKey.create(Registries.ITEM,resourceLocation);
				return Optional.ofNullable(BuiltInRegistries.ITEM.getTag(itemTagKey)
						.get().stream().map(h->h.value()))
						.orElse(empty());
			}
			return Stream.of(BuiltInRegistries.ITEM.get(resourceLocation));
		}
		catch(Exception e){
			return empty();
		}
	}
	
	public static Set<Block> getAsBlocks(Collection<? extends String> names){
		return names.stream()
				.filter(Objects::nonNull)
				.filter(val -> !val.isEmpty())
				.flatMap(FallingTreeUtils::getBlock)
				.filter(Objects::nonNull)
				.collect(toSet());
	}
	
	@Nonnull
	public static Stream<Block> getBlock(String name){
		try{
			boolean isTag = name.startsWith("#");
			if(isTag){
				name = name.substring(1);
			}
			ResourceLocation resourceLocation = new ResourceLocation(name);
			if(isTag){
				TagKey<Block> blockTagKey = TagKey.create(Registries.BLOCK, resourceLocation);
				return Optional.ofNullable(BuiltInRegistries.BLOCK.getTag(blockTagKey)
								.get().stream().map(h->h.value()))
						.orElse(empty());
			}
			return Stream.of(BuiltInRegistries.BLOCK.get(resourceLocation));
		}
		catch(Exception e){
			return empty();
		}
	}
	
	public static boolean isLeafBlock(@Nonnull Block block){
		boolean isWhitelistedBlock = block.defaultBlockState().is(LEAVES);
		if(isWhitelistedBlock){
			return true;
		}
		return false;
	}
	
	public static TreePartType getTreePart(Block checkBlock){
		if(isLogBlock(checkBlock)){
			return TreePartType.LOG;
		}
		if(isNetherWartOrShroomlight(checkBlock)){
			return TreePartType.NETHER_WART;
		}
		if(isLeafNeedBreakBlock(checkBlock)){
			return TreePartType.LEAF_NEED_BREAK;
		}
		return TreePartType.OTHER;
	}
	
	public static boolean isLeafNeedBreakBlock(Block block){
		return false;
	}
	
	public static boolean isLogBlock(Block block){
		boolean isWhitelistedBlock = block.defaultBlockState().is(LOGS);

		if(isWhitelistedBlock){
			return true;
		}
		return false;
	}
	
	public static boolean isNetherWartOrShroomlight(Block block){
		return block.defaultBlockState().is(WART_BLOCKS) || block.equals(SHROOMLIGHT);
	}
}
