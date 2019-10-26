package oomitchoo.gaymercraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
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
import oomitchoo.gaymercraft.config.Config;
import oomitchoo.gaymercraft.init.CreativeTab;
import oomitchoo.gaymercraft.proxy.ClientProxy;
import oomitchoo.gaymercraft.proxy.IProxy;
import oomitchoo.gaymercraft.proxy.ServerProxy;
import oomitchoo.gaymercraft.reference.Reference;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;

@Mod(Reference.MOD_ID)
public class GaymerCraft
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static CreativeTab creativeTab = new CreativeTab();

    // todo: Hope for a better Forge solution for the fluids. I'm just keeping it in the main class for now...
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID); // todo: WATER CODE STUFF
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID); // todo: WATER CODE STUFF
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Reference.MOD_ID); // todo: WATER CODE STUFF

    // FLUIDS (STILL?)
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER = FLUIDS.register("rainbow_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER = FLUIDS.register("white_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER = FLUIDS.register("orange_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER = FLUIDS.register("magenta_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER = FLUIDS.register("light_blue_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER = FLUIDS.register("yellow_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER = FLUIDS.register("lime_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER = FLUIDS.register("pink_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER = FLUIDS.register("gray_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER = FLUIDS.register("light_gray_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER = FLUIDS.register("cyan_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER = FLUIDS.register("purple_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER = FLUIDS.register("blue_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER = FLUIDS.register("brown_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER = FLUIDS.register("green_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER = FLUIDS.register("red_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER = FLUIDS.register("black_water", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Source(GaymerCraft.BLACK_WATER_PROPERTIES)
    );

    // FLUIDS FLOWING
    public static final RegistryObject<FlowingFluid> RAINBOW_WATER_FLOWING = FLUIDS.register("rainbow_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.RAINBOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> WHITE_WATER_FLOWING = FLUIDS.register("white_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.WHITE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> ORANGE_WATER_FLOWING = FLUIDS.register("orange_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.ORANGE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> MAGENTA_WATER_FLOWING = FLUIDS.register("magenta_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.MAGENTA_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_BLUE_WATER_FLOWING = FLUIDS.register("light_blue_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIGHT_BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> YELLOW_WATER_FLOWING = FLUIDS.register("yellow_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.YELLOW_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIME_WATER_FLOWING = FLUIDS.register("lime_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIME_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PINK_WATER_FLOWING = FLUIDS.register("pink_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.PINK_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GRAY_WATER_FLOWING = FLUIDS.register("gray_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> LIGHT_GRAY_WATER_FLOWING = FLUIDS.register("light_gray_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.LIGHT_GRAY_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> CYAN_WATER_FLOWING = FLUIDS.register("cyan_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.CYAN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> PURPLE_WATER_FLOWING = FLUIDS.register("purple_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.PURPLE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLUE_WATER_FLOWING = FLUIDS.register("blue_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.BLUE_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BROWN_WATER_FLOWING = FLUIDS.register("brown_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.BROWN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> GREEN_WATER_FLOWING = FLUIDS.register("green_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.GREEN_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> RED_WATER_FLOWING = FLUIDS.register("red_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.RED_WATER_PROPERTIES)
    );
    public static final RegistryObject<FlowingFluid> BLACK_WATER_FLOWING = FLUIDS.register("black_water_flowing", () -> // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Flowing(GaymerCraft.BLACK_WATER_PROPERTIES)
    );

    // FLUID BLOCKS
    public static final Block.Properties waterBlockProperties = Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops();
    public static final RegistryObject<FlowingFluidBlock> RAINBOW_WATER_BLOCK = BLOCKS.register("rainbow_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(RAINBOW_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> WHITE_WATER_BLOCK = BLOCKS.register("white_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(WHITE_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> ORANGE_WATER_BLOCK = BLOCKS.register("orange_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(ORANGE_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> MAGENTA_WATER_BLOCK = BLOCKS.register("magenta_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(MAGENTA_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_BLUE_WATER_BLOCK = BLOCKS.register("light_blue_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(LIGHT_BLUE_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> YELLOW_WATER_BLOCK = BLOCKS.register("yellow_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(YELLOW_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> LIME_WATER_BLOCK = BLOCKS.register("lime_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(LIME_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> PINK_WATER_BLOCK = BLOCKS.register("pink_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(PINK_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> GRAY_WATER_BLOCK = BLOCKS.register("gray_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(GRAY_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> LIGHT_GRAY_WATER_BLOCK = BLOCKS.register("light_gray_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(LIGHT_GRAY_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> CYAN_WATER_BLOCK = BLOCKS.register("cyan_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(CYAN_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> PURPLE_WATER_BLOCK = BLOCKS.register("purple_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(PURPLE_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> BLUE_WATER_BLOCK = BLOCKS.register("blue_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(BLUE_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> BROWN_WATER_BLOCK = BLOCKS.register("brown_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(BROWN_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> GREEN_WATER_BLOCK = BLOCKS.register("green_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(GREEN_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> RED_WATER_BLOCK = BLOCKS.register("red_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(RED_WATER, waterBlockProperties)
    );
    public static final RegistryObject<FlowingFluidBlock> BLACK_WATER_BLOCK = BLOCKS.register("black_water_block", () -> // todo: WATER CODE STUFF
            new FlowingFluidBlock(BLACK_WATER, waterBlockProperties)
    );

    // BUCKET FOR THE FLUIDS
    public static final Item.Properties waterBucketItemProperties = new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(creativeTab.itemGroup);
    public static final RegistryObject<Item> RAINBOW_WATER_BUCKET = ITEMS.register("rainbow_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(RAINBOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> WHITE_WATER_BUCKET = ITEMS.register("white_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(WHITE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> ORANGE_WATER_BUCKET = ITEMS.register("orange_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(ORANGE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> MAGENTA_WATER_BUCKET = ITEMS.register("magenta_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(MAGENTA_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_BLUE_WATER_BUCKET = ITEMS.register("light_blue_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(LIGHT_BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> YELLOW_WATER_BUCKET = ITEMS.register("yellow_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(YELLOW_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIME_WATER_BUCKET = ITEMS.register("lime_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(LIME_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PINK_WATER_BUCKET = ITEMS.register("pink_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(PINK_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GRAY_WATER_BUCKET = ITEMS.register("gray_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> LIGHT_GRAY_WATER_BUCKET = ITEMS.register("light_gray_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(LIGHT_GRAY_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> CYAN_WATER_BUCKET = ITEMS.register("cyan_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(CYAN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> PURPLE_WATER_BUCKET = ITEMS.register("purple_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(PURPLE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLUE_WATER_BUCKET = ITEMS.register("blue_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(BLUE_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BROWN_WATER_BUCKET = ITEMS.register("brown_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(BROWN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> GREEN_WATER_BUCKET = ITEMS.register("green_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(GREEN_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> RED_WATER_BUCKET = ITEMS.register("red_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(RED_WATER, waterBucketItemProperties)
    );
    public static final RegistryObject<Item> BLACK_WATER_BUCKET = ITEMS.register("black_water_bucket", () -> // todo: WATER CODE STUFF
            new BucketItem(BLACK_WATER, waterBucketItemProperties)
    );

    // FLUID PROPERTIES
    public static final ResourceLocation waterStillTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_still");
    public static final ResourceLocation waterFlowingTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_flow");
    public static final ResourceLocation rainbowWaterStillTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_rainbow_water_still");
    public static final ResourceLocation rainbowWaterFlowingTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_rainbow_water_flow");
    public static final ResourceLocation waterOverlayTexture = new ResourceLocation(Reference.MOD_ID, "block/desat_hell60_water_overlay");
    public static final ForgeFlowingFluid.Properties RAINBOW_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(RAINBOW_WATER, RAINBOW_WATER_FLOWING, FluidAttributes.builder(rainbowWaterStillTexture, rainbowWaterFlowingTexture).overlay(waterOverlayTexture)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(RAINBOW_WATER_BUCKET).block(RAINBOW_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties WHITE_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(WHITE_WATER, WHITE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFF0F0F0)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(WHITE_WATER_BUCKET).block(WHITE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties ORANGE_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(ORANGE_WATER, ORANGE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFEB8844)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(ORANGE_WATER_BUCKET).block(ORANGE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties MAGENTA_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(MAGENTA_WATER, MAGENTA_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFC354CD)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(MAGENTA_WATER_BUCKET).block(MAGENTA_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIGHT_BLUE_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(LIGHT_BLUE_WATER, LIGHT_BLUE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF6689D3)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIGHT_BLUE_WATER_BUCKET).block(LIGHT_BLUE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties YELLOW_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(YELLOW_WATER, YELLOW_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFDECF2A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(YELLOW_WATER_BUCKET).block(YELLOW_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIME_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(LIME_WATER, LIME_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF41CD34)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIME_WATER_BUCKET).block(LIME_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties PINK_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(PINK_WATER, PINK_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFD88198)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(PINK_WATER_BUCKET).block(PINK_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties GRAY_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(GRAY_WATER, GRAY_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF434343)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(GRAY_WATER_BUCKET).block(GRAY_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties LIGHT_GRAY_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(LIGHT_GRAY_WATER, LIGHT_GRAY_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFABABAB)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(LIGHT_GRAY_WATER_BUCKET).block(LIGHT_GRAY_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties CYAN_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(CYAN_WATER, CYAN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF287697)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(CYAN_WATER_BUCKET).block(CYAN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties PURPLE_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(PURPLE_WATER, PURPLE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF7B2FBE)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(PURPLE_WATER_BUCKET).block(PURPLE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BLUE_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(BLUE_WATER, BLUE_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF253192)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BLUE_WATER_BUCKET).block(BLUE_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BROWN_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(BROWN_WATER, BROWN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF51301A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BROWN_WATER_BUCKET).block(BROWN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties GREEN_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(GREEN_WATER, GREEN_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF3B511A)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(GREEN_WATER_BUCKET).block(GREEN_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties RED_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(RED_WATER, RED_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFFB3312C)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(RED_WATER_BUCKET).block(RED_WATER_BLOCK).canMultiply();
    public static final ForgeFlowingFluid.Properties BLACK_WATER_PROPERTIES = // todo: WATER CODE STUFF
            new ForgeFlowingFluid.Properties(BLACK_WATER, BLACK_WATER_FLOWING, FluidAttributes.builder(waterStillTexture, waterFlowingTexture).overlay(waterOverlayTexture).color(0xFF1E1B1B)) // FF ist alpha mit 100% die letzten 6 chars sind für RGB
                    .bucket(BLACK_WATER_BUCKET).block(BLACK_WATER_BLOCK).canMultiply();

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
}