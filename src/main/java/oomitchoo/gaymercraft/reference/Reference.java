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
        public static final String CATEGORY_COLORED_WATER = "colored_water";
        public static final String CATEGORY_UNICORN = "unicorn";

        public static int configColoredWaterBrightness = 60;
        public static int configUnicornLoveAmount = 1;
        public static double configUnicornMaxHealth = 30D;
        public static double configUnicornMovementSpeed = 0.3375D;
        public static double configUnicornJumpStrength = 1.0D;
        public static double configUnicornArmor = 11D;
    }
}
