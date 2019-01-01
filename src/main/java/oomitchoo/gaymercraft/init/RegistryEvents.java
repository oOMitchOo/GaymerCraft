package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.BlockColoredWater;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 30.12.2018.
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryEvents {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        // ITEMS
        r.register(ModItems.RAINBOW_STAR);
        // ITEMBLOCKS VERT SLABS
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_1);
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_2);
        r.register(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_1);
        r.register(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_2);
        r.register(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.ITEMBLOCK_PURPUR_VERT_SLAB);
        // ITEMBLOCKS HEDGE
        r.register(ModBlocks.ITEMBLOCK_HEDGE);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        // BLOCKS
        r.register(ModBlocks.STONE_VERT_SLAB_1);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_1);
        r.register(ModBlocks.STONE_VERT_SLAB_2);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_2);
        r.register(ModBlocks.WOOD_VERT_SLAB_1);
        r.register(ModBlocks.DOUBLE_WOOD_VERT_SLAB_1);
        r.register(ModBlocks.WOOD_VERT_SLAB_2);
        r.register(ModBlocks.DOUBLE_WOOD_VERT_SLAB_2);
        r.register(ModBlocks.STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.DOUBLE_STONE_VERT_SLAB_NEW);
        r.register(ModBlocks.PURPUR_VERT_SLAB);
        r.register(ModBlocks.DOUBLE_PURPUR_VERT_SLAB);

        // FLUID BLOCKS
        r.register(ModBlocks.BLOCK_WHITE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_WHITE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_WHITE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_ORANGE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_ORANGE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_ORANGE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_MAGENTA_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_MAGENTA_WATER, ((BlockColoredWater) ModBlocks.BLOCK_MAGENTA_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIGHT_BLUE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIGHT_BLUE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIGHT_BLUE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_YELLOW_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_YELLOW_WATER, ((BlockColoredWater) ModBlocks.BLOCK_YELLOW_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIME_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIME_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIME_WATER).getFluid());
        r.register(ModBlocks.BLOCK_PINK_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_PINK_WATER, ((BlockColoredWater) ModBlocks.BLOCK_PINK_WATER).getFluid());
        r.register(ModBlocks.BLOCK_GRAY_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_GRAY_WATER, ((BlockColoredWater) ModBlocks.BLOCK_GRAY_WATER).getFluid());
        r.register(ModBlocks.BLOCK_LIGHT_GRAY_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_LIGHT_GRAY_WATER, ((BlockColoredWater) ModBlocks.BLOCK_LIGHT_GRAY_WATER).getFluid());
        r.register(ModBlocks.BLOCK_CYAN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_CYAN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_CYAN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_PURPLE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_PURPLE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_PURPLE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BLUE_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BLUE_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BLUE_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BROWN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BROWN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BROWN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_GREEN_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_GREEN_WATER, ((BlockColoredWater) ModBlocks.BLOCK_GREEN_WATER).getFluid());
        r.register(ModBlocks.BLOCK_RED_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_RED_WATER, ((BlockColoredWater) ModBlocks.BLOCK_RED_WATER).getFluid());
        r.register(ModBlocks.BLOCK_BLACK_WATER);
        GaymerCraft.proxy.mapFluidState(ModBlocks.BLOCK_BLACK_WATER, ((BlockColoredWater) ModBlocks.BLOCK_BLACK_WATER).getFluid());

        // HEDGE BLOCK
        r.register(ModBlocks.BLOCK_HEDGE);
    }
}