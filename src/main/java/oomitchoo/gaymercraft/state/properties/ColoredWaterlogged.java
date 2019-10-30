package oomitchoo.gaymercraft.state.properties;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IStringSerializable;
import oomitchoo.gaymercraft.init.ModFluids;

public enum ColoredWaterlogged implements IStringSerializable {
    EMPTY("empty", Fluids.EMPTY, Fluids.EMPTY),
    VANILLA("vanilla_water", Fluids.WATER, Fluids.WATER),
    RAINBOW("rainbow_water", ModFluids.RAINBOW_WATER, ModFluids.RAINBOW_WATER_FLOWING),
    WHITE("white_water", ModFluids.WHITE_WATER, ModFluids.WHITE_WATER_FLOWING),
    ORANGE("orange_water", ModFluids.ORANGE_WATER, ModFluids.ORANGE_WATER_FLOWING),
    MAGENTA("magenta_water", ModFluids.MAGENTA_WATER, ModFluids.MAGENTA_WATER_FLOWING),
    LIGHT_BLUE("light_blue_water", ModFluids.LIGHT_BLUE_WATER, ModFluids.LIGHT_BLUE_WATER_FLOWING),
    YELLOW("yellow_water", ModFluids.YELLOW_WATER, ModFluids.YELLOW_WATER_FLOWING),
    LIME("lime_water", ModFluids.LIME_WATER, ModFluids.LIME_WATER_FLOWING),
    PINK("pink_water", ModFluids.PINK_WATER, ModFluids.PINK_WATER_FLOWING),
    GRAY("gray_water", ModFluids.GRAY_WATER, ModFluids.GRAY_WATER_FLOWING),
    LIGHT_GRAY("light_gray_water", ModFluids.LIGHT_GRAY_WATER, ModFluids.LIGHT_GRAY_WATER_FLOWING),
    CYAN("cyan_water", ModFluids.CYAN_WATER, ModFluids.CYAN_WATER_FLOWING),
    PURPLE("purple_water", ModFluids.PURPLE_WATER, ModFluids.PURPLE_WATER_FLOWING),
    BLUE("blue_water", ModFluids.BLUE_WATER, ModFluids.BLUE_WATER_FLOWING),
    BROWN("brown_water", ModFluids.BROWN_WATER, ModFluids.BROWN_WATER_FLOWING),
    GREEN("green_water", ModFluids.GREEN_WATER, ModFluids.GREEN_WATER_FLOWING),
    RED("red_water", ModFluids.RED_WATER, ModFluids.RED_WATER_FLOWING),
    BLACK("black_water", ModFluids.BLACK_WATER, ModFluids.BLACK_WATER_FLOWING);

    private final String name;
    private final Fluid fluidStill;
    private final Fluid fluidFlowing;

    private ColoredWaterlogged(String name, Fluid fluidStill, Fluid fluidFlowing) {
        this.name = name;
        this.fluidStill = fluidStill;
        this.fluidFlowing = fluidFlowing;
    }

    public String toString() {
        return this.name;
    }

    public String getName() { return this.name; }

    public Fluid getFluid() { return this.fluidStill; }

    public static ColoredWaterlogged byFluid(Fluid fluid) {
        for(ColoredWaterlogged waterType : values()) {
            if (waterType.fluidStill.equals(fluid)) {
                return waterType;
            }
        }
        return VANILLA;
    }
}
