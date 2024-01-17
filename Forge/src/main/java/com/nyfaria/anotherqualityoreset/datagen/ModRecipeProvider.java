package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModRecipeProvider extends RecipeProvider {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        Stream.of(
                BlockInit.EASIUM_ORE,
                BlockInit.MEDIUM_ORE,
                BlockInit.HARDIUM_ORE
        ).forEach(collection->oreCollectionRecipes(collection, pWriter));

    }

    public void oreCollectionRecipes(OreCollection collection, Consumer<FinishedRecipe> recipeSaver) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.getAxe().get())
                .pattern("XX ")
                .pattern("X# ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.getHoe().get())
                .pattern("XX ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.getPickaxe().get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.getShovel().get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.getSword().get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.getHelmet().get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.getChestplate().get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.getLeggings().get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.getBoots().get())
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        oreSmelting(recipeSaver, List.of(collection.getOre().get(), collection.getRawOre().get()),RecipeCategory.BUILDING_BLOCKS, collection.getIngot().get(), 1.0f, 200, collection.getName());
        oreBlasting(recipeSaver, List.of(collection.getOre().get(), collection.getRawOre().get()),RecipeCategory.BUILDING_BLOCKS, collection.getIngot().get(), 1.0f, 100, collection.getName());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeSaver,RecipeCategory.BUILDING_BLOCKS, collection.getIngot().get(),RecipeCategory.BUILDING_BLOCKS, collection.getBlock().get(), collection.getName() + "_ingot_from_" + collection.getName() + "_block", collection.getName() + "_ingot");
        nineBlockStorageRecipesWithCustomPacking(recipeSaver,RecipeCategory.BUILDING_BLOCKS, collection.getNugget().get(),RecipeCategory.BUILDING_BLOCKS, collection.getIngot().get(), collection.getName() + "_ingot_from_nuggets", collection.getName() + "_ingot");
        nineBlockStorageRecipes(recipeSaver, RecipeCategory.BUILDING_BLOCKS,collection.getRawOre().get(),RecipeCategory.BUILDING_BLOCKS, collection.getRawOreBlock().get());
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(
                                collection.getPickaxe().get(),
                                collection.getShovel().get(),
                                collection.getAxe().get(),
                                collection.getHoe().get(),
                                collection.getSword().get(),
                                collection.getHelmet().get(),
                                collection.getChestplate().get(),
                                collection.getLeggings().get(),
                                collection.getBoots().get()
//                                , Items.GOLDEN_HORSE_ARMOR
                        ),RecipeCategory.MISC,
                        collection.getNugget().get(), 0.1F, 100)
                .unlockedBy("has_" + collection.getName() + "_pickaxe", has(collection.getPickaxe().get()))
                .unlockedBy("has_" + collection.getName() + "_shovel", has(collection.getShovel().get()))
                .unlockedBy("has_" + collection.getName() + "_axe", has(collection.getAxe().get()))
                .unlockedBy("has_" + collection.getName() + "_hoe", has(collection.getHoe().get()))
                .unlockedBy("has_" + collection.getName() + "_sword", has(collection.getSword().get()))
                .unlockedBy("has_" + collection.getName() + "_helmet", has(collection.getHelmet().get()))
                .unlockedBy("has_" + collection.getName() + "_chestplate", has(collection.getChestplate().get()))
                .unlockedBy("has_" + collection.getName() + "_leggings", has(collection.getLeggings().get()))
                .unlockedBy("has_" + collection.getName() + "_boots", has(collection.getBoots().get()))
//                .unlockedBy("has_" + collection.getName() + "_horse_armor", has(Items.GOLDEN_HORSE_ARMOR))
                .save(recipeSaver, getBlastingRecipeName(collection.getNugget().get()));
    }




}
