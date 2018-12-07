package oomitchoo.gaymercraft;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oomitchoo.gaymercraft.helper.handlers.ConfigHandler;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.proxy.IProxy;
import oomitchoo.gaymercraft.reference.Reference;

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
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        LogHelper.info("Pre Initialization of GaymerCraft Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        LogHelper.info("Initialization of GaymerCraft Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

        LogHelper.info("Post Initialization of GaymerCraft Complete!");
    }
}
