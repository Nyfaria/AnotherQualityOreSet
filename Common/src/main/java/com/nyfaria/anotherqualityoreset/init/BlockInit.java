package com.nyfaria.anotherqualityoreset.init;

import com.nyfaria.anotherqualityoreset.Constants;
import com.nyfaria.anotherqualityoreset.api.AQOArmoMaterials;
import com.nyfaria.anotherqualityoreset.api.AQOToolTiers;
import com.nyfaria.anotherqualityoreset.api.OreCollection;
import com.nyfaria.anotherqualityoreset.registration.RegistrationProvider;
import com.nyfaria.anotherqualityoreset.registration.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {
    public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, Constants.MODID);
    public static final RegistrationProvider<BlockEntityType<?>> BLOCK_ENTITIES = RegistrationProvider.get(Registries.BLOCK_ENTITY_TYPE, Constants.MODID);

    public static final RegistryObject<DropExperienceBlock> DEEPSLATE_EASIUM_ORE = registerBlock("deepslate_easium_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)));
//    public static final RegistryObject<DropExperienceBlock> MEDIUM_ORE = registerBlock("medium_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE),UniformInt.of(3,5)));
//    public static final RegistryObject<DropExperienceBlock> HARDIUM_ORE = registerBlock("hardium_ore", ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS), UniformInt.of(6,9)));

    public static final OreCollection EASIUM_ORE = OreCollection.of(AQOArmoMaterials.EASIUM, AQOToolTiers.EASIUM);
    public static final OreCollection MEDIUM_ORE = OreCollection.of(AQOArmoMaterials.MEDIUM, AQOToolTiers.MEDIUM, () -> new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 3, 0));
    public static final OreCollection HARDIUM_ORE = OreCollection.of(AQOArmoMaterials.HARDIUM, AQOToolTiers.HARDIUM, ()-> new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 3, 0));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, b -> () -> new BlockItem(b.get(), ItemInit.getItemProperties()));
    }

    protected static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> item) {
        var reg = BLOCKS.register(name, block);
        ItemInit.ITEMS.register(name, () -> item.apply(reg).get());
        return reg;
    }

    public static void loadClass() {
    }
}
