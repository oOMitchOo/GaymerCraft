package oomitchoo.gaymercraft.common.crafting;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import oomitchoo.gaymercraft.reference.Reference;

import java.util.function.BooleanSupplier;

/**
 * Created by oOMitchOo on 13.01.2019.
 */
public class ConditionBotaniaLoaded implements IConditionFactory {
    // "conditions": [{"is_botania_loaded": "true"}],
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {

        return () -> Reference.ModSupport.isBotaniaLoaded;
    }
}