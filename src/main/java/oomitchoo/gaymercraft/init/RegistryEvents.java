package oomitchoo.gaymercraft.init;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.*;
import oomitchoo.gaymercraft.item.RainbowStarItem;
import oomitchoo.gaymercraft.item.SpecialBlockItem;
import oomitchoo.gaymercraft.item.SpecialWallOrFloorItem;
import oomitchoo.gaymercraft.item.crafting.NoContainerShapelessRecipe;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;

/**
 * The order in which RegistryEvent.Register events fire is alphabetically, with the exception that Block will always fire first,
 * and Item will always fire second, right after Block. After the Register<Block> event has fired, all ObjectHolder annotations are refreshed,
 * and after Register<Item> has fired they are refreshed again. They are refreshed for a third time after all of the other Register events have fired.
 *
 * 1. Blocks
 * 2. Items
 * 3. ... (alphabetically)
 */



@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        event.getRegistry().registerAll(
                // Bubbles (See GaymerCraft class)

                /* Water Blocks STILL IN GaymerCraft CLASS
                setup(new ColoredFlowingFluidBlock(GaymerCraft.RAINBOW_WATER, createWaterBlockProperties(MaterialColor.WATER), (ColoredBubbleColumnBlock) ModBlocks.RAINBOW_BUBBLE_COLUMN), "rainbow_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.WHITE_WATER, createWaterBlockProperties(MaterialColor.WHITE_TERRACOTTA), (ColoredBubbleColumnBlock) ModBlocks.WHITE_BUBBLE_COLUMN), "white_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.ORANGE_WATER, createWaterBlockProperties(MaterialColor.ORANGE_TERRACOTTA), (ColoredBubbleColumnBlock) ModBlocks.ORANGE_BUBBLE_COLUMN), "orange_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.MAGENTA_WATER, createWaterBlockProperties(MaterialColor.MAGENTA), (ColoredBubbleColumnBlock) ModBlocks.MAGENTA_BUBBLE_COLUMN), "magenta_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.LIGHT_BLUE_WATER, createWaterBlockProperties(MaterialColor.LIGHT_BLUE), (ColoredBubbleColumnBlock) ModBlocks.LIGHT_BLUE_BUBBLE_COLUMN), "light_blue_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.YELLOW_WATER, createWaterBlockProperties(MaterialColor.YELLOW), (ColoredBubbleColumnBlock) ModBlocks.YELLOW_BUBBLE_COLUMN), "yellow_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.LIME_WATER, createWaterBlockProperties(MaterialColor.LIME), (ColoredBubbleColumnBlock) ModBlocks.LIME_BUBBLE_COLUMN), "lime_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.PINK_WATER, createWaterBlockProperties(MaterialColor.PINK), (ColoredBubbleColumnBlock) ModBlocks.PINK_BUBBLE_COLUMN), "pink_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.GRAY_WATER, createWaterBlockProperties(MaterialColor.GRAY), (ColoredBubbleColumnBlock) ModBlocks.GRAY_BUBBLE_COLUMN), "gray_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.LIGHT_GRAY_WATER, createWaterBlockProperties(MaterialColor.LIGHT_GRAY), (ColoredBubbleColumnBlock) ModBlocks.LIGHT_GRAY_BUBBLE_COLUMN), "light_gray_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.CYAN_WATER, createWaterBlockProperties(MaterialColor.CYAN), (ColoredBubbleColumnBlock) ModBlocks.CYAN_BUBBLE_COLUMN), "cyan_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.PURPLE_WATER, createWaterBlockProperties(MaterialColor.PURPLE), (ColoredBubbleColumnBlock) ModBlocks.PURPLE_BUBBLE_COLUMN), "purple_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.BLUE_WATER, createWaterBlockProperties(MaterialColor.BLUE), (ColoredBubbleColumnBlock) ModBlocks.BLUE_BUBBLE_COLUMN), "blue_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.BROWN_WATER, createWaterBlockProperties(MaterialColor.BROWN), (ColoredBubbleColumnBlock) ModBlocks.BROWN_BUBBLE_COLUMN), "brown_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.GREEN_WATER, createWaterBlockProperties(MaterialColor.GREEN), (ColoredBubbleColumnBlock) ModBlocks.GREEN_BUBBLE_COLUMN), "green_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.RED_WATER, createWaterBlockProperties(MaterialColor.RED), (ColoredBubbleColumnBlock) ModBlocks.RED_BUBBLE_COLUMN), "red_water_block"),
                setup(new ColoredFlowingFluidBlock(GaymerCraft.BLACK_WATER, createWaterBlockProperties(MaterialColor.BLACK), (ColoredBubbleColumnBlock) ModBlocks.BLACK_BUBBLE_COLUMN), "black_water_block"),
                 */
                // Hedge Blocks
                setup(new HedgeBlock(), "oak_hedge"),
                setup(new HedgeBlock(), "spruce_hedge"),
                setup(new HedgeBlock(), "birch_hedge"),
                setup(new HedgeBlock(), "jungle_hedge"),
                setup(new HedgeBlock(), "acacia_hedge"),
                setup(new HedgeBlock(), "dark_oak_hedge"),
                // Vertical Slab Blocks
                setup(new VertSlabBlock(Block.Properties.from(Blocks.OAK_SLAB)), "oak_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SPRUCE_SLAB)), "spruce_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.BIRCH_SLAB)), "birch_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.JUNGLE_SLAB)), "jungle_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.ACACIA_SLAB)), "acacia_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.DARK_OAK_SLAB)), "dark_oak_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.STONE_SLAB)), "stone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SMOOTH_STONE_SLAB)), "smooth_stone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SANDSTONE_SLAB)), "sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.CUT_SANDSTONE_SLAB)), "cut_sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.PETRIFIED_OAK_SLAB)), "petrified_oak_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.COBBLESTONE_SLAB)), "cobblestone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.BRICK_SLAB)), "brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.STONE_BRICK_SLAB)), "stone_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.NETHER_BRICK_SLAB)), "nether_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.QUARTZ_SLAB)), "quartz_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.RED_SANDSTONE_SLAB)), "red_sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.CUT_RED_SANDSTONE_SLAB)), "cut_red_sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.PURPUR_SLAB)), "purpur_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.PRISMARINE_SLAB)), "prismarine_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.PRISMARINE_BRICK_SLAB)), "prismarine_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.DARK_PRISMARINE_SLAB)), "dark_prismarine_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.POLISHED_GRANITE_SLAB)), "polished_granite_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SMOOTH_RED_SANDSTONE_SLAB)), "smooth_red_sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.MOSSY_STONE_BRICK_SLAB)), "mossy_stone_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.POLISHED_DIORITE_SLAB)), "polished_diorite_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.MOSSY_COBBLESTONE_SLAB)), "mossy_cobblestone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.END_STONE_BRICK_SLAB)), "end_stone_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SMOOTH_SANDSTONE_SLAB)), "smooth_sandstone_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.SMOOTH_QUARTZ_SLAB)), "smooth_quartz_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.GRANITE_SLAB)), "granite_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.ANDESITE_SLAB)), "andesite_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.RED_NETHER_BRICK_SLAB)), "red_nether_brick_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.POLISHED_ANDESITE_SLAB)), "polished_andesite_vert_slab"),
                setup(new VertSlabBlock(Block.Properties.from(Blocks.DIORITE_SLAB)), "diorite_vert_slab"),
                // Rainbow Soul Sand, Rainbow Magma
                setup(new RainbowSoulSandBlock(Block.Properties.from(Blocks.SOUL_SAND)), "rainbow_soul_sand"),
                setup(new RainbowMagmaBlock(Block.Properties.from(Blocks.MAGMA_BLOCK)), "rainbow_magma_block"),
                // Rainbow underwater plants
                setup(new RainbowKelpTopBlock(Block.Properties.from(Blocks.KELP)), "rainbow_kelp"),
                setup(new RainbowKelpBlock((RainbowKelpTopBlock) ModBlocks.RAINBOW_KELP, Block.Properties.from(Blocks.KELP_PLANT)), "rainbow_kelp_plant"),
                setup(new RainbowSeaGrassBlock(Block.Properties.from(Blocks.SEAGRASS)), "rainbow_seagrass"),
                setup(new RainbowTallSeaGrassBlock(Block.Properties.from(Blocks.TALL_SEAGRASS)), "rainbow_tall_seagrass"),
                setup(new RainbowSeaPickleBlock(Block.Properties.from(Blocks.SEA_PICKLE)), "rainbow_sea_pickle")

                /*
                setup(new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_TUBE_CORAL_FAN)), "rainbow_dead_tube_coral_fan"),
                setup(new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_BRAIN_CORAL_FAN)), "rainbow_dead_brain_coral_fan"),
                setup(new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_BUBBLE_CORAL_FAN)), "rainbow_dead_bubble_coral_fan"),
                setup(new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_FIRE_CORAL_FAN)), "rainbow_dead_fire_coral_fan"),
                setup(new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_HORN_CORAL_FAN)), "rainbow_dead_horn_coral_fan"),

                setup(new RainbowCoralFinBlock(ModBlocks.RAINBOW_DEAD_TUBE_CORAL_FAN, Block.Properties.from(Blocks.TUBE_CORAL_FAN)), "rainbow_tube_coral_fan"),
                setup(new RainbowCoralFinBlock(ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_FAN, Block.Properties.from(Blocks.BRAIN_CORAL_FAN)), "rainbow_brain_coral_fan"),
                setup(new RainbowCoralFinBlock(ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_FAN, Block.Properties.from(Blocks.BUBBLE_CORAL_FAN)), "rainbow_bubble_coral_fan"),
                setup(new RainbowCoralFinBlock(ModBlocks.RAINBOW_DEAD_FIRE_CORAL_FAN, Block.Properties.from(Blocks.FIRE_CORAL_FAN)), "rainbow_fire_coral_fan"),
                setup(new RainbowCoralFinBlock(ModBlocks.RAINBOW_DEAD_HORN_CORAL_FAN, Block.Properties.from(Blocks.HORN_CORAL_FAN)), "rainbow_horn_coral_fan"),

                setup(new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F).lootFrom(ModBlocks.RAINBOW_DEAD_TUBE_CORAL_FAN)), "rainbow_dead_tube_coral_wall_fan"),
                setup(new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F).lootFrom(ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_FAN)), "rainbow_dead_brain_coral_wall_fan"),
                setup(new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F).lootFrom(ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_FAN)), "rainbow_dead_bubble_coral_wall_fan"),
                setup(new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F).lootFrom(ModBlocks.RAINBOW_DEAD_FIRE_CORAL_FAN)), "rainbow_dead_fire_coral_wall_fan"),
                setup(new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F).lootFrom(ModBlocks.RAINBOW_DEAD_HORN_CORAL_FAN)), "rainbow_dead_horn_coral_wall_fan"),

                setup(new RainbowCoralWallFanBlock(ModBlocks.RAINBOW_DEAD_TUBE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS).lootFrom(ModBlocks.RAINBOW_TUBE_CORAL_FAN)), "rainbow_tube_coral_wall_fan"),
                setup(new RainbowCoralWallFanBlock(ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.PINK).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS).lootFrom(ModBlocks.RAINBOW_BRAIN_CORAL_FAN)), "rainbow_brain_coral_wall_fan"),
                setup(new RainbowCoralWallFanBlock(ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.PURPLE).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS).lootFrom(ModBlocks.RAINBOW_BUBBLE_CORAL_FAN)), "rainbow_bubble_coral_wall_fan"),
                setup(new RainbowCoralWallFanBlock(ModBlocks.RAINBOW_DEAD_FIRE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS).lootFrom(ModBlocks.RAINBOW_FIRE_CORAL_FAN)), "rainbow_fire_coral_wall_fan"),
                setup(new RainbowCoralWallFanBlock(ModBlocks.RAINBOW_DEAD_HORN_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.YELLOW).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS).lootFrom(ModBlocks.RAINBOW_HORN_CORAL_FAN)), "rainbow_horn_coral_wall_fan")
                 */
        );
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
        Item.Properties plainItemProperties = new Item.Properties().group(GaymerCraft.creativeTab.itemGroup);

        event.getRegistry().registerAll(
                // Unicorn Spawn Egg
                ModItems.UNICORN_SPAWN_EGG,
                // Rainbow Star Item
                setup(new RainbowStarItem(true), "rainbow_star_charged"),
                setup(new RainbowStarItem(false), "rainbow_star_uncharged"),
                // Hedge BlockItems
                setup(new BlockItem(ModBlocks.OAK_HEDGE, plainItemProperties), "oak_hedge"),
                setup(new BlockItem(ModBlocks.SPRUCE_HEDGE, plainItemProperties), "spruce_hedge"),
                setup(new BlockItem(ModBlocks.BIRCH_HEDGE, plainItemProperties), "birch_hedge"),
                setup(new BlockItem(ModBlocks.JUNGLE_HEDGE, plainItemProperties), "jungle_hedge"),
                setup(new BlockItem(ModBlocks.ACACIA_HEDGE, plainItemProperties), "acacia_hedge"),
                setup(new BlockItem(ModBlocks.DARK_OAK_HEDGE, plainItemProperties), "dark_oak_hedge"),
                // Vertical Slab BlockItems
                setup(new BlockItem(ModBlocks.OAK_VERT_SLAB, plainItemProperties), "oak_vert_slab"),
                setup(new BlockItem(ModBlocks.SPRUCE_VERT_SLAB, plainItemProperties), "spruce_vert_slab"),
                setup(new BlockItem(ModBlocks.BIRCH_VERT_SLAB, plainItemProperties), "birch_vert_slab"),
                setup(new BlockItem(ModBlocks.JUNGLE_VERT_SLAB, plainItemProperties), "jungle_vert_slab"),
                setup(new BlockItem(ModBlocks.ACACIA_VERT_SLAB, plainItemProperties), "acacia_vert_slab"),
                setup(new BlockItem(ModBlocks.DARK_OAK_VERT_SLAB, plainItemProperties), "dark_oak_vert_slab"),
                setup(new BlockItem(ModBlocks.STONE_VERT_SLAB, plainItemProperties), "stone_vert_slab"),
                setup(new BlockItem(ModBlocks.SMOOTH_STONE_VERT_SLAB, plainItemProperties), "smooth_stone_vert_slab"),
                setup(new BlockItem(ModBlocks.SANDSTONE_VERT_SLAB, plainItemProperties), "sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.CUT_SANDSTONE_VERT_SLAB, plainItemProperties), "cut_sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.PETRIFIED_OAK_VERT_SLAB, plainItemProperties), "petrified_oak_vert_slab"),
                setup(new BlockItem(ModBlocks.COBBLESTONE_VERT_SLAB, plainItemProperties), "cobblestone_vert_slab"),
                setup(new BlockItem(ModBlocks.BRICK_VERT_SLAB, plainItemProperties), "brick_vert_slab"),
                setup(new BlockItem(ModBlocks.STONE_BRICK_VERT_SLAB, plainItemProperties), "stone_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.NETHER_BRICK_VERT_SLAB, plainItemProperties), "nether_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.QUARTZ_VERT_SLAB, plainItemProperties), "quartz_vert_slab"),
                setup(new BlockItem(ModBlocks.RED_SANDSTONE_VERT_SLAB, plainItemProperties), "red_sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.CUT_RED_SANDSTONE_VERT_SLAB, plainItemProperties), "cut_red_sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.PURPUR_VERT_SLAB, plainItemProperties), "purpur_vert_slab"),
                setup(new BlockItem(ModBlocks.PRISMARINE_VERT_SLAB, plainItemProperties), "prismarine_vert_slab"),
                setup(new BlockItem(ModBlocks.PRISMARINE_BRICK_VERT_SLAB, plainItemProperties), "prismarine_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.DARK_PRISMARINE_VERT_SLAB, plainItemProperties), "dark_prismarine_vert_slab"),
                setup(new BlockItem(ModBlocks.POLISHED_GRANITE_VERT_SLAB, plainItemProperties), "polished_granite_vert_slab"),
                setup(new BlockItem(ModBlocks.SMOOTH_RED_SANDSTONE_VERT_SLAB, plainItemProperties), "smooth_red_sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.MOSSY_STONE_BRICK_VERT_SLAB, plainItemProperties), "mossy_stone_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.POLISHED_DIORITE_VERT_SLAB, plainItemProperties), "polished_diorite_vert_slab"),
                setup(new BlockItem(ModBlocks.MOSSY_COBBLESTONE_VERT_SLAB, plainItemProperties), "mossy_cobblestone_vert_slab"),
                setup(new BlockItem(ModBlocks.END_STONE_BRICK_VERT_SLAB, plainItemProperties), "end_stone_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.SMOOTH_SANDSTONE_VERT_SLAB, plainItemProperties), "smooth_sandstone_vert_slab"),
                setup(new BlockItem(ModBlocks.SMOOTH_QUARTZ_VERT_SLAB, plainItemProperties), "smooth_quartz_vert_slab"),
                setup(new BlockItem(ModBlocks.GRANITE_VERT_SLAB, plainItemProperties), "granite_vert_slab"),
                setup(new BlockItem(ModBlocks.ANDESITE_VERT_SLAB, plainItemProperties), "andesite_vert_slab"),
                setup(new BlockItem(ModBlocks.RED_NETHER_BRICK_VERT_SLAB, plainItemProperties), "red_nether_brick_vert_slab"),
                setup(new BlockItem(ModBlocks.POLISHED_ANDESITE_VERT_SLAB, plainItemProperties), "polished_andesite_vert_slab"),
                setup(new BlockItem(ModBlocks.DIORITE_VERT_SLAB, plainItemProperties), "diorite_vert_slab"),
                // (for bubbles) Rainbow Soul Sand, Rainbow Magma
                setup(new BlockItem(ModBlocks.RAINBOW_SOUL_SAND, plainItemProperties), "rainbow_soul_sand"),
                setup(new BlockItem(ModBlocks.RAINBOW_MAGMA_BLOCK, plainItemProperties), "rainbow_magma_block"),
                // Rainbow underwater plants
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_KELP, plainItemProperties, true, "message.underwater.plant"), "rainbow_kelp"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_SEAGRASS, plainItemProperties, true, "message.underwater.plant"), "rainbow_seagrass"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_SEA_PICKLE, plainItemProperties, true, "message.underwater.plant"), "rainbow_sea_pickle"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_TUBE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_tube_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_BRAIN_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_brain_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_BUBBLE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_bubble_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_FIRE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_fire_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_HORN_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_horn_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_DEAD_TUBE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_tube_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_DEAD_BRAIN_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_brain_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_bubble_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_DEAD_FIRE_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_fire_coral"),
                setup(new SpecialBlockItem(ModBlocks.RAINBOW_DEAD_HORN_CORAL , plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_horn_coral"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_TUBE_CORAL_FAN, ModBlocks.RAINBOW_TUBE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_tube_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_BRAIN_CORAL_FAN, ModBlocks.RAINBOW_BRAIN_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_brain_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_BUBBLE_CORAL_FAN, ModBlocks.RAINBOW_BUBBLE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_bubble_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_FIRE_CORAL_FAN, ModBlocks.RAINBOW_FIRE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_fire_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_HORN_CORAL_FAN, ModBlocks.RAINBOW_HORN_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_horn_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_DEAD_TUBE_CORAL_FAN, ModBlocks.RAINBOW_DEAD_TUBE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_tube_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_FAN, ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_brain_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_FAN, ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_bubble_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_DEAD_FIRE_CORAL_FAN, ModBlocks.RAINBOW_DEAD_FIRE_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_fire_coral_fan"),
                setup(new SpecialWallOrFloorItem(ModBlocks.RAINBOW_DEAD_HORN_CORAL_FAN, ModBlocks.RAINBOW_DEAD_HORN_CORAL_WALL_FAN, plainItemProperties, true, "message.underwater.plant"), "rainbow_dead_horn_coral_fan")
        );
    }

    //Register Entities
    @SubscribeEvent
    public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(ModEntities.UNICORN.setRegistryName(Reference.MOD_ID,"unicorn"));
    }

    @SubscribeEvent
    public static void onRecipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        // Name says it all. This is used, so that the colored water recipes don't dupe buckets.
        event.getRegistry().register(setup(new NoContainerShapelessRecipe.Serializer(), "no_container_crafting_shapeless"));
        //event.getRegistry().register(new NoContainerShapelessRecipe.Serializer().setRegistryName(Reference.MOD_ID, "no_container_crafting_shapeless"));
    }

    //================ helping methods ==================

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(Reference.MOD_ID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}
