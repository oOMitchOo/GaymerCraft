package oomitchoo.gaymercraft.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.item.RainbowStarItem;
import oomitchoo.gaymercraft.reference.Reference;

public class ModItems {
    // (For now) Only used to put the BlockItems in the right creative tab.
    private static final Item.Properties plainItemProperties = new Item.Properties().group(GaymerCraft.creativeTab.itemGroup);

    // Spawn Egg
    public static final Item UNICORN_SPAWN_EGG = new SpawnEggItem(ModEntities.UNICORN, 0xFFE6FF, 0x9966FF, new Item.Properties().maxStackSize(1).maxDamage(0).group(GaymerCraft.creativeTab.itemGroup)).setRegistryName(Reference.MOD_ID, "unicorn_spawn_egg");

    // Rainbow Star Items
    public static final Item RAINBOW_STAR_CHARGED = new RainbowStarItem("rainbow_star_charged", true);
    public static final Item RAINBOW_STAR_UNCHARGED = new RainbowStarItem("rainbow_star_uncharged", false);

    // Hedge BlockItems
    public static final Item OAK_HEDGE_BOCKITEM = new BlockItem(ModBlocks.OAK_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "oak_hedge");
    public static final Item SPRUCE_HEDGE_BOCKITEM = new BlockItem(ModBlocks.SPRUCE_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "spruce_hedge");
    public static final Item BIRCH_HEDGE_BOCKITEM = new BlockItem(ModBlocks.BIRCH_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "birch_hedge");
    public static final Item JUNGLE_HEDGE_BOCKITEM = new BlockItem(ModBlocks.JUNGLE_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "jungle_hedge");
    public static final Item ACACIA_HEDGE_BOCKITEM = new BlockItem(ModBlocks.ACACIA_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "acacia_hedge");
    public static final Item DARK_OAK_HEDGE_BOCKITEM = new BlockItem(ModBlocks.DARK_OAK_HEDGE, plainItemProperties).setRegistryName(Reference.MOD_ID, "dark_oak_hedge");

    // Vertical Slab BlockItems
    public static final Item OAK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.OAK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "oak_vert_slab");
    public static final Item SPRUCE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SPRUCE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "spruce_vert_slab");
    public static final Item BIRCH_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.BIRCH_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "birch_vert_slab");
    public static final Item JUNGLE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.JUNGLE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "jungle_vert_slab");
    public static final Item ACACIA_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.ACACIA_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "acacia_vert_slab");
    public static final Item DARK_OAK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.DARK_OAK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "dark_oak_vert_slab");
    public static final Item STONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.STONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "stone_vert_slab");
    public static final Item SMOOTH_STONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SMOOTH_STONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "smooth_stone_vert_slab");
    public static final Item SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "sandstone_vert_slab");
    public static final Item CUT_SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.CUT_SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "cut_sandstone_vert_slab");
    public static final Item PETRIFIED_OAK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.PETRIFIED_OAK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "petrified_oak_vert_slab");
    public static final Item COBBLESTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.COBBLESTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "cobblestone_vert_slab");
    public static final Item BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "brick_vert_slab");
    public static final Item STONE_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.STONE_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "stone_brick_vert_slab");
    public static final Item NETHER_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.NETHER_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "nether_brick_vert_slab");
    public static final Item QUARTZ_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.QUARTZ_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "quartz_vert_slab");
    public static final Item RED_SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.RED_SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "red_sandstone_vert_slab");
    public static final Item CUT_RED_SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.CUT_RED_SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "cut_red_sandstone_vert_slab");
    public static final Item PURPUR_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.PURPUR_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "purpur_vert_slab");
    public static final Item PRISMARINE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.PRISMARINE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "prismarine_vert_slab");
    public static final Item PRISMARINE_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.PRISMARINE_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "prismarine_brick_vert_slab");
    public static final Item DARK_PRISMARINE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.DARK_PRISMARINE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "dark_prismarine_vert_slab");
    public static final Item POLISHED_GRANITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.POLISHED_GRANITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "polished_granite_vert_slab");
    public static final Item SMOOTH_RED_SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SMOOTH_RED_SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "smooth_red_sandstone_vert_slab");
    public static final Item MOSSY_STONE_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.MOSSY_STONE_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "mossy_stone_brick_vert_slab");
    public static final Item POLISHED_DIORITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.POLISHED_DIORITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "polished_diorite_vert_slab");
    public static final Item MOSSY_COBBLESTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.MOSSY_COBBLESTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "mossy_cobblestone_vert_slab");
    public static final Item END_STONE_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.END_STONE_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "end_stone_brick_vert_slab");
    public static final Item SMOOTH_SANDSTONE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SMOOTH_SANDSTONE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "smooth_sandstone_vert_slab");
    public static final Item SMOOTH_QUARTZ_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.SMOOTH_QUARTZ_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "smooth_quartz_vert_slab");
    public static final Item GRANITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.GRANITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "granite_vert_slab");
    public static final Item ANDESITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.ANDESITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "andesite_vert_slab");
    public static final Item RED_NETHER_BRICK_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.RED_NETHER_BRICK_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "red_nether_brick_vert_slab");
    public static final Item POLISHED_ANDESITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.POLISHED_ANDESITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "polished_andesite_vert_slab");
    public static final Item DIORITE_VERT_SLAB_BOCKITEM = new BlockItem(ModBlocks.DIORITE_VERT_SLAB, plainItemProperties).setRegistryName(Reference.MOD_ID, "diorite_vert_slab");
}
