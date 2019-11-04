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
import oomitchoo.gaymercraft.init.RegistryObjects;
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

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public GaymerCraft() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.ConfigConstants.CLIENT_CONFIG_FILE_NAME));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(Reference.ConfigConstants.COMMON_CONFIG_FILE_NAME));

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(this::onServerStarting);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        RegistryObjects.BLOCKS.register(modEventBus);
        RegistryObjects.ITEMS.register(modEventBus);
        RegistryObjects.FLUIDS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // todo: Check if this is good here. It'll work, but not sure, if this is the best place.
        ComposterBlock.registerCompostable(0.5F, ModItems.RAINBOW_SEAGRASS); // vanilla seagrass has a chance of 0.3F
        ComposterBlock.registerCompostable(0.8F, ModItems.RAINBOW_SEA_PICKLE); // vanilla sea pickle has a chance of 0.65F
        ComposterBlock.registerCompostable(0.5F, ModItems.RAINBOW_KELP); //vanilla kelp has a chance of 0.3F
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

    private void onServerStarting(final FMLServerStartingEvent event) {
        // do something when the server starts
        // LOGGER.info("HELLO from server starting");
    }
}