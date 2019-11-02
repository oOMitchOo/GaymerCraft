package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import oomitchoo.gaymercraft.item.crafting.NoContainerShapelessRecipe;
import oomitchoo.gaymercraft.reference.Reference;

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
                // Bubbles
                ModBlocks.RAINBOW_BUBBLE_COLUMN,
                ModBlocks.WHITE_BUBBLE_COLUMN,
                ModBlocks.ORANGE_BUBBLE_COLUMN,
                ModBlocks.MAGENTA_BUBBLE_COLUMN,
                ModBlocks.LIGHT_BLUE_BUBBLE_COLUMN,
                ModBlocks.YELLOW_BUBBLE_COLUMN,
                ModBlocks.LIME_BUBBLE_COLUMN,
                ModBlocks.PINK_BUBBLE_COLUMN,
                ModBlocks.GRAY_BUBBLE_COLUMN,
                ModBlocks.LIGHT_GRAY_BUBBLE_COLUMN,
                ModBlocks.CYAN_BUBBLE_COLUMN,
                ModBlocks.PURPLE_BUBBLE_COLUMN,
                ModBlocks.BLUE_BUBBLE_COLUMN,
                ModBlocks.BROWN_BUBBLE_COLUMN,
                ModBlocks.GREEN_BUBBLE_COLUMN,
                ModBlocks.RED_BUBBLE_COLUMN,
                ModBlocks.BLACK_BUBBLE_COLUMN,
                // Hedge Blocks
                ModBlocks.OAK_HEDGE,
                ModBlocks.SPRUCE_HEDGE,
                ModBlocks.BIRCH_HEDGE,
                ModBlocks.JUNGLE_HEDGE,
                ModBlocks.ACACIA_HEDGE,
                ModBlocks.DARK_OAK_HEDGE,
                // Vertical Slab Blocks
                ModBlocks.OAK_VERT_SLAB,
                ModBlocks.SPRUCE_VERT_SLAB,
                ModBlocks.BIRCH_VERT_SLAB,
                ModBlocks.JUNGLE_VERT_SLAB,
                ModBlocks.ACACIA_VERT_SLAB,
                ModBlocks.DARK_OAK_VERT_SLAB,
                ModBlocks.STONE_VERT_SLAB,
                ModBlocks.SMOOTH_STONE_VERT_SLAB,
                ModBlocks.SANDSTONE_VERT_SLAB,
                ModBlocks.CUT_SANDSTONE_VERT_SLAB,
                ModBlocks.PETRIFIED_OAK_VERT_SLAB,
                ModBlocks.COBBLESTONE_VERT_SLAB,
                ModBlocks.BRICK_VERT_SLAB,
                ModBlocks.STONE_BRICK_VERT_SLAB,
                ModBlocks.NETHER_BRICK_VERT_SLAB,
                ModBlocks.QUARTZ_VERT_SLAB,
                ModBlocks.RED_SANDSTONE_VERT_SLAB,
                ModBlocks.CUT_RED_SANDSTONE_VERT_SLAB,
                ModBlocks.PURPUR_VERT_SLAB,
                ModBlocks.PRISMARINE_VERT_SLAB,
                ModBlocks.PRISMARINE_BRICK_VERT_SLAB,
                ModBlocks.DARK_PRISMARINE_VERT_SLAB,
                ModBlocks.POLISHED_GRANITE_VERT_SLAB,
                ModBlocks.SMOOTH_RED_SANDSTONE_VERT_SLAB,
                ModBlocks.MOSSY_STONE_BRICK_VERT_SLAB,
                ModBlocks.POLISHED_DIORITE_VERT_SLAB,
                ModBlocks.MOSSY_COBBLESTONE_VERT_SLAB,
                ModBlocks.END_STONE_BRICK_VERT_SLAB,
                ModBlocks.SMOOTH_SANDSTONE_VERT_SLAB,
                ModBlocks.SMOOTH_QUARTZ_VERT_SLAB,
                ModBlocks.GRANITE_VERT_SLAB,
                ModBlocks.ANDESITE_VERT_SLAB,
                ModBlocks.RED_NETHER_BRICK_VERT_SLAB,
                ModBlocks.POLISHED_ANDESITE_VERT_SLAB,
                ModBlocks.DIORITE_VERT_SLAB,
                // Rainbow Soul Sand, Rainbow Magma
                ModBlocks.RAINBOW_SOUL_SAND,
                ModBlocks.RAINBOW_MAGMA_BLOCK,
                // Rainbow underwater plants
                ModBlocks.RAINBOW_KELP,
                ModBlocks.RAINBOW_KELP_PLANT,
                ModBlocks.RAINBOW_SEAGRASS,
                ModBlocks.RAINBOW_TALL_SEAGRASS,
                ModBlocks.RAINBOW_SEA_PICKLE,
                ModBlocks.RAINBOW_TUBE_CORAL,
                ModBlocks.RAINBOW_BRAIN_CORAL,
                ModBlocks.RAINBOW_BUBBLE_CORAL,
                ModBlocks.RAINBOW_FIRE_CORAL,
                ModBlocks.RAINBOW_HORN_CORAL,
                ModBlocks.RAINBOW_DEAD_TUBE_CORAL,
                ModBlocks.RAINBOW_DEAD_BRAIN_CORAL,
                ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL,
                ModBlocks.RAINBOW_DEAD_FIRE_CORAL,
                ModBlocks.RAINBOW_DEAD_HORN_CORAL,
                ModBlocks.RAINBOW_TUBE_CORAL_FAN,
                ModBlocks.RAINBOW_BRAIN_CORAL_FAN,
                ModBlocks.RAINBOW_BUBBLE_CORAL_FAN,
                ModBlocks.RAINBOW_FIRE_CORAL_FAN,
                ModBlocks.RAINBOW_HORN_CORAL_FAN,
                ModBlocks.RAINBOW_DEAD_TUBE_CORAL_FAN,
                ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_FAN,
                ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_FAN,
                ModBlocks.RAINBOW_DEAD_FIRE_CORAL_FAN,
                ModBlocks.RAINBOW_DEAD_HORN_CORAL_FAN,
                ModBlocks.RAINBOW_TUBE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_BRAIN_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_BUBBLE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_FIRE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_HORN_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_DEAD_TUBE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_DEAD_BRAIN_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_DEAD_BUBBLE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_DEAD_FIRE_CORAL_WALL_FAN,
                ModBlocks.RAINBOW_DEAD_HORN_CORAL_WALL_FAN
        );
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                // Unicorn Spawn Egg
                ModItems.UNICORN_SPAWN_EGG,
                // Rainbow Star Item
                ModItems.RAINBOW_STAR_CHARGED,
                ModItems.RAINBOW_STAR_UNCHARGED,
                // Hedge BlockItems
                ModItems.OAK_HEDGE_BOCKITEM,
                ModItems.SPRUCE_HEDGE_BOCKITEM,
                ModItems.BIRCH_HEDGE_BOCKITEM,
                ModItems.JUNGLE_HEDGE_BOCKITEM,
                ModItems.ACACIA_HEDGE_BOCKITEM,
                ModItems.DARK_OAK_HEDGE_BOCKITEM,
                // Vertical Slab BlockItems
                ModItems.OAK_VERT_SLAB_BOCKITEM,
                ModItems.SPRUCE_VERT_SLAB_BOCKITEM,
                ModItems.BIRCH_VERT_SLAB_BOCKITEM,
                ModItems.JUNGLE_VERT_SLAB_BOCKITEM,
                ModItems.ACACIA_VERT_SLAB_BOCKITEM,
                ModItems.DARK_OAK_VERT_SLAB_BOCKITEM,
                ModItems.STONE_VERT_SLAB_BOCKITEM,
                ModItems.SMOOTH_STONE_VERT_SLAB_BOCKITEM,
                ModItems.SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.CUT_SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.PETRIFIED_OAK_VERT_SLAB_BOCKITEM,
                ModItems.COBBLESTONE_VERT_SLAB_BOCKITEM,
                ModItems.BRICK_VERT_SLAB_BOCKITEM,
                ModItems.STONE_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.NETHER_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.QUARTZ_VERT_SLAB_BOCKITEM,
                ModItems.RED_SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.CUT_RED_SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.PURPUR_VERT_SLAB_BOCKITEM,
                ModItems.PRISMARINE_VERT_SLAB_BOCKITEM,
                ModItems.PRISMARINE_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.DARK_PRISMARINE_VERT_SLAB_BOCKITEM,
                ModItems.POLISHED_GRANITE_VERT_SLAB_BOCKITEM,
                ModItems.SMOOTH_RED_SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.MOSSY_STONE_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.POLISHED_DIORITE_VERT_SLAB_BOCKITEM,
                ModItems.MOSSY_COBBLESTONE_VERT_SLAB_BOCKITEM,
                ModItems.END_STONE_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.SMOOTH_SANDSTONE_VERT_SLAB_BOCKITEM,
                ModItems.SMOOTH_QUARTZ_VERT_SLAB_BOCKITEM,
                ModItems.GRANITE_VERT_SLAB_BOCKITEM,
                ModItems.ANDESITE_VERT_SLAB_BOCKITEM,
                ModItems.RED_NETHER_BRICK_VERT_SLAB_BOCKITEM,
                ModItems.POLISHED_ANDESITE_VERT_SLAB_BOCKITEM,
                ModItems.DIORITE_VERT_SLAB_BOCKITEM,
                // (for bubbles) Rainbow Soul Sand, Rainbow Magma
                ModItems.RAINBOW_SOUL_SAND_BLOCKITEM,
                ModItems.RAINBOW_MAGMA_BLOCK_BLOCKITEM,
                // Rainbow underwater plants
                ModItems.RAINBOW_KELP_BLOCKITEM,
                ModItems.RAINBOW_SEAGRASS_BLOCKITEM,
                ModItems.RAINBOW_SEA_PICKLE_BLOCKITEM,
                ModItems.RAINBOW_TUBE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_BRAIN_CORAL_BLOCKITEM,
                ModItems.RAINBOW_BUBBLE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_FIRE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_HORN_CORAL_BLOCKITEM,
                ModItems.RAINBOW_DEAD_TUBE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_DEAD_BRAIN_CORAL_BLOCKITEM,
                ModItems.RAINBOW_DEAD_BUBBLE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_DEAD_FIRE_CORAL_BLOCKITEM,
                ModItems.RAINBOW_DEAD_HORN_CORAL_BLOCKITEM,
                ModItems.RAINBOW_TUBE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_BRAIN_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_BUBBLE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_FIRE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_HORN_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_DEAD_TUBE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_DEAD_BRAIN_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_DEAD_BUBBLE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_DEAD_FIRE_CORAL_FAN_BLOCKITEM,
                ModItems.RAINBOW_DEAD_HORN_CORAL_FAN_BLOCKITEM
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
        event.getRegistry().register(new NoContainerShapelessRecipe.Serializer().setRegistryName(Reference.MOD_ID, "no_container_crafting_shapeless"));
    }
}
