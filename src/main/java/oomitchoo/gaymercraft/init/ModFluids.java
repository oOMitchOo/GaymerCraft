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
    public static final Fluid FLUID_WHITE_WATER = setupFluid(new Fluid("white_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(240, 240, 240)));
    public static final Fluid FLUID_ORANGE_WATER = setupFluid(new Fluid("orange_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(235,139,79)));
    public static final Fluid FLUID_MAGENTA_WATER = setupFluid(new Fluid("magenta_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(201,103,212)));
    public static final Fluid FLUID_LIGHT_BLUE_WATER = setupFluid(new Fluid("light_blue_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(139,164,221)));
    public static final Fluid FLUID_YELLOW_WATER = setupFluid(new Fluid("yellow_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(220,207,33)));
    public static final Fluid FLUID_LIME_WATER = setupFluid(new Fluid("lime_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(74,207,62)));
    public static final Fluid FLUID_PINK_WATER = setupFluid(new Fluid("pink_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(228,165,182)));
    public static final Fluid FLUID_GRAY_WATER = setupFluid(new Fluid("gray_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(75, 75, 75)));
    public static final Fluid FLUID_LIGHT_GRAY_WATER = setupFluid(new Fluid("light_gray_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(182,186,187)));
    public static final Fluid FLUID_CYAN_WATER = setupFluid(new Fluid("cyan_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(44,134,169)));
    public static final Fluid FLUID_PURPLE_WATER = setupFluid(new Fluid("purple_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(146,75,207)));
    public static final Fluid FLUID_BLUE_WATER = setupFluid(new Fluid("blue_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(45,58,173)));
    public static final Fluid FLUID_BROWN_WATER = setupFluid(new Fluid("brown_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(99, 59, 33)));
    public static final Fluid FLUID_GREEN_WATER = setupFluid(new Fluid("green_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(64,88,28)));
    public static final Fluid FLUID_RED_WATER = setupFluid(new Fluid("red_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(189, 50, 47)));
    public static final Fluid FLUID_BLACK_WATER = setupFluid(new Fluid("black_water", new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_still"), new ResourceLocation(Reference.MOD_ID, "blocks/fluids/desat_hell60_dk100_water_flow"), null, new Color(24, 20, 19)));


    public static Fluid setupFluid(Fluid fluid)
    {
        FluidRegistry.addBucketForFluid(fluid);
        if(!FluidRegistry.registerFluid(fluid))
            return FluidRegistry.getFluid(fluid.getName());
        return fluid;
    }
}