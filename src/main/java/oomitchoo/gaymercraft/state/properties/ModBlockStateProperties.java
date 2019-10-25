package oomitchoo.gaymercraft.state.properties;

import net.minecraft.state.EnumProperty;

public class ModBlockStateProperties {
    public static final EnumProperty<VertSlabType> VERT_SLAB_TYPE;

    static {
        VERT_SLAB_TYPE = EnumProperty.create("type", VertSlabType.class);
    }
}
