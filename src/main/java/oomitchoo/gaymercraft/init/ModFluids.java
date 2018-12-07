package oomitchoo.gaymercraft.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import oomitchoo.gaymercraft.reference.Reference;

import java.awt.*;

/**
 * Created by oOMitchOo on 03.12.2018.
 */
public class ModFluids {
    private static int brightness = Reference.Config.configColoredWaterBrightness;

    public static final Fluid FLUID_WHITE_WATER = setupFluid(new Fluid("white_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(240, 240, 240)));
    public static final Fluid FLUID_ORANGE_WATER = setupFluid(new Fluid("orange_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(235,136,68)));
    public static final Fluid FLUID_MAGENTA_WATER = setupFluid(new Fluid("magenta_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(195,84,205)));
    public static final Fluid FLUID_LIGHT_BLUE_WATER = setupFluid(new Fluid("light_blue_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(102,137,211)));
    public static final Fluid FLUID_YELLOW_WATER = setupFluid(new Fluid("yellow_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(222,207,42)));
    public static final Fluid FLUID_LIME_WATER = setupFluid(new Fluid("lime_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(65,205,52)));
    public static final Fluid FLUID_PINK_WATER = setupFluid(new Fluid("pink_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(216,129,152)));
    public static final Fluid FLUID_GRAY_WATER = setupFluid(new Fluid("gray_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(67, 67, 67)));
    public static final Fluid FLUID_LIGHT_GRAY_WATER = setupFluid(new Fluid("light_gray_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(171,171,171)));
    public static final Fluid FLUID_CYAN_WATER = setupFluid(new Fluid("cyan_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(40,118,151)));
    public static final Fluid FLUID_PURPLE_WATER = setupFluid(new Fluid("purple_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(123,47,190)));
    public static final Fluid FLUID_BLUE_WATER = setupFluid(new Fluid("blue_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(37,49,146)));
    public static final Fluid FLUID_BROWN_WATER = setupFluid(new Fluid("brown_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(81, 48, 26)));
    public static final Fluid FLUID_GREEN_WATER = setupFluid(new Fluid("green_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(59,81,26)));
    public static final Fluid FLUID_RED_WATER = setupFluid(new Fluid("red_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(179, 49, 44)));
    public static final Fluid FLUID_BLACK_WATER = setupFluid(new Fluid("black_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_flow"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell"+ brightness +"_water_overlay"), new Color(30, 27, 27)));

    public static Fluid setupFluid(Fluid fluid)
    {
        FluidRegistry.addBucketForFluid(fluid);
        if(!FluidRegistry.registerFluid(fluid))
            return FluidRegistry.getFluid(fluid.getName());
        return fluid;
    }
}