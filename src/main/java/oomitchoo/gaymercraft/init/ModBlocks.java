package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.block.BlockColoredWater;
import oomitchoo.gaymercraft.block.doubleslabs.*;
import oomitchoo.gaymercraft.block.halfslabs.*;
import oomitchoo.gaymercraft.item.ItemBlockVertSlab;

/**
 * Created by oOMitchOo on 21.11.2018.
 */

public class ModBlocks {
    // BLOCKS
    public static final Block DOUBLE_STONE_VERT_SLAB_1 = new BlockDoubleStoneVertSlab1("stoneVerticalSlab1","double_stone_vertical_slab_1");
    public static final Block STONE_VERT_SLAB_1 = new BlockHalfStoneVertSlab1("stoneVerticalSlab1","stone_vertical_slab_1");

    public static final Block DOUBLE_STONE_VERT_SLAB_2 = new BlockDoubleStoneVertSlab2("stoneVerticalSlab2","double_stone_vertical_slab_2");
    public static final Block STONE_VERT_SLAB_2 = new BlockHalfStoneVertSlab2("stoneVerticalSlab2","stone_vertical_slab_2");

    public static final Block DOUBLE_WOOD_VERT_SLAB_1 = new BlockDoubleWoodVertSlab1("woodVerticalSlab1","double_wood_vertical_slab_1");
    public static final Block WOOD_VERT_SLAB_1 = new BlockHalfWoodVertSlab1("woodVerticalSlab1","wood_vertical_slab_1");

    public static final Block DOUBLE_WOOD_VERT_SLAB_2 = new BlockDoubleWoodVertSlab2("woodVerticalSlab2","double_wood_vertical_slab_2");
    public static final Block WOOD_VERT_SLAB_2 = new BlockHalfWoodVertSlab2("woodVerticalSlab2","wood_vertical_slab_2");

    public static final Block DOUBLE_STONE_VERT_SLAB_NEW = new BlockDoubleStoneVertSlabNew("stoneVerticalSlabNew","double_stone_vertical_slab_new");
    public static final Block STONE_VERT_SLAB_NEW = new BlockHalfStoneVertSlabNew("stoneVerticalSlabNew","stone_vertical_slab_new");

    public static final Block DOUBLE_PURPUR_VERT_SLAB = new BlockDoublePurpurVertSlab("purpurVerticalSlab","double_purpur_vertical_slab");
    public static final Block PURPUR_VERT_SLAB = new BlockHalfPurpurVertSlab("purpurVerticalSlab","purpur_vertical_slab");

