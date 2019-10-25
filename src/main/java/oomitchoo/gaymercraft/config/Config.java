package oomitchoo.gaymercraft.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import oomitchoo.gaymercraft.reference.Reference;

import java.nio.file.Path;

public class Config {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    // unicorn
    public static ForgeConfigSpec.IntValue UNICORN_LOVE_AMOUNT;
    public static ForgeConfigSpec.IntValue UNICORN_MAX_HEALTH_HALF_HEARTS;
    public static ForgeConfigSpec.DoubleValue UNICORN_MOVEMENT_SPEED;
    public static ForgeConfigSpec.DoubleValue UNICORN_JUMP_STRENGTH;
    public static ForgeConfigSpec.DoubleValue UNICORN_ARMOR;
    // colored_water
    public static ForgeConfigSpec.IntValue COLORED_WATER_BRIGHTNESS;

    static {
        COMMON_BUILDER.comment("Unicorn Settings").push(Reference.ConfigConstants.CATEGORY_UNICORN);
        setupUnicornConfig();
        COMMON_BUILDER.pop();

        CLIENT_BUILDER.comment("Colored Water Settings").push(Reference.ConfigConstants.CATEGORY_COLORED_WATER);
        setupColoredWaterConfig();
        CLIENT_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupUnicornConfig() {
        UNICORN_LOVE_AMOUNT = COMMON_BUILDER.comment("Sets the amount of heart particles around a unicorn. The number translates to the chance (in %) for 1 heart per tick. Can be modified while playing.")
                .defineInRange("LoveForYou", Reference.ConfigConstants.defaultUnicornLoveAmount, 0, 100);
        UNICORN_MAX_HEALTH_HALF_HEARTS = COMMON_BUILDER.comment("Sets the max health for the unicorn. (in amount of HALF hearts) SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.")
                .defineInRange("MaxHealthHalfHearts", Reference.ConfigConstants.defaultUnicornMaxHealthHalfHearts, 15, 30);
        UNICORN_MOVEMENT_SPEED = COMMON_BUILDER.comment("Sets the movement speed for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.")
                .defineInRange("MovementSpeed", Reference.ConfigConstants.defaultUnicornMovementSpeed,0.1125D, 0.3375D);
        UNICORN_JUMP_STRENGTH = COMMON_BUILDER.comment("Sets the jump strength for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.")
                .defineInRange("JumpStrength", Reference.ConfigConstants.defaultUnicornJumpStrength, 0.5D, 1.0D);
        UNICORN_ARMOR = COMMON_BUILDER.comment("Sets the armor for the unicorn. Can't surpass a vanilla horse. SP: Can be modified while playing but applies only to newly spawned unicorns. MP: Restart Server.")
                .defineInRange("Armor", Reference.ConfigConstants.defaultUnicornArmor, 0, 11);
    }

    private static void setupColoredWaterConfig() {
        // "Sets the brightness of the water. (in multiples of 10%, meaning 1 is 10%, 2 is 20%, ... 10 is 100% and so on) Needs a MC restart."
        COLORED_WATER_BRIGHTNESS = CLIENT_BUILDER.comment("NOT WORKING YET. BRIGHTNESS IS ALWAYS THE DEFAULT 60%.")
                .defineInRange("Brigthness", Reference.ConfigConstants.defaultColoredWaterBrightness, 0, 10);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) { }
}
