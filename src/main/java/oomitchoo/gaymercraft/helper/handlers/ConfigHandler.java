package oomitchoo.gaymercraft.helper.handlers;

import net.minecraftforge.common.config.Configuration;
import oomitchoo.gaymercraft.reference.Reference;

import java.io.File;

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
        Reference.Config.configColoredWaterBrightness = config.get(Reference.Config.CATEGORY_COLORED_WATER,"Brightness", 60, "Sets the brightness of the water. Only multiples of 10 within range are allowed (0, 10, 20, .., 100), any other number gets round up/down to nearest allowed one. Needs a MC restart.", 0, 100).getInt(60);
        Reference.Config.configColoredWaterBrightness = setBrightnessToAllowed(Reference.Config.configColoredWaterBrightness);

        Reference.Config.configUnicornLoveAmount = config.get(Reference.Config.CATEGORY_UNICORN, "LoveForYou", 5, "Sets the amount of heart particles around a unicorn. The number translates to the chance (in %) for 1 heart per tick. Can be modified while playing.", 0, 100).getInt(1);

        Reference.Config.configUnicornMaxHealth = config.get(Reference.Config.CATEGORY_UNICORN, "MaxHealth", 30D, "Sets the max health for the unicorn. Multiples of 0.5 within range are allowed (15, 15.5, 16 .., 30). SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.", 15D, 30D).getDouble(30D);
        Reference.Config.configUnicornMaxHealth = setMaxHealthToAllowed(Reference.Config.configUnicornMaxHealth);

        Reference.Config.configUnicornMovementSpeed = config.get(Reference.Config.CATEGORY_UNICORN, "MovementSpeed", 0.3375D, "Sets the movement speed for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.", 0.1125D, 0.3375D).getDouble(0.3375D);
        Reference.Config.configUnicornMovementSpeed = setDoubleToFiveDigitsAllowed(Reference.Config.configUnicornMovementSpeed, 0.3375D, 0.1125D, 0.3375D);

        Reference.Config.configUnicornJumpStrength = config.get(Reference.Config.CATEGORY_UNICORN, "JumpStrength", 1.0D, "Sets the jump strength for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.", 0.5D, 1.0D).getDouble(1.0D);
        Reference.Config.configUnicornJumpStrength = setDoubleToFiveDigitsAllowed(Reference.Config.configUnicornJumpStrength, 1.0D, 0.5D, 1.0D);

        Reference.Config.configUnicornArmor = (double)config.get(Reference.Config.CATEGORY_UNICORN, "Armor", 11, "Sets the armor for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.", 0, 11).getInt(11);
        Reference.Config.configUnicornArmor = (double)setIntToAllowed((int)Reference.Config.configUnicornArmor, 11, 0, 11);

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

    private static double setMaxHealthToAllowed (double maxHealth) {
        int maxHealthTimes10 = (int)(maxHealth*10);

        if(maxHealthTimes10 < 150 || maxHealthTimes10 > 300) {
            return 30D;
        } else {
            return (((double)maxHealthTimes10 - 5) + (double)(5 - (maxHealthTimes10 % 5)))/10; // rounds up to nearest int divisible by 5.
        }
    }
    private static double setDoubleToFiveDigitsAllowed (double valueToCheck, double defaultValue, double min, double max) {
        int valueToCheckTimes10000 = (int)(valueToCheck*10000);

        if (valueToCheckTimes10000 < (int)(min*10000) || valueToCheckTimes10000 > (int)(max*10000)) {
             return defaultValue;
        } else {
            return ((double)valueToCheckTimes10000) / 10000;
        }
    }

    private static int setIntToAllowed (int valueToCheck, int defaultValue, int min, int max) {
        return valueToCheck < min || valueToCheck > max ? defaultValue : valueToCheck;
    }
}