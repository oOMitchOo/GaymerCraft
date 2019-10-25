package oomitchoo.gaymercraft.reference;

public class Reference {
    public static final String MOD_ID = "gaymercraft";
    public static final String MOD_NAME = "GaymerCraft";
    public static final String MC_VERSION = "1.14.4";
    public static final String CLIENT_PROXY_CLASS = "oomitchoo.gaymercraft.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "oomitchoo.gaymercraft.proxy.ServerProxy";

    public static class ConfigConstants {
        public static final String CLIENT_CONFIG_FILE_NAME = MOD_ID + "-client.toml";
        public static final String COMMON_CONFIG_FILE_NAME = MOD_ID + "-common.toml";

        public static final String CATEGORY_COLORED_WATER = "colored_water";
        public static final String CATEGORY_UNICORN = "unicorn";

        //colored_water
        public static int defaultColoredWaterBrightness = 6;
        //unicorn
        public static int defaultUnicornLoveAmount = 1;
        public static int defaultUnicornMaxHealthHalfHearts = 30;
        public static double defaultUnicornMovementSpeed = 0.3375D;
        public static double defaultUnicornJumpStrength = 1.0D;
        public static double defaultUnicornArmor = 11D;
    }
}
