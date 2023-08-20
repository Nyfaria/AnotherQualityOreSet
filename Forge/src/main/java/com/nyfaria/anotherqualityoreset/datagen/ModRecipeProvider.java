package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeSaver) {
        Stream.of(
                BlockInit.EASIUM_ORE,
                BlockInit.MEDIUM_ORE,
                BlockInit.HARDIUM_ORE
        ).forEach(collection->oreCollectionRecipes(collection, recipeSaver));

    }

    public void oreCollectionRecipes(OreCollection collection, Consumer<FinishedRecipe> recipeSaver) {
        ShapedRecipeBuilder.shaped(collection.getAxe().get())
                .pattern("XX ")
                .pattern("X# ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getHoe().get())
                .pattern("XX ")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getPickaxe().get())
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getShovel().get())
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getSword().get())
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .define('X', collection.getIngot().get())
                .define('#', Items.STICK)
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getHelmet().get())
                .pattern("XXX")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getChestplate().get())
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getLeggings().get())
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        ShapedRecipeBuilder.shaped(collection.getBoots().get())
                .pattern("X X")
                .pattern("X X")
                .define('X', collection.getIngot().get())
                .unlockedBy("has_item", has(collection.getIngot().get()))
                .save(recipeSaver);
        oreSmelting(recipeSaver, List.of(collection.getOre().get(), collection.getRawOre().get()), collection.getIngot().get(), 1.0f, 200, collection.getName());
        oreBlasting(recipeSaver, List.of(collection.getOre().get(), collection.getRawOre().get()), collection.getIngot().get(), 1.0f, 100, collection.getName());
        nineBlockStorageRecipesRecipesWithCustomUnpacking(recipeSaver, collection.getIngot().get(), collection.getBlock().get(), collection.getName() + "_ingot_from_" + collection.getName() + "_block", collection.getName() + "_ingot");
        nineBlockStorageRecipesWithCustomPacking(recipeSaver, collection.getNugget().get(), collection.getIngot().get(), collection.getName() + "_ingot_from_nuggets", collection.getName() + "_ingot");
        nineBlockStorageRecipes(recipeSaver, collection.getRawOre().get(), collection.getRawOreBlock().get());
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
                        ),
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
