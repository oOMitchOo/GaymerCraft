package oomitchoo.gaymercraft.item.crafting.conditions;

import com.google.gson.JsonObject;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import oomitchoo.gaymercraft.config.Config;
import oomitchoo.gaymercraft.reference.Reference;

public class VertSlabsEnabledCondition implements ICondition {
    public static final VertSlabsEnabledCondition INSTANCE = new VertSlabsEnabledCondition();
    private static final ResourceLocation NAME = new ResourceLocation(Reference.MOD_ID, "vert_slabs_by_config");

    private VertSlabsEnabledCondition() {}

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test() {
        return Config.VERTICAL_SLABS_ENABLED.get();
    }

    @Override
    public String toString()
    {
        return Boolean.toString(Config.VERTICAL_SLABS_ENABLED.get());
    }

    public static class Serializer implements IConditionSerializer<VertSlabsEnabledCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, VertSlabsEnabledCondition value) { }

        @Override
        public VertSlabsEnabledCondition read(JsonObject json)
        {
            return VertSlabsEnabledCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return VertSlabsEnabledCondition.NAME;
        }
    }
}
