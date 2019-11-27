package oomitchoo.gaymercraft.init;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.*;
import oomitchoo.gaymercraft.fluid.ColoredFlowingFluid;
import oomitchoo.gaymercraft.reference.Reference;

public class RegistryObjects {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Reference.MOD_ID);

    //#################################################################################################################
    //##                                               BLOCKS                                                        ##
    //#################################################################################################################

    // FLUID BLOCKS
    public static final RegistryObject<FlowingFluidBlock> RAINBOW_WATER_BLOCK = BLOCKS.register("rainbow_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.RAINBOW_WATER, createWaterBlockProperties(MaterialColor.WATER), RegistryObjects.RAINBOW_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> WHITE_WATER_BLOCK = BLOCKS.register("white_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.WHITE_WATER, createWaterBlockProperties(MaterialColor.WHITE_TERRACOTTA), RegistryObjects.WHITE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> ORANGE_WATER_BLOCK = BLOCKS.register("orange_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.ORANGE_WATER, createWaterBlockProperties(MaterialColor.ORANGE_TERRACOTTA), RegistryObjects.ORANGE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> MAGENTA_WATER_BLOCK = BLOCKS.register("magenta_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.MAGENTA_WATER, createWaterBlockProperties(MaterialColor.MAGENTA), RegistryObjects.MAGENTA_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("light_blue_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.LIGHT_BLUE_WATER, createWaterBlockProperties(MaterialColor.LIGHT_BLUE), RegistryObjects.LIGHT_BLUE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> YELLOW_WATER_BLOCK = BLOCKS.register("yellow_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.YELLOW_WATER, createWaterBlockProperties(MaterialColor.YELLOW), RegistryObjects.YELLOW_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIME_WATER_BLOCK = BLOCKS.register("lime_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.LIME_WATER, createWaterBlockProperties(MaterialColor.LIME), RegistryObjects.LIME_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> PINK_WATER_BLOCK = BLOCKS.register("pink_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.PINK_WATER, createWaterBlockProperties(MaterialColor.PINK), RegistryObjects.PINK_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> GRAY_WATER_BLOCK = BLOCKS.register("gray_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.GRAY_WATER, createWaterBlockProperties(MaterialColor.GRAY), RegistryObjects.GRAY_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("light_gray_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.LIGHT_GRAY_WATER, createWaterBlockProperties(MaterialColor.LIGHT_GRAY), RegistryObjects.LIGHT_GRAY_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> CYAN_WATER_BLOCK = BLOCKS.register("cyan_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.CYAN_WATER, createWaterBlockProperties(MaterialColor.CYAN), RegistryObjects.CYAN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> PURPLE_WATER_BLOCK = BLOCKS.register("purple_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.PURPLE_WATER, createWaterBlockProperties(MaterialColor.PURPLE), RegistryObjects.PURPLE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BLUE_WATER_BLOCK = BLOCKS.register("blue_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.BLUE_WATER, createWaterBlockProperties(MaterialColor.BLUE), RegistryObjects.BLUE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BROWN_WATER_BLOCK = BLOCKS.register("brown_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.BROWN_WATER, createWaterBlockProperties(MaterialColor.BROWN), RegistryObjects.BROWN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> GREEN_WATER_BLOCK = BLOCKS.register("green_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.GREEN_WATER, createWaterBlockProperties(MaterialColor.GREEN), RegistryObjects.GREEN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> RED_WATER_BLOCK = BLOCKS.register("red_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.RED_WATER, createWaterBlockProperties(MaterialColor.RED), RegistryObjects.RED_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BLACK_WATER_BLOCK = BLOCKS.register("black_water_block", () ->
            new ColoredFlowingFluidBlock(RegistryObjects.BLACK_WATER, createWaterBlockProperties(MaterialColor.BLACK), RegistryObjects.BLACK_BUBBLE_COLUMN)
    );
    // BUBBLE COLUMN BLOCKS
    public static final RegistryObject<ColoredBubbleColumnBlock> MODDED_VANILLA_BUBBLE_COLUMN = BLOCKS.register("modded_vanilla_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), null, null)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> RAINBOW_BUBBLE_COLUMN = BLOCKS.register("rainbow_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.RAINBOW_WATER, RAINBOW_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> WHITE_BUBBLE_COLUMN = BLOCKS.register("white_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.WHITE_WATER, WHITE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> ORANGE_BUBBLE_COLUMN = BLOCKS.register("orange_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.ORANGE_WATER, ORANGE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> MAGENTA_BUBBLE_COLUMN = BLOCKS.register("magenta_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.MAGENTA_WATER, MAGENTA_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIGHT_BLUE_BUBBLE_COLUMN = BLOCKS.register("light_blue_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> YELLOW_BUBBLE_COLUMN = BLOCKS.register("yellow_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.YELLOW_WATER, YELLOW_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIME_BUBBLE_COLUMN = BLOCKS.register("lime_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.LIME_WATER, LIME_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> PINK_BUBBLE_COLUMN = BLOCKS.register("pink_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.PINK_WATER, PINK_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> GRAY_BUBBLE_COLUMN = BLOCKS.register("gray_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.GRAY_WATER, GRAY_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIGHT_GRAY_BUBBLE_COLUMN = BLOCKS.register("light_gray_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> CYAN_BUBBLE_COLUMN = BLOCKS.register("cyan_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.CYAN_WATER, CYAN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> PURPLE_BUBBLE_COLUMN = BLOCKS.register("purple_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.PURPLE_WATER, PURPLE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BLUE_BUBBLE_COLUMN = BLOCKS.register("blue_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.BLUE_WATER, BLUE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BROWN_BUBBLE_COLUMN = BLOCKS.register("brown_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.BROWN_WATER, BROWN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> GREEN_BUBBLE_COLUMN = BLOCKS.register("green_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.GREEN_WATER, GREEN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> RED_BUBBLE_COLUMN = BLOCKS.register("red_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.RED_WATER, RED_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BLACK_BUBBLE_COLUMN = BLOCKS.register("black_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RegistryObjects.BLACK_WATER, BLACK_WATER_BLOCK)
    );
    // DEAD CORALS
    public static final RegistryObject<RainbowDeadCoralPlantBlock> RAINBOW_DEAD_TUBE_CORAL = BLOCKS.register("rainbow_dead_tube_coral", () ->
            new RainbowDeadCoralPlantBlock(Block.Properties.from(Blocks.DEAD_TUBE_CORAL))
    );
    public static final RegistryObject<RainbowDeadCoralPlantBlock> RAINBOW_DEAD_BRAIN_CORAL = BLOCKS.register("rainbow_dead_brain_coral", () ->
            new RainbowDeadCoralPlantBlock(Block.Properties.from(Blocks.DEAD_BRAIN_CORAL))
    );
    public static final RegistryObject<RainbowDeadCoralPlantBlock> RAINBOW_DEAD_BUBBLE_CORAL = BLOCKS.register("rainbow_dead_bubble_coral", () ->
            new RainbowDeadCoralPlantBlock(Block.Properties.from(Blocks.DEAD_BUBBLE_CORAL))
    );
    public static final RegistryObject<RainbowDeadCoralPlantBlock> RAINBOW_DEAD_FIRE_CORAL = BLOCKS.register("rainbow_dead_fire_coral", () ->
            new RainbowDeadCoralPlantBlock(Block.Properties.from(Blocks.DEAD_FIRE_CORAL))
    );
    public static final RegistryObject<RainbowDeadCoralPlantBlock> RAINBOW_DEAD_HORN_CORAL = BLOCKS.register("rainbow_dead_horn_coral", () ->
            new RainbowDeadCoralPlantBlock(Block.Properties.from(Blocks.DEAD_HORN_CORAL))
    );
    // CORALS
    public static final RegistryObject<RainbowCoralPlantBlock> RAINBOW_TUBE_CORAL = BLOCKS.register("rainbow_tube_coral", () ->
            new RainbowCoralPlantBlock(RAINBOW_DEAD_TUBE_CORAL, Block.Properties.from(Blocks.TUBE_CORAL))
    );
    public static final RegistryObject<RainbowCoralPlantBlock> RAINBOW_BRAIN_CORAL = BLOCKS.register("rainbow_brain_coral", () ->
            new RainbowCoralPlantBlock(RAINBOW_DEAD_BRAIN_CORAL, Block.Properties.from(Blocks.TUBE_CORAL))
    );
    public static final RegistryObject<RainbowCoralPlantBlock> RAINBOW_BUBBLE_CORAL = BLOCKS.register("rainbow_bubble_coral", () ->
            new RainbowCoralPlantBlock(RAINBOW_DEAD_BUBBLE_CORAL, Block.Properties.from(Blocks.TUBE_CORAL))
    );
    public static final RegistryObject<RainbowCoralPlantBlock> RAINBOW_FIRE_CORAL = BLOCKS.register("rainbow_fire_coral", () ->
            new RainbowCoralPlantBlock(RAINBOW_DEAD_FIRE_CORAL, Block.Properties.from(Blocks.TUBE_CORAL))
    );
    public static final RegistryObject<RainbowCoralPlantBlock> RAINBOW_HORN_CORAL = BLOCKS.register("rainbow_horn_coral", () ->
            new RainbowCoralPlantBlock(RAINBOW_DEAD_HORN_CORAL, Block.Properties.from(Blocks.TUBE_CORAL))
    );
    // DEAD CORAL FANS
    public static final RegistryObject<RainbowCoralFanBlock> RAINBOW_DEAD_TUBE_CORAL_FAN = BLOCKS.register("rainbow_dead_tube_coral_fan", () ->
            new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_TUBE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFanBlock> RAINBOW_DEAD_BRAIN_CORAL_FAN = BLOCKS.register("rainbow_dead_brain_coral_fan", () ->
            new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_BRAIN_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFanBlock> RAINBOW_DEAD_BUBBLE_CORAL_FAN = BLOCKS.register("rainbow_dead_bubble_coral_fan", () ->
            new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_BUBBLE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFanBlock> RAINBOW_DEAD_FIRE_CORAL_FAN = BLOCKS.register("rainbow_dead_fire_coral_fan", () ->
            new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_FIRE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFanBlock> RAINBOW_DEAD_HORN_CORAL_FAN = BLOCKS.register("rainbow_dead_horn_coral_fan", () ->
            new RainbowCoralFanBlock(Block.Properties.from(Blocks.DEAD_HORN_CORAL_FAN))
    );
    // CORAL FANS
    public static final RegistryObject<RainbowCoralFinBlock> RAINBOW_TUBE_CORAL_FAN = BLOCKS.register( "rainbow_tube_coral_fan", () ->
            new RainbowCoralFinBlock(RAINBOW_DEAD_TUBE_CORAL_FAN, Block.Properties.from(Blocks.TUBE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFinBlock> RAINBOW_BRAIN_CORAL_FAN = BLOCKS.register( "rainbow_brain_coral_fan", () ->
            new RainbowCoralFinBlock(RAINBOW_DEAD_BRAIN_CORAL_FAN, Block.Properties.from(Blocks.BRAIN_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFinBlock> RAINBOW_BUBBLE_CORAL_FAN = BLOCKS.register( "rainbow_bubble_coral_fan", () ->
            new RainbowCoralFinBlock(RAINBOW_DEAD_BUBBLE_CORAL_FAN, Block.Properties.from(Blocks.BUBBLE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFinBlock> RAINBOW_FIRE_CORAL_FAN = BLOCKS.register( "rainbow_fire_coral_fan", () ->
            new RainbowCoralFinBlock(RAINBOW_DEAD_FIRE_CORAL_FAN, Block.Properties.from(Blocks.FIRE_CORAL_FAN))
    );
    public static final RegistryObject<RainbowCoralFinBlock> RAINBOW_HORN_CORAL_FAN = BLOCKS.register( "rainbow_horn_coral_fan", () ->
            new RainbowCoralFinBlock(RAINBOW_DEAD_HORN_CORAL_FAN, Block.Properties.from(Blocks.HORN_CORAL_FAN))
    );
    // DEAD CORAL WALL FANS
    public static final RegistryObject<RainbowDeadCoralWallFanBlock> RAINBOW_DEAD_TUBE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_dead_tube_coral_wall_fan", () ->
            new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F))
    );
    public static final RegistryObject<RainbowDeadCoralWallFanBlock> RAINBOW_DEAD_BRAIN_CORAL_WALL_FAN = BLOCKS.register( "rainbow_dead_brain_coral_wall_fan", () ->
            new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F))
    );
    public static final RegistryObject<RainbowDeadCoralWallFanBlock> RAINBOW_DEAD_BUBBLE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_dead_bubble_coral_wall_fan", () ->
            new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F))
    );
    public static final RegistryObject<RainbowDeadCoralWallFanBlock> RAINBOW_DEAD_FIRE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_dead_fire_coral_wall_fan", () ->
            new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F))
    );
    public static final RegistryObject<RainbowDeadCoralWallFanBlock> RAINBOW_DEAD_HORN_CORAL_WALL_FAN = BLOCKS.register( "rainbow_dead_horn_coral_wall_fan", () ->
            new RainbowDeadCoralWallFanBlock(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).doesNotBlockMovement().hardnessAndResistance(0.0F))
    );
    // CORAL WALL FANS
    public static final RegistryObject<RainbowCoralWallFanBlock> RAINBOW_TUBE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_tube_coral_wall_fan", () ->
            new RainbowCoralWallFanBlock(RAINBOW_DEAD_TUBE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS))
    );
    public static final RegistryObject<RainbowCoralWallFanBlock> RAINBOW_BRAIN_CORAL_WALL_FAN = BLOCKS.register( "rainbow_brain_coral_wall_fan", () ->
            new RainbowCoralWallFanBlock(RAINBOW_DEAD_BRAIN_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.PINK).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS))
    );
    public static final RegistryObject<RainbowCoralWallFanBlock> RAINBOW_BUBBLE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_bubble_coral_wall_fan", () ->
            new RainbowCoralWallFanBlock(RAINBOW_DEAD_BUBBLE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.PURPLE).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS))
    );
    public static final RegistryObject<RainbowCoralWallFanBlock> RAINBOW_FIRE_CORAL_WALL_FAN = BLOCKS.register( "rainbow_fire_coral_wall_fan", () ->
            new RainbowCoralWallFanBlock(RAINBOW_DEAD_FIRE_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS))
    );
    public static final RegistryObject<RainbowCoralWallFanBlock> RAINBOW_HORN_CORAL_WALL_FAN = BLOCKS.register( "rainbow_horn_coral_wall_fan", () ->
            new RainbowCoralWallFanBlock(RAINBOW_DEAD_HORN_CORAL_WALL_FAN, Block.Properties.create(Material.OCEAN_PLANT, MaterialColor.YELLOW).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.WET_GRASS))
    );
    // KELP BLOCK AND KELP TOP BLOCK
    public static final RegistryObject<RainbowKelpTopBlock> RAINBOW_KELP = BLOCKS.register( "rainbow_kelp", () ->
            new RainbowKelpTopBlock(Block.Properties.from(Blocks.KELP))
    );
    public static final RegistryObject<RainbowKelpBlock> RAINBOW_KELP_PLANT = BLOCKS.register( "rainbow_kelp_plant", () ->
            new RainbowKelpBlock(RAINBOW_KELP, Block.Properties.from(Blocks.KELP_PLANT))
    );

    //#################################################################################################################
    //##                                               ITEMS                                                         ##
    //#################################################################################################################

    // BUCKETS FOR THE FLUIDS
    public static final Item.Properties waterBucketItemProperties = new Item.Properties().containerItem(Items.BUCKET)
            .maxStackSize(1).group(GaymerCraft.creativeTab.itemGroup);
    public static final RegistryObject<Item> RAINBOW_WATER_BUCKET = ITEMS.register("rainbow_water_bucket", () ->
            new BucketItem(RegistryObjects.RAINBOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> WHITE_WATER_BUCKET = ITEMS.register("white_water_bucket", () ->
            new BucketItem(RegistryObjects.WHITE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> ORANGE_WATER_BUCKET = ITEMS.register("orange_water_bucket", () ->
            new BucketItem(RegistryObjects.ORANGE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> MAGENTA_WATER_BUCKET = ITEMS.register("magenta_water_bucket", () ->
            new BucketItem(RegistryObjects.MAGENTA_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_BLUE_WATER_BUCKET = ITEMS.register("light_blue_water_bucket", () ->
            new BucketItem(RegistryObjects.LIGHT_BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> YELLOW_WATER_BUCKET = ITEMS.register("yellow_water_bucket", () ->
            new BucketItem(RegistryObjects.YELLOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIME_WATER_BUCKET = ITEMS.register("lime_water_bucket", () ->
            new BucketItem(RegistryObjects.LIME_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PINK_WATER_BUCKET = ITEMS.register("pink_water_bucket", () ->
            new BucketItem(RegistryObjects.PINK_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GRAY_WATER_BUCKET = ITEMS.register("gray_water_bucket", () ->
            new BucketItem(RegistryObjects.GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_GRAY_WATER_BUCKET = ITEMS.register("light_gray_water_bucket", () ->
            new BucketItem(RegistryObjects.LIGHT_GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> CYAN_WATER_BUCKET = ITEMS.register("cyan_water_bucket", () ->
            new BucketItem(RegistryObjects.CYAN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PURPLE_WATER_BUCKET = ITEMS.register("purple_water_bucket", () ->
            new BucketItem(RegistryObjects.PURPLE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLUE_WATER_BUCKET = ITEMS.register("blue_water_bucket", () ->
            new BucketItem(RegistryObjects.BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BROWN_WATER_BUCKET = ITEMS.register("brown_water_bucket", () ->
            new BucketItem(RegistryObjects.BROWN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GREEN_WATER_BUCKET = ITEMS.register("green_water_bucket", () ->
            new BucketItem(RegistryObjects.GREEN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> RED_WATER_BUCKET = ITEMS.register("red_water_bucket", () ->
            new BucketItem(RegistryObjects.RED_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLACK_WATER_BUCKET = ITEMS.register("black_water_bucket", () ->
            new BucketItem(RegistryObjects.BLACK_WATER, waterBucketItemProperties)
    );

    //#################################################################################################################
    //##                                               FLUIDS                                                        ##
    //#################################################################################################################

    // FLUID SOURCES
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER = FLUIDS.register("rainbow_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER = FLUIDS.register("white_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER = FLUIDS.register("orange_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER = FLUIDS.register("magenta_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER = FLUIDS.register("light_blue_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER = FLUIDS.register("yellow_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER = FLUIDS.register("lime_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER = FLUIDS.register("pink_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER = FLUIDS.register("gray_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER = FLUIDS.register("light_gray_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER = FLUIDS.register("cyan_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER = FLUIDS.register("purple_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER = FLUIDS.register("blue_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER = FLUIDS.register("brown_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER = FLUIDS.register("green_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER = FLUIDS.register("red_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER = FLUIDS.register("black_water", () ->
            new ColoredFlowingFluid.Source(RegistryObjects.BLACK_WATER_PROPERTIES)
    );
    // FLUIDS FLOWING
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER_FLOWING = FLUIDS.register("rainbow_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER_FLOWING = FLUIDS.register("white_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER_FLOWING = FLUIDS.register("orange_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER_FLOWING = FLUIDS.register("magenta_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER_FLOWING = FLUIDS.register("light_blue_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER_FLOWING = FLUIDS.register("yellow_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER_FLOWING = FLUIDS.register("lime_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER_FLOWING = FLUIDS.register("pink_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER_FLOWING = FLUIDS.register("gray_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER_FLOWING = FLUIDS.register("light_gray_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER_FLOWING = FLUIDS.register("cyan_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER_FLOWING = FLUIDS.register("purple_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER_FLOWING = FLUIDS.register("blue_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER_FLOWING = FLUIDS.register("brown_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER_FLOWING = FLUIDS.register("green_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER_FLOWING = FLUIDS.register("red_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER_FLOWING = FLUIDS.register("black_water_flowing", () ->
            new ColoredFlowingFluid.Flowing(RegistryObjects.BLACK_WATER_PROPERTIES)
    );
    // FLUID PROPERTIES
    public static final ResourceLocation waterStillTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_still");
    public static final ResourceLocation waterFlowingTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_flow");
    public static final ResourceLocation waterOverlayTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_overlay");
    public static final ResourceLocation rainbowWaterStillTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_rainbow_water_still");
    public static final ResourceLocation rainbowWaterFlowingTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_rainbow_water_flow");
    public static final ResourceLocation rainbowWaterOverlayTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_rainbow_water_overlay");
    public static final ForgeFlowingFluid.Properties RAINBOW_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(RAINBOW_WATER, RAINBOW_WATER_FLOWING, FluidAttributes.builder(rainbowWaterStillTexture, rainbowWaterFlowingTexture).overlay(rainbowWaterOverlayTexture)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(RAINBOW_WATER_BUCKET).block(RAINBOW_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties WHITE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(WHITE_WATER, WHITE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFF0F0F0)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(WHITE_WATER_BUCKET).block(WHITE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties ORANGE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(ORANGE_WATER, ORANGE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFEB8844)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(ORANGE_WATER_BUCKET).block(ORANGE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties MAGENTA_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(MAGENTA_WATER, MAGENTA_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFC354CD)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(MAGENTA_WATER_BUCKET).block(MAGENTA_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIGHT_BLUE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF6689D3)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIGHT_BLUE_WATER_BUCKET).block(LIGHT_BLUE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties YELLOW_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(YELLOW_WATER, YELLOW_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFDECF2A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(YELLOW_WATER_BUCKET).block(YELLOW_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIME_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(LIME_WATER, LIME_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF41CD34)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIME_WATER_BUCKET).block(LIME_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties PINK_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(PINK_WATER, PINK_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFD88198)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(PINK_WATER_BUCKET).block(PINK_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties GRAY_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(GRAY_WATER, GRAY_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF434343)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(GRAY_WATER_BUCKET).block(GRAY_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIGHT_GRAY_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFABABAB)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIGHT_GRAY_WATER_BUCKET).block(LIGHT_GRAY_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties CYAN_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(CYAN_WATER, CYAN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF287697)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(CYAN_WATER_BUCKET).block(CYAN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties PURPLE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(PURPLE_WATER, PURPLE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF7B2FBE)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(PURPLE_WATER_BUCKET).block(PURPLE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BLUE_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(BLUE_WATER, BLUE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF253192)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BLUE_WATER_BUCKET).block(BLUE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BROWN_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(BROWN_WATER, BROWN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF51301A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BROWN_WATER_BUCKET).block(BROWN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties GREEN_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(GREEN_WATER, GREEN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF3B511A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(GREEN_WATER_BUCKET).block(GREEN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties RED_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(RED_WATER, RED_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFB3312C)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(RED_WATER_BUCKET).block(RED_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BLACK_WATER_PROPERTIES =
            new ForgeFlowingFluid.Properties(BLACK_WATER, BLACK_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF1E1B1B)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BLACK_WATER_BUCKET).block(BLACK_WATER_BLOCK).canMultiply();

    //#################################################################################################################
    //##                                          HELPING METHODS                                                    ##
    //#################################################################################################################

    private static Block.Properties createWaterBlockProperties(MaterialColor color) {
        return Block.Properties.create(
                new Material.Builder(color).doesNotBlockMovement().notOpaque().notSolid().pushDestroys().replaceable().liquid().build()
        ).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops();
    }
}