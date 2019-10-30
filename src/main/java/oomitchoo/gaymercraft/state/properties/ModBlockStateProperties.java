package oomitchoo.gaymercraft.state.properties;

import net.minecraft.state.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<VertSlabType> VERT_SLAB_TYPE;
    public static final EnumProperty<ColoredWaterlogged> COLORED_WATERLOGGED;

    static {
        VERT_SLAB_TYPE = EnumProperty.create("type", VertSlabType.class);
        COLORED_WATERLOGGED = EnumProperty.create("fluid", ColoredWaterlogged.class);
    }
}
