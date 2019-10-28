package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ObjectHolder;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.*;
import oomitchoo.gaymercraft.reference.Reference;

public class ModBlocks {

    // Rainbow Soul Sand and Rainbow Magma Block (for bubbles in colored water)
    public static final Block RAINBOW_SOUL_SAND = new RainbowSoulSandBlock(Block.Properties.create(Material.SAND, MaterialColor.BROWN).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.SAND)).setRegistryName(Reference.MOD_ID, "rainbow_soul_sand");
    public static final Block RAINBOW_MAGMA_BLOCK = new RainbowMagmaBlock(Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).lightValue(3).tickRandomly().hardnessAndResistance(0.5F)).setRegistryName(Reference.MOD_ID, "rainbow_magma_block");

    // Bubble Blocks (for water elevators)
    private static final Block.Properties bubbleProperties = Block.Properties.create(Material.BUBBLE_COLUMN).doesNotBlockMovement().noDrops();
    public static final Block RAINBOW_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.RAINBOW_WATER, new ResourceLocation(Reference.MOD_ID, "rainbow_water_block")).setRegistryName(Reference.MOD_ID, "rainbow_bubble_column");
    public static final Block WHITE_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.WHITE_WATER, new ResourceLocation(Reference.MOD_ID, "white_water_block")).setRegistryName(Reference.MOD_ID, "white_bubble_column");
    public static final Block ORANGE_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.ORANGE_WATER, new ResourceLocation(Reference.MOD_ID, "orange_water_block")).setRegistryName(Reference.MOD_ID, "orange_bubble_column");
    public static final Block MAGENTA_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.MAGENTA_WATER, new ResourceLocation(Reference.MOD_ID, "magenta_water_block")).setRegistryName(Reference.MOD_ID, "magenta_bubble_column");
    public static final Block LIGHT_BLUE_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.LIGHT_BLUE_WATER, new ResourceLocation(Reference.MOD_ID, "light_blue_water_block")).setRegistryName(Reference.MOD_ID, "light_blue_bubble_column");
    public static final Block YELLOW_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.YELLOW_WATER, new ResourceLocation(Reference.MOD_ID, "yellow_water_block")).setRegistryName(Reference.MOD_ID, "yellow_bubble_column");
    public static final Block LIME_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.LIME_WATER, new ResourceLocation(Reference.MOD_ID, "lime_water_block")).setRegistryName(Reference.MOD_ID, "lime_bubble_column");
    public static final Block PINK_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.PINK_WATER, new ResourceLocation(Reference.MOD_ID, "pink_water_block")).setRegistryName(Reference.MOD_ID, "pink_bubble_column");
    public static final Block GRAY_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.GRAY_WATER, new ResourceLocation(Reference.MOD_ID, "gray_water_block")).setRegistryName(Reference.MOD_ID, "gray_bubble_column");
    public static final Block LIGHT_GRAY_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.LIGHT_GRAY_WATER, new ResourceLocation(Reference.MOD_ID, "light_gray_water_block")).setRegistryName(Reference.MOD_ID, "light_gray_bubble_column");
    public static final Block CYAN_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.CYAN_WATER, new ResourceLocation(Reference.MOD_ID, "cyan_water_block")).setRegistryName(Reference.MOD_ID, "cyan_bubble_column");
    public static final Block PURPLE_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.PURPLE_WATER, new ResourceLocation(Reference.MOD_ID, "purple_water_block")).setRegistryName(Reference.MOD_ID, "purple_bubble_column");
    public static final Block BLUE_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.BLUE_WATER, new ResourceLocation(Reference.MOD_ID, "blue_water_block")).setRegistryName(Reference.MOD_ID, "blue_bubble_column");
    public static final Block BROWN_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.BROWN_WATER, new ResourceLocation(Reference.MOD_ID, "brown_water_block")).setRegistryName(Reference.MOD_ID, "brown_bubble_column");
    public static final Block GREEN_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.GREEN_WATER, new ResourceLocation(Reference.MOD_ID, "green_water_block")).setRegistryName(Reference.MOD_ID, "green_bubble_column");
    public static final Block RED_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.RED_WATER, new ResourceLocation(Reference.MOD_ID, "red_water_block")).setRegistryName(Reference.MOD_ID, "red_bubble_column");
    public static final Block BLACK_BUBBLE_COLUMN = new ColoredBubbleColumnBlock(bubbleProperties, ModFluids.BLACK_WATER, new ResourceLocation(Reference.MOD_ID, "black_water_block")).setRegistryName(Reference.MOD_ID, "black_bubble_column");

    // Water Blocks
    @ObjectHolder("gaymercraft:rainbow_water_block")
    public static final Block RAINBOW_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.RAINBOW_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) RAINBOW_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:white_water_block")
    public static final Block WHITE_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.WHITE_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) WHITE_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:orange_water_block")
    public static final Block ORANGE_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.ORANGE_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) ORANGE_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:magenta_water_block")
    public static final Block MAGENTA_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.MAGENTA_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) MAGENTA_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:light_blue_water_block")
    public static final Block LIGHT_BLUE_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.LIGHT_BLUE_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) LIGHT_BLUE_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:yellow_water_block")
    public static final Block YELLOW_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.YELLOW_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) YELLOW_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:lime_water_block")
    public static final Block LIME_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.LIME_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) LIME_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:pink_water_block")
    public static final Block PINK_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.PINK_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) PINK_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:gray_water_block")
    public static final Block GRAY_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.GRAY_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) GRAY_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:light_gray_water_block")
    public static final Block LIGHT_GRAY_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.LIGHT_GRAY_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) LIGHT_GRAY_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:cyan_water_block")
    public static final Block CYAN_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.CYAN_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) CYAN_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:purple_water_block")
    public static final Block PURPLE_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.PURPLE_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) PURPLE_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:blue_water_block")
    public static final Block BLUE_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.BLUE_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) BLUE_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:brown_water_block")
    public static final Block BROWN_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.BROWN_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) BROWN_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:green_water_block")
    public static final Block GREEN_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.GREEN_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) GREEN_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:red_water_block")
    public static final Block RED_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.RED_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) RED_BUBBLE_COLUMN);
    @ObjectHolder("gaymercraft:black_water_block")
    public static final Block BLACK_WATER_BLOCK = new ColoredFlowingFluidBlock(GaymerCraft.BLACK_WATER, GaymerCraft.waterBlockProperties, (ColoredBubbleColumnBlock) BLACK_BUBBLE_COLUMN);

    // Hedge Blocks
    public static final Block OAK_HEDGE = new HedgeBlock("oak_hedge");
    public static final Block SPRUCE_HEDGE = new HedgeBlock("spruce_hedge");
    public static final Block BIRCH_HEDGE = new HedgeBlock("birch_hedge");
    public static final Block JUNGLE_HEDGE = new HedgeBlock("jungle_hedge");
    public static final Block ACACIA_HEDGE = new HedgeBlock("acacia_hedge");
    public static final Block DARK_OAK_HEDGE = new HedgeBlock("dark_oak_hedge");

    // Vertical Slab Blocks
    public static final Block OAK_VERT_SLAB = new VertSlabBlock("oak_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block SPRUCE_VERT_SLAB = new VertSlabBlock("spruce_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block BIRCH_VERT_SLAB = new VertSlabBlock("birch_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block JUNGLE_VERT_SLAB = new VertSlabBlock("jungle_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block ACACIA_VERT_SLAB = new VertSlabBlock("acacia_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block DARK_OAK_VERT_SLAB = new VertSlabBlock("dark_oak_vert_slab", Block.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    public static final Block STONE_VERT_SLAB = new VertSlabBlock("stone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block SMOOTH_STONE_VERT_SLAB = new VertSlabBlock("smooth_stone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block SANDSTONE_VERT_SLAB = new VertSlabBlock("sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(2.0F, 6.0F));
    public static final Block CUT_SANDSTONE_VERT_SLAB = new VertSlabBlock("cut_sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(2.0F, 6.0F));
    public static final Block PETRIFIED_OAK_VERT_SLAB = new VertSlabBlock("petrified_oak_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.WOOD).hardnessAndResistance(2.0F, 6.0F));
    public static final Block COBBLESTONE_VERT_SLAB = new VertSlabBlock("cobblestone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block BRICK_VERT_SLAB = new VertSlabBlock("brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(2.0F, 6.0F));
    public static final Block STONE_BRICK_VERT_SLAB = new VertSlabBlock("stone_brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block NETHER_BRICK_VERT_SLAB = new VertSlabBlock("nether_brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(2.0F, 6.0F));
    public static final Block QUARTZ_VERT_SLAB = new VertSlabBlock("quartz_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F));
    public static final Block RED_SANDSTONE_VERT_SLAB = new VertSlabBlock("red_sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block CUT_RED_SANDSTONE_VERT_SLAB = new VertSlabBlock("cut_red_sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block PURPUR_VERT_SLAB = new VertSlabBlock("purpur_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.MAGENTA).hardnessAndResistance(2.0F, 6.0F));
    public static final Block PRISMARINE_VERT_SLAB = new VertSlabBlock("prismarine_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.CYAN).hardnessAndResistance(1.5F, 6.0F));
    public static final Block PRISMARINE_BRICK_VERT_SLAB = new VertSlabBlock("prismarine_brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.DIAMOND).hardnessAndResistance(1.5F, 6.0F));
    public static final Block DARK_PRISMARINE_VERT_SLAB = new VertSlabBlock("dark_prismarine_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.DIAMOND).hardnessAndResistance(1.5F, 6.0F));
    public static final Block POLISHED_GRANITE_VERT_SLAB = new VertSlabBlock("polished_granite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.DIRT).hardnessAndResistance(1.5F, 6.0F));
    public static final Block SMOOTH_RED_SANDSTONE_VERT_SLAB = new VertSlabBlock("smooth_red_sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 6.0F));
    public static final Block MOSSY_STONE_BRICK_VERT_SLAB = new VertSlabBlock("mossy_stone_brick_vert_slab", Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F));
    public static final Block POLISHED_DIORITE_VERT_SLAB = new VertSlabBlock("polished_diorite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(1.5F, 6.0F));
    public static final Block MOSSY_COBBLESTONE_VERT_SLAB = new VertSlabBlock("mossy_cobblestone_vert_slab", Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F));
    public static final Block END_STONE_BRICK_VERT_SLAB = new VertSlabBlock("end_stone_brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(0.8F));
    public static final Block SMOOTH_SANDSTONE_VERT_SLAB = new VertSlabBlock("smooth_sandstone_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(2.0F, 6.0F));
    public static final Block SMOOTH_QUARTZ_VERT_SLAB = new VertSlabBlock("smooth_quartz_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F));
    public static final Block GRANITE_VERT_SLAB = new VertSlabBlock("granite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.DIRT).hardnessAndResistance(1.5F, 6.0F));
    public static final Block ANDESITE_VERT_SLAB = new VertSlabBlock("andesite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F));
    public static final Block RED_NETHER_BRICK_VERT_SLAB = new VertSlabBlock("red_nether_brick_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).hardnessAndResistance(2.0F, 6.0F));
    public static final Block POLISHED_ANDESITE_VERT_SLAB = new VertSlabBlock("polished_andesite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.STONE).hardnessAndResistance(1.5F, 6.0F));
    public static final Block DIORITE_VERT_SLAB = new VertSlabBlock("diorite_vert_slab", Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(1.5F, 6.0F));
}