    // FLUID BLOCKS
    public static final Block BLOCK_WHITE_WATER = new BlockColoredWater("whiteWater","white_water",ModFluids.FLUID_WHITE_WATER, Material.WATER);
    public static final Block BLOCK_ORANGE_WATER = new BlockColoredWater("orangeWater","orange_water",ModFluids.FLUID_ORANGE_WATER, Material.WATER);
    public static final Block BLOCK_MAGENTA_WATER = new BlockColoredWater("magentaWater","magenta_water",ModFluids.FLUID_MAGENTA_WATER, Material.WATER);
    public static final Block BLOCK_LIGHT_BLUE_WATER = new BlockColoredWater("lightBlueWater","light_blue_water",ModFluids.FLUID_LIGHT_BLUE_WATER, Material.WATER);
    public static final Block BLOCK_YELLOW_WATER = new BlockColoredWater("yellowWater","yellow_water",ModFluids.FLUID_YELLOW_WATER, Material.WATER);
    public static final Block BLOCK_LIME_WATER = new BlockColoredWater("limeWater","lime_water",ModFluids.FLUID_LIME_WATER, Material.WATER);
    public static final Block BLOCK_PINK_WATER = new BlockColoredWater("pinkWater","pink_water",ModFluids.FLUID_PINK_WATER, Material.WATER);
    public static final Block BLOCK_GRAY_WATER = new BlockColoredWater("grayWater","gray_water",ModFluids.FLUID_GRAY_WATER, Material.WATER);
    public static final Block BLOCK_LIGHT_GRAY_WATER = new BlockColoredWater("lightGrayWater","light_gray_water",ModFluids.FLUID_LIGHT_GRAY_WATER, Material.WATER);
    public static final Block BLOCK_CYAN_WATER = new BlockColoredWater("cyanWater","cyan_water",ModFluids.FLUID_CYAN_WATER, Material.WATER);
    public static final Block BLOCK_PURPLE_WATER = new BlockColoredWater("purpleWater","purple_water",ModFluids.FLUID_PURPLE_WATER, Material.WATER);
    public static final Block BLOCK_BLUE_WATER = new BlockColoredWater("blueWater","blue_water",ModFluids.FLUID_BLUE_WATER, Material.WATER);
    public static final Block BLOCK_BROWN_WATER = new BlockColoredWater("brownWater","brown_water",ModFluids.FLUID_BROWN_WATER, Material.WATER);
    public static final Block BLOCK_GREEN_WATER = new BlockColoredWater("greenWater", "green_water", ModFluids.FLUID_GREEN_WATER, Material.WATER);
    public static final Block BLOCK_RED_WATER = new BlockColoredWater("redWater","red_water",ModFluids.FLUID_RED_WATER, Material.WATER);
    public static final Block BLOCK_BLACK_WATER = new BlockColoredWater("blackWater","black_water",ModFluids.FLUID_BLACK_WATER, Material.WATER);

    //ITEMBLOCKS
    public static final ItemBlock ITEMBLOCK_STONE_VERT_SLAB_1 = new ItemBlockVertSlab((BlockVertSlabBase)STONE_VERT_SLAB_1, (BlockVertSlabBase)STONE_VERT_SLAB_1, (BlockVertSlabBase)DOUBLE_STONE_VERT_SLAB_1,"stoneVerticalSlab1","stone_vertical_slab_1");
    public static final ItemBlock ITEMBLOCK_STONE_VERT_SLAB_2 = new ItemBlockVertSlab((BlockVertSlabBase)STONE_VERT_SLAB_2, (BlockVertSlabBase)STONE_VERT_SLAB_2, (BlockVertSlabBase)DOUBLE_STONE_VERT_SLAB_2,"stoneVerticalSlab2","stone_vertical_slab_2");
    public static final ItemBlock ITEMBLOCK_WOOD_VERT_SLAB_1 = new ItemBlockVertSlab((BlockVertSlabBase)WOOD_VERT_SLAB_1, (BlockVertSlabBase)WOOD_VERT_SLAB_1, (BlockVertSlabBase)DOUBLE_WOOD_VERT_SLAB_1,"woodVerticalSlab1","wood_vertical_slab_1");
    public static final ItemBlock ITEMBLOCK_WOOD_VERT_SLAB_2 = new ItemBlockVertSlab((BlockVertSlabBase)WOOD_VERT_SLAB_2, (BlockVertSlabBase)WOOD_VERT_SLAB_2, (BlockVertSlabBase)DOUBLE_WOOD_VERT_SLAB_2,"woodVerticalSlab2","wood_vertical_slab_2");
    public static final ItemBlock ITEMBLOCK_STONE_VERT_SLAB_NEW = new ItemBlockVertSlab((BlockVertSlabBase)STONE_VERT_SLAB_NEW, (BlockVertSlabBase)STONE_VERT_SLAB_NEW, (BlockVertSlabBase)DOUBLE_STONE_VERT_SLAB_NEW,"stoneVerticalSlabNew","stone_vertical_slab_new");
    public static final ItemBlock ITEMBLOCK_PURPUR_VERT_SLAB = new ItemBlockVertSlab((BlockVertSlabBase)PURPUR_VERT_SLAB, (BlockVertSlabBase)PURPUR_VERT_SLAB, (BlockVertSlabBase)DOUBLE_PURPUR_VERT_SLAB,"purpurVerticalSlab","purpur_vertical_slab");
}
