package oomitchoo.gaymercraft;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oomitchoo.gaymercraft.common.config.ConfigHandler;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.init.ModEntities;
import oomitchoo.gaymercraft.proxy.IProxy;
import oomitchoo.gaymercraft.reference.Reference;

import static net.minecraftforge.fml.common.Loader.isModLoaded;

/**
 * Created by oOMitchOo on 21.11.2018.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MC_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class GaymerCraft {

    // Muss anscheinend einmal aufgerufen werden, damit der UniversalBucket überhaupt für den Mod genutzt werden kann.
    static
    { FluidRegistry.enableUniversalBucket(); }

    @Mod.Instance(Reference.MOD_ID)
    public static GaymerCraft instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        if ((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"))
            Reference.isDevEnvironment = true;

        ConfigHandler.init(event.getSuggestedConfigurationFile());

        if(isModLoaded("botania") && Reference.Config.configBotaniaSupportEnabled) { // TODO: isBotaniaLoaded is more like isBotaniaLoadedAndSupported for now.
            Reference.ModSupport.isBotaniaLoaded = true;
            oomitchoo.gaymercraft.init.modsupport.botania.ModBlocks.init();
        }

        if (Reference.ModSupport.isBotaniaLoaded)
            proxy.registerCustomModelLoader(); // This also makes a new FaceBakery.
        // init.RegistryEvents are probably fired here.
        // client.event.ModelRegistryEvents are probably fired here too.
        ModEntities.init();
        proxy.registerEntityRenderer();

        LogHelper.info("Pre Initialization of GaymerCraft Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // All blocks should be registered by now (registered in preInit). This registers a color overlay method (for the hedge) for the client. The Block Models must have a tintindex > -1 for this to work.
        GaymerCraft.proxy.registerColoredBlocks();
        // Aaaand color overlay for the item of the block above.
        GaymerCraft.proxy.registerColoredItems();
        LogHelper.info("Initialization of GaymerCraft Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        if (Reference.isDevEnvironment)
            proxy.testingStuff();
        LogHelper.info("Post Initialization of GaymerCraft Complete!");
    }
}
