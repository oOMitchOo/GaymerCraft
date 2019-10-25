package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.HedgeBlock;
import oomitchoo.gaymercraft.block.VertSlabBlock;

public class ModBlocks {
    private static Item.Properties properties = new Item.Properties().group(GaymerCraft.creativeTab.itemGroup);

    // Hedge Blocks with their BlockItems
    public static final Block OAK_HEDGE = new HedgeBlock("oak_hedge");
    public static final Block SPRUCE_HEDGE = new HedgeBlock("spruce_hedge");
    public static final Block BIRCH_HEDGE = new HedgeBlock("birch_hedge");
    public static final Block JUNGLE_HEDGE = new HedgeBlock("jungle_hedge");
    public static final Block ACACIA_HEDGE = new HedgeBlock("acacia_hedge");
    public static final Block DARK_OAK_HEDGE = new HedgeBlock("dark_oak_hedge");

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
