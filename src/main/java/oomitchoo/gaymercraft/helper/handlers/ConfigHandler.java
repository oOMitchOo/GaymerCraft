package oomitchoo.gaymercraft.helper.handlers;

import net.minecraftforge.common.config.Configuration;
import oomitchoo.gaymercraft.helper.LogHelper;
import oomitchoo.gaymercraft.reference.Reference;

import java.io.File;
import java.math.RoundingMode;

/**
 * Created by oOMitchOo on 07.12.2018.
 */
public class ConfigHandler {
    public static Configuration config;

    public static void init(File configFile) {
        config = new Configuration(configFile);

        config.load();
        loadConfig();
    }

    public static void loadConfig () {
        Reference.Config.configColoredWaterBrightness = config.get(Reference.Config.CATEGORY_COLORED_WATER,"BrightnessColoredWater", 60, "Sets the brightness of the water. Only multiples of 10 within range are allowed (0, 10, 20, .., 100), any other number gets round up/down to nearest allowed one. Needs a MC restart.", 0, 100).getInt(60);
        Reference.Config.configColoredWaterBrightness = setBrightnessToAllowed(Reference.Config.configColoredWaterBrightness);

        Reference.Config.configUnicornLoveAmount = config.get(Reference.Config.CATEGORY_UNICORN, "UnicornLoveForYou", 5, "Sets the amount of heart particles around a unicorn. The number roughly translates to the chance for 1 heart per tick. Can be modified while playing.", 0, 100).getInt(1);

        if (config.hasChanged()) {
            config.save();
        }
    }

    private static int setBrightnessToAllowed(int brightness) {
        if ((brightness < 0) || (brightness > 100)) {
            return 60;
        } else if ((brightness % 10) == 0) {
            return brightness;
        } else {
            return (Math.round(((float)brightness / 10))*10);
        }
    }
}