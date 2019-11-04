package oomitchoo.gaymercraft.init;

import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.registries.ObjectHolder;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.ModUtil;
import oomitchoo.gaymercraft.reference.Reference;

@ObjectHolder(Reference.MOD_ID)
public class ModItems {
    // Water Buckets
    public static final Item RAINBOW_WATER_BUCKET = ModUtil._null();
    public static final Item WHITE_WATER_BUCKET = ModUtil._null();
    public static final Item ORANGE_WATER_BUCKET = ModUtil._null();
    public static final Item MAGENTA_WATER_BUCKET = ModUtil._null();
    public static final Item LIGHT_BLUE_WATER_BUCKET = ModUtil._null();
    public static final Item YELLOW_WATER_BUCKET = ModUtil._null();
    public static final Item LIME_WATER_BUCKET = ModUtil._null();
    public static final Item PINK_WATER_BUCKET = ModUtil._null();
    public static final Item GRAY_WATER_BUCKET = ModUtil._null();
    public static final Item LIGHT_GRAY_WATER_BUCKET = ModUtil._null();
    public static final Item CYAN_WATER_BUCKET = ModUtil._null();
    public static final Item PURPLE_WATER_BUCKET = ModUtil._null();
    public static final Item BLUE_WATER_BUCKET = ModUtil._null();
    public static final Item BROWN_WATER_BUCKET = ModUtil._null();
    public static final Item GREEN_WATER_BUCKET = ModUtil._null();
    public static final Item RED_WATER_BUCKET = ModUtil._null();
    public static final Item BLACK_WATER_BUCKET = ModUtil._null();

    // TODO: Change this to being null again and register like everything else, when the SpawnEggItem works better (with suppliers???)
    // Spawn Egg
    public static final Item UNICORN_SPAWN_EGG = new SpawnEggItem(ModEntities.UNICORN, 0xFFE6FF, 0x9966FF, new Item.Properties().maxStackSize(1).maxDamage(0).group(GaymerCraft.creativeTab.itemGroup)).setRegistryName(Reference.MOD_ID, "unicorn_spawn_egg");

    // Rainbow Star Items
    public static final Item RAINBOW_STAR_CHARGED = ModUtil._null();
    public static final Item RAINBOW_STAR_UNCHARGED = ModUtil._null();

    // Hedge BlockItems
    public static final Item OAK_HEDGE = ModUtil._null();
    public static final Item SPRUCE_HEDGE = ModUtil._null();
    public static final Item BIRCH_HEDGE = ModUtil._null();
    public static final Item JUNGLE_HEDGE = ModUtil._null();
    public static final Item ACACIA_HEDGE = ModUtil._null();
    public static final Item DARK_OAK_HEDGE = ModUtil._null();

    // Vertical Slab BlockItems
    public static final Item OAK_VERT_SLAB = ModUtil._null();
    public static final Item SPRUCE_VERT_SLAB = ModUtil._null();
    public static final Item BIRCH_VERT_SLAB = ModUtil._null();
    public static final Item JUNGLE_VERT_SLAB = ModUtil._null();
    public static final Item ACACIA_VERT_SLAB = ModUtil._null();
    public static final Item DARK_OAK_VERT_SLAB = ModUtil._null();
    public static final Item STONE_VERT_SLAB = ModUtil._null();
    public static final Item SMOOTH_STONE_VERT_SLAB = ModUtil._null();
    public static final Item SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item CUT_SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item PETRIFIED_OAK_VERT_SLAB = ModUtil._null();
    public static final Item COBBLESTONE_VERT_SLAB = ModUtil._null();
    public static final Item BRICK_VERT_SLAB = ModUtil._null();
    public static final Item STONE_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item NETHER_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item QUARTZ_VERT_SLAB = ModUtil._null();
    public static final Item RED_SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item CUT_RED_SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item PURPUR_VERT_SLAB = ModUtil._null();
    public static final Item PRISMARINE_VERT_SLAB = ModUtil._null();
    public static final Item PRISMARINE_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item DARK_PRISMARINE_VERT_SLAB = ModUtil._null();
    public static final Item POLISHED_GRANITE_VERT_SLAB = ModUtil._null();
    public static final Item SMOOTH_RED_SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item MOSSY_STONE_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item POLISHED_DIORITE_VERT_SLAB = ModUtil._null();
    public static final Item MOSSY_COBBLESTONE_VERT_SLAB = ModUtil._null();
    public static final Item END_STONE_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item SMOOTH_SANDSTONE_VERT_SLAB = ModUtil._null();
    public static final Item SMOOTH_QUARTZ_VERT_SLAB = ModUtil._null();
    public static final Item GRANITE_VERT_SLAB = ModUtil._null();
    public static final Item ANDESITE_VERT_SLAB = ModUtil._null();
    public static final Item RED_NETHER_BRICK_VERT_SLAB = ModUtil._null();
    public static final Item POLISHED_ANDESITE_VERT_SLAB = ModUtil._null();
    public static final Item DIORITE_VERT_SLAB = ModUtil._null();

    // Rainbow Soul Sand and Rainbow Magma Block
    public static final Item RAINBOW_SOUL_SAND = ModUtil._null();
    public static final Item RAINBOW_MAGMA_BLOCK = ModUtil._null();

    // Rainbow underwater plants
    public static final Item RAINBOW_KELP = ModUtil._null();
    public static final Item RAINBOW_SEAGRASS = ModUtil._null();
    public static final Item RAINBOW_SEA_PICKLE = ModUtil._null();
    public static final Item RAINBOW_TUBE_CORAL = ModUtil._null();
    public static final Item RAINBOW_BRAIN_CORAL = ModUtil._null();
    public static final Item RAINBOW_BUBBLE_CORAL = ModUtil._null();
    public static final Item RAINBOW_FIRE_CORAL = ModUtil._null();
    public static final Item RAINBOW_HORN_CORAL = ModUtil._null();
    public static final Item RAINBOW_DEAD_TUBE_CORAL = ModUtil._null();
    public static final Item RAINBOW_DEAD_BRAIN_CORAL = ModUtil._null();
    public static final Item RAINBOW_DEAD_BUBBLE_CORAL = ModUtil._null();
    public static final Item RAINBOW_DEAD_FIRE_CORAL = ModUtil._null();
    public static final Item RAINBOW_DEAD_HORN_CORAL = ModUtil._null();
    public static final Item RAINBOW_TUBE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_BRAIN_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_BUBBLE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_FIRE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_HORN_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_DEAD_TUBE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_DEAD_BRAIN_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_DEAD_BUBBLE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_DEAD_FIRE_CORAL_FAN = ModUtil._null();
    public static final Item RAINBOW_DEAD_HORN_CORAL_FAN = ModUtil._null();
}
