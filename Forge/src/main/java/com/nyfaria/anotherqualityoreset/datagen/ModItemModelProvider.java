package com.nyfaria.anotherqualityoreset.datagen;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.init.BlockInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Stream;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Stream.of(
                BlockInit.EASIUM_ORE,
                BlockInit.MEDIUM_ORE,
                BlockInit.HARDIUM_ORE
        ).forEach(this::oreCollection);
    }

    public void oreCollection(OreCollection collection){
        simpleBlockItemModel(collection.getOre().get());
        simpleBlockItemModel(collection.getBlock().get());
        simpleGeneratedModel(collection.getIngot().get());
        simpleGeneratedModel(collection.getNugget().get());
        simpleGeneratedModel(collection.getRawOre().get());
        simpleBlockItemModel(collection.getRawOreBlock().get());
        simpleHandHeldModel(collection.getAxe().get());
        simpleHandHeldModel(collection.getHoe().get());
        simpleHandHeldModel(collection.getPickaxe().get());
        simpleHandHeldModel(collection.getShovel().get());
        simpleHandHeldModel(collection.getSword().get());
        simpleGeneratedModel(collection.getHelmet().get());
        simpleGeneratedModel(collection.getChestplate().get());
        simpleGeneratedModel(collection.getLeggings().get());
        simpleGeneratedModel(collection.getBoots().get());
    }

    protected ItemModelBuilder simpleBlockItemModel(Block block) {
        String name = getName(block);
        return withExistingParent(name, modLoc("block/" + name));
    }

    protected ItemModelBuilder simpleGeneratedModel(Item item) {
        return simpleModel(item, mcLoc("item/generated"));
    }

    protected ItemModelBuilder simpleHandHeldModel(Item item) {
        return simpleModel(item, mcLoc("item/handheld"));
    }

    protected ItemModelBuilder simpleModel(Item item, ResourceLocation parent) {
        String name = getName(item);
        return singleTexture(name, parent, "layer0", modLoc("item/" + name));
    }

    protected String getName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    protected String getName(Block item) {
        return ForgeRegistries.BLOCKS.getKey(item).getPath();
    }
}
