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
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.axe().get())
                .pattern("XX ")
                .pattern("X# ")
                .pattern(" # ")
                .define('X', collection.ingot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.hoe().get())
                .pattern("XX ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.ingot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.pickaxe().get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.ingot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,collection.shovel().get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', collection.ingot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.sword().get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', collection.ingot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.helmet().get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', collection.ingot().get())
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.chestplate().get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', collection.ingot().get())
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.leggings().get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.ingot().get())
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT,collection.boots().get())
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.ingot().get())
                .unlockedBy("has_item", has(collection.ingot().get()))
                .save(recipeSaver);
        oreSmelting(recipeSaver, List.of(collection.ore().get(), collection.rawOre().get()),RecipeCategory.BUILDING_BLOCKS, collection.ingot().get(), 1.0f, 200, collection.name());
        oreBlasting(recipeSaver, List.of(collection.ore().get(), collection.rawOre().get()),RecipeCategory.BUILDING_BLOCKS, collection.ingot().get(), 1.0f, 100, collection.name());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeSaver,RecipeCategory.BUILDING_BLOCKS, collection.ingot().get(),RecipeCategory.BUILDING_BLOCKS, collection.block().get(), collection.name() + "_ingot_from_" + collection.name() + "_block", collection.name() + "_ingot");
        nineBlockStorageRecipesWithCustomPacking(recipeSaver,RecipeCategory.BUILDING_BLOCKS, collection.nugget().get(),RecipeCategory.BUILDING_BLOCKS, collection.ingot().get(), collection.name() + "_ingot_from_nuggets", collection.name() + "_ingot");
        nineBlockStorageRecipes(recipeSaver, RecipeCategory.BUILDING_BLOCKS,collection.rawOre().get(),RecipeCategory.BUILDING_BLOCKS, collection.rawOreBlock().get());
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(
                                collection.pickaxe().get(),
                                collection.shovel().get(),
                                collection.axe().get(),
                                collection.hoe().get(),
                                collection.sword().get(),
                                collection.helmet().get(),
                                collection.chestplate().get(),
                                collection.leggings().get(),
                                collection.boots().get()
//                                , Items.GOLDEN_HORSE_ARMOR
                        ),RecipeCategory.MISC,
                        collection.nugget().get(), 0.1F, 100)
                .unlockedBy("has_" + collection.name() + "_pickaxe", has(collection.pickaxe().get()))
                .unlockedBy("has_" + collection.name() + "_shovel", has(collection.shovel().get()))
                .unlockedBy("has_" + collection.name() + "_axe", has(collection.axe().get()))
                .unlockedBy("has_" + collection.name() + "_hoe", has(collection.hoe().get()))
                .unlockedBy("has_" + collection.name() + "_sword", has(collection.sword().get()))
                .unlockedBy("has_" + collection.name() + "_helmet", has(collection.helmet().get()))
                .unlockedBy("has_" + collection.name() + "_chestplate", has(collection.chestplate().get()))
                .unlockedBy("has_" + collection.name() + "_leggings", has(collection.leggings().get()))
                .unlockedBy("has_" + collection.name() + "_boots", has(collection.boots().get()))
//                .unlockedBy("has_" + collection.getName() + "_horse_armor", has(Items.GOLDEN_HORSE_ARMOR))
                .save(recipeSaver, getBlastingRecipeName(collection.nugget().get()));
    }




}
