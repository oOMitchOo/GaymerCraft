package oomitchoo.gaymercraft.reference;

import oomitchoo.gaymercraft.client.ModCreativeTab;

/**
 * Created by oOMitchOo on 21.11.2018.
 */

public class Reference {
    public static final String MOD_ID = "gaymercraft";
    public static final String MOD_NAME = "GaymerCraft";
    public static final String MC_VERSION = "1.12.2";
    public static final String MOD_VERSION = MC_VERSION+"-"+"0.5";
    public static final String CLIENT_PROXY_CLASS = "oomitchoo.gaymercraft.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "oomitchoo.gaymercraft.proxy.ServerProxy";
    public static final String DEPENDENCIES = ""; //required-after:botania@[r1.10-356,) <- for vertical slabs in Botania.
    public static final String GUI_FACTORY_CLASS = "oomitchoo.gaymercraft.client.gui.GuiFactory";

    public static final ModCreativeTab creativeTab = new ModCreativeTab();

    public static class Config {
        public static int configColoredWaterBrightness = 60;
    }
}
