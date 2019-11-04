package oomitchoo.gaymercraft.state.properties;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IStringSerializable;
import oomitchoo.gaymercraft.init.RegistryObjects;

import javax.annotation.Nullable;

public enum ColoredWaterlogged implements IStringSerializable {
    EMPTY("empty", Fluids.EMPTY, null),
    VANILLA("vanilla_water", Fluids.WATER, null),
    RAINBOW("rainbow_water", null, RegistryObjects.RAINBOW_WATER),
    WHITE("white_water", null, RegistryObjects.WHITE_WATER),
    ORANGE("orange_water", null, RegistryObjects.ORANGE_WATER),
    MAGENTA("magenta_water", null, RegistryObjects.MAGENTA_WATER),
    LIGHT_BLUE("light_blue_water", null, RegistryObjects.LIGHT_BLUE_WATER),
    YELLOW("yellow_water", null, RegistryObjects.YELLOW_WATER),
    LIME("lime_water", null, RegistryObjects.LIME_WATER),
    PINK("pink_water", null, RegistryObjects.PINK_WATER),
    GRAY("gray_water", null, RegistryObjects.GRAY_WATER),
    LIGHT_GRAY("light_gray_water", null, RegistryObjects.LIGHT_GRAY_WATER),
    CYAN("cyan_water", null, RegistryObjects.CYAN_WATER),
    PURPLE("purple_water", null, RegistryObjects.PURPLE_WATER),
    BLUE("blue_water", null, RegistryObjects.BLUE_WATER),
    BROWN("brown_water", null, RegistryObjects.BROWN_WATER),
    GREEN("green_water", null, RegistryObjects.GREEN_WATER),
    RED("red_water", null, RegistryObjects.RED_WATER),
    BLACK("black_water", null, RegistryObjects.BLACK_WATER);

    private final String name;
    @Nullable
    private final Fluid fluidStill;
    @Nullable
    private final java.util.function.Supplier<? extends Fluid> fluidSupplier;

    private ColoredWaterlogged(String name, @Nullable Fluid fluidStill, @Nullable java.util.function.Supplier<? extends Fluid> fluidSupplier) {
        this.name = name;
        this.fluidStill = fluidStill;
        this.fluidSupplier = fluidSupplier;
    }

    public String toString() {
        return this.name;
    }

    public String getName() { return this.name; }

    public Fluid getFluid() { return this.fluidStill != null ? this.fluidStill : this.fluidSupplier.get(); }

    public java.util.function.Supplier<? extends Fluid> getFluidSupplier() { return this.fluidSupplier; }

    public static ColoredWaterlogged byFluid(Fluid fluid) {
        for(ColoredWaterlogged waterType : values()) {
            if(waterType.fluidStill != null) {
                if(waterType.fluidStill.equals(fluid)) return waterType;
            } else {
                if(waterType.fluidSupplier.get().equals(fluid)) return waterType;
            }
        }
        return VANILLA;
    }
}
