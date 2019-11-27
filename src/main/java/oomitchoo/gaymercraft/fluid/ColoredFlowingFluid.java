package oomitchoo.gaymercraft.fluid;

import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ColoredFlowingFluid {
    public static class Flowing extends ForgeFlowingFluid.Flowing {
        public Flowing(Properties properties) {
            super(properties);
        }
    }

    public static class Source extends ForgeFlowingFluid.Source {
        public Source(Properties properties) {
            super(properties);
        }
    }
}
