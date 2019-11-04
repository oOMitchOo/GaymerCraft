package oomitchoo.gaymercraft;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import oomitchoo.gaymercraft.block.*;
import oomitchoo.gaymercraft.config.Config;
import oomitchoo.gaymercraft.init.CreativeTab;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.proxy.ClientProxy;
import oomitchoo.gaymercraft.proxy.IProxy;
import oomitchoo.gaymercraft.proxy.ServerProxy;
import oomitchoo.gaymercraft.reference.Reference;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class GaymerCraft
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static CreativeTab creativeTab = new CreativeTab();

    // todo: ###### START ###### Fluid stuff (put it out of the main mod class...)
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Reference.MOD_ID);

    // FLUIDS (STILL?)
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER = FLUIDS.register("rainbow_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER = FLUIDS.register("white_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER = FLUIDS.register("orange_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER = FLUIDS.register("magenta_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER = FLUIDS.register("light_blue_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER = FLUIDS.register("yellow_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER = FLUIDS.register("lime_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER = FLUIDS.register("pink_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER = FLUIDS.register("gray_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER = FLUIDS.register("light_gray_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER = FLUIDS.register("cyan_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER = FLUIDS.register("purple_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER = FLUIDS.register("blue_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER = FLUIDS.register("brown_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER = FLUIDS.register("green_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER = FLUIDS.register("red_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER = FLUIDS.register("black_water", () ->
            new ForgeFlowingFluid.Source(GaymerCraft.BLACK_WATER_PROPERTIES)
    );

    // FLUIDS FLOWING
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER_FLOWING = FLUIDS.register("rainbow_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER_FLOWING = FLUIDS.register("white_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER_FLOWING = FLUIDS.register("orange_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER_FLOWING = FLUIDS.register("magenta_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER_FLOWING = FLUIDS.register("light_blue_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER_FLOWING = FLUIDS.register("yellow_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER_FLOWING = FLUIDS.register("lime_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER_FLOWING = FLUIDS.register("pink_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER_FLOWING = FLUIDS.register("gray_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER_FLOWING = FLUIDS.register("light_gray_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER_FLOWING = FLUIDS.register("cyan_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER_FLOWING = FLUIDS.register("purple_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER_FLOWING = FLUIDS.register("blue_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER_FLOWING = FLUIDS.register("brown_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER_FLOWING = FLUIDS.register("green_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER_FLOWING = FLUIDS.register("red_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER_FLOWING = FLUIDS.register("black_water_flowing", () ->
            new ForgeFlowingFluid.Flowing(GaymerCraft.BLACK_WATER_PROPERTIES)
    );

    // FLUID BLOCKS
    public static final RegistryObject<FlowingFluidBlock> RAINBOW_WATER_BLOCK = BLOCKS.register("rainbow_water_block", () ->
            new ColoredFlowingFluidBlock(RAINBOW_WATER, createWaterBlockProperties(MaterialColor.WATER), GaymerCraft.RAINBOW_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> WHITE_WATER_BLOCK = BLOCKS.register("white_water_block", () ->
            new ColoredFlowingFluidBlock(WHITE_WATER, createWaterBlockProperties(MaterialColor.WHITE_TERRACOTTA), GaymerCraft.WHITE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> ORANGE_WATER_BLOCK = BLOCKS.register("orange_water_block", () ->
            new ColoredFlowingFluidBlock(ORANGE_WATER, createWaterBlockProperties(MaterialColor.ORANGE_TERRACOTTA), GaymerCraft.ORANGE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> MAGENTA_WATER_BLOCK = BLOCKS.register("magenta_water_block", () ->
            new ColoredFlowingFluidBlock(MAGENTA_WATER, createWaterBlockProperties(MaterialColor.MAGENTA), GaymerCraft.MAGENTA_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("light_blue_water_block", () ->
            new ColoredFlowingFluidBlock(LIGHT_BLUE_WATER, createWaterBlockProperties(MaterialColor.LIGHT_BLUE), GaymerCraft.LIGHT_BLUE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> YELLOW_WATER_BLOCK = BLOCKS.register("yellow_water_block", () ->
            new ColoredFlowingFluidBlock(YELLOW_WATER, createWaterBlockProperties(MaterialColor.YELLOW), GaymerCraft.YELLOW_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIME_WATER_BLOCK = BLOCKS.register("lime_water_block", () ->
            new ColoredFlowingFluidBlock(LIME_WATER, createWaterBlockProperties(MaterialColor.LIME), GaymerCraft.LIME_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> PINK_WATER_BLOCK = BLOCKS.register("pink_water_block", () ->
            new ColoredFlowingFluidBlock(PINK_WATER, createWaterBlockProperties(MaterialColor.PINK), GaymerCraft.PINK_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> GRAY_WATER_BLOCK = BLOCKS.register("gray_water_block", () ->
            new ColoredFlowingFluidBlock(GRAY_WATER, createWaterBlockProperties(MaterialColor.GRAY), GaymerCraft.GRAY_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("light_gray_water_block", () ->
            new ColoredFlowingFluidBlock(LIGHT_GRAY_WATER, createWaterBlockProperties(MaterialColor.LIGHT_GRAY), GaymerCraft.LIGHT_GRAY_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> CYAN_WATER_BLOCK = BLOCKS.register("cyan_water_block", () ->
            new ColoredFlowingFluidBlock(CYAN_WATER, createWaterBlockProperties(MaterialColor.CYAN), GaymerCraft.CYAN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> PURPLE_WATER_BLOCK = BLOCKS.register("purple_water_block", () ->
            new ColoredFlowingFluidBlock(PURPLE_WATER, createWaterBlockProperties(MaterialColor.PURPLE), GaymerCraft.PURPLE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BLUE_WATER_BLOCK = BLOCKS.register("blue_water_block", () ->
            new ColoredFlowingFluidBlock(BLUE_WATER, createWaterBlockProperties(MaterialColor.BLUE), GaymerCraft.BLUE_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BROWN_WATER_BLOCK = BLOCKS.register("brown_water_block", () ->
            new ColoredFlowingFluidBlock(BROWN_WATER, createWaterBlockProperties(MaterialColor.BROWN), GaymerCraft.BROWN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> GREEN_WATER_BLOCK = BLOCKS.register("green_water_block", () ->
            new ColoredFlowingFluidBlock(GREEN_WATER, createWaterBlockProperties(MaterialColor.GREEN), GaymerCraft.GREEN_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> RED_WATER_BLOCK = BLOCKS.register("red_water_block", () ->
            new ColoredFlowingFluidBlock(RED_WATER, createWaterBlockProperties(MaterialColor.RED), GaymerCraft.RED_BUBBLE_COLUMN)
    );
    public static final RegistryObject<FlowingFluidBlock> BLACK_WATER_BLOCK = BLOCKS.register("black_water_block", () ->
            new ColoredFlowingFluidBlock(BLACK_WATER, createWaterBlockProperties(MaterialColor.BLACK), GaymerCraft.BLACK_BUBBLE_COLUMN)
    );

    // BUBBLE COLUMN BLOCKS
    public static final RegistryObject<ColoredBubbleColumnBlock> RAINBOW_BUBBLE_COLUMN = BLOCKS.register("rainbow_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RAINBOW_WATER, RAINBOW_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> WHITE_BUBBLE_COLUMN = BLOCKS.register("white_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), WHITE_WATER, WHITE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> ORANGE_BUBBLE_COLUMN = BLOCKS.register("orange_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), ORANGE_WATER, ORANGE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> MAGENTA_BUBBLE_COLUMN = BLOCKS.register("magenta_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), MAGENTA_WATER, MAGENTA_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIGHT_BLUE_BUBBLE_COLUMN = BLOCKS.register("light_blue_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> YELLOW_BUBBLE_COLUMN = BLOCKS.register("yellow_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), YELLOW_WATER, YELLOW_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIME_BUBBLE_COLUMN = BLOCKS.register("lime_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), LIME_WATER, LIME_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> PINK_BUBBLE_COLUMN = BLOCKS.register("pink_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), PINK_WATER, PINK_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> GRAY_BUBBLE_COLUMN = BLOCKS.register("gray_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), GRAY_WATER, GRAY_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> LIGHT_GRAY_BUBBLE_COLUMN = BLOCKS.register("light_gray_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> CYAN_BUBBLE_COLUMN = BLOCKS.register("cyan_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), CYAN_WATER, CYAN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> PURPLE_BUBBLE_COLUMN = BLOCKS.register("purple_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), PURPLE_WATER, PURPLE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BLUE_BUBBLE_COLUMN = BLOCKS.register("blue_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), BLUE_WATER, BLUE_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BROWN_BUBBLE_COLUMN = BLOCKS.register("brown_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), BROWN_WATER, BROWN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> GREEN_BUBBLE_COLUMN = BLOCKS.register("green_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), GREEN_WATER, GREEN_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> RED_BUBBLE_COLUMN = BLOCKS.register("red_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), RED_WATER, RED_WATER_BLOCK)
    );
    public static final RegistryObject<ColoredBubbleColumnBlock> BLACK_BUBBLE_COLUMN = BLOCKS.register("black_bubble_column", () ->
            new ColoredBubbleColumnBlock(Block.Properties.from(Blocks.BUBBLE_COLUMN), BLACK_WATER, BLACK_WATER_BLOCK)
    );


    // BUCKET FOR THE FLUIDS
    public static final Item.Properties waterBucketItemProperties = new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(creativeTab.itemGroup);
    public static final RegistryObject<Item> RAINBOW_WATER_BUCKET = ITEMS.register("rainbow_water_bucket", () ->
            new BucketItem(RAINBOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> WHITE_WATER_BUCKET = ITEMS.register("white_water_bucket", () ->
            new BucketItem(WHITE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> ORANGE_WATER_BUCKET = ITEMS.register("orange_water_bucket", () ->
            new BucketItem(ORANGE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> MAGENTA_WATER_BUCKET = ITEMS.register("magenta_water_bucket", () ->
            new BucketItem(MAGENTA_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_BLUE_WATER_BUCKET = ITEMS.register("light_blue_water_bucket", () ->
            new BucketItem(LIGHT_BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> YELLOW_WATER_BUCKET = ITEMS.register("yellow_water_bucket", () ->
            new BucketItem(YELLOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIME_WATER_BUCKET = ITEMS.register("lime_water_bucket", () ->
            new BucketItem(LIME_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PINK_WATER_BUCKET = ITEMS.register("pink_water_bucket", () ->
            new BucketItem(PINK_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GRAY_WATER_BUCKET = ITEMS.register("gray_water_bucket", () ->
            new BucketItem(GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_GRAY_WATER_BUCKET = ITEMS.register("light_gray_water_bucket", () ->
            new BucketItem(LIGHT_GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> CYAN_WATER_BUCKET = ITEMS.register("cyan_water_bucket", () ->
            new BucketItem(CYAN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PURPLE_WATER_BUCKET = ITEMS.register("purple_water_bucket", () ->
            new BucketItem(PURPLE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLUE_WATER_BUCKET = ITEMS.register("blue_water_bucket", () ->
            new BucketItem(BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BROWN_WATER_BUCKET = ITEMS.register("brown_water_bucket", () ->
            new BucketItem(BROWN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GREEN_WATER_BUCKET = ITEMS.register("green_water_bucket", () ->
            new BucketItem(GREEN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> RED_WATER_BUCKET = ITEMS.register("red_water_bucket", () ->
            new BucketItem(RED_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLACK_WATER_BUCKET = ITEMS.register("black_water_bucket", () ->
            new BucketItem(BLACK_WATER, waterBucketItemProperties)
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

    // todo: ###### END ###### Fluid stuff (put it out of the main mod class...)

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public GaymerCraft() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.ConfigConstants.CLIENT_CONFIG_FILE_NAME));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.ConfigConstants.COMMON_CONFIG_FILE_NAME));

        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this); // todo: WATER CODE STUFF

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus(); // todo: WATER CODE STUFF

        modEventBus.addListener(this::loadComplete); // todo: WATER CODE STUFF

        BLOCKS.register(modEventBus); // todo: WATER CODE STUFF
        ITEMS.register(modEventBus); // todo: WATER CODE STUFF
        FLUIDS.register(modEventBus); // todo: WATER CODE STUFF
    }

    public void loadComplete(FMLLoadCompleteEvent event) // todo: WATER CODE STUFF
    {
        // todo: Check if this is good here. It'll work, but not sure, if this is the best place.
        ComposterBlock.registerCompostable(0.5F, ModItems.RAINBOW_SEAGRASS); // vanilla seagrass has a chance of 0.3F
        ComposterBlock.registerCompostable(0.8F, ModItems.RAINBOW_SEA_PICKLE); // vanilla sea pickle has a chance of 0.65F
        ComposterBlock.registerCompostable(0.5F, ModItems.RAINBOW_KELP); //vanilla kelp has a chance of 0.3F
        // some sanity checks
        BlockState state = Fluids.WATER.getDefaultState().getBlockState(); // todo: WATER CODE STUFF
        BlockState state2 = Fluids.WATER.getAttributes().getBlock(null,null,Fluids.WATER.getDefaultState()); // todo: WATER CODE STUFF
        Validate.isTrue(state.getBlock() == Blocks.WATER && state2 == state); // todo: WATER CODE STUFF
        ItemStack stack = Fluids.WATER.getAttributes().getBucket(new FluidStack(Fluids.WATER, 1)); // todo: WATER CODE STUFF
        Validate.isTrue(stack.getItem() == Fluids.WATER.getFilledBucket()); // todo: WATER CODE STUFF
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        // LOGGER.info("HELLO FROM PREINIT");
        // LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        proxy.registerColoredBlocks();
        proxy.registerColoredItems();
        proxy.registerEntityRenderers();
        // do something that can only be done on the client
        // LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        // InterModComms.sendTo("gaymercraft", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        // LOGGER.info("Got IMC {}", event.getIMCStream().
        //        map(m->m.getMessageSupplier().get()).
        //        collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent //todo: If I ever use this, I should rather use the @Mod.EventBusSubscriber, I think.
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        // LOGGER.info("HELLO from server starting");
    }

    // todo: water stuff. This has to move too.
    private static Block.Properties createWaterBlockProperties(MaterialColor color) {
        return Block.Properties.create(
                new Material.Builder(color).doesNotBlockMovement().notOpaque().notSolid().pushDestroys().replaceable().liquid().build()
        ).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops();
    }
}