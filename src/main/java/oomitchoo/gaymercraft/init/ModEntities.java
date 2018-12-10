package oomitchoo.gaymercraft.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 08.12.2018.
 */
public class ModEntities {

    public static void init() {
        // todo: Int-Werte (16, 3) müssen eventuell angepasst werden. 16 ist trackingRange und 3 ist updateFrequency. trackingRange habe ich schon angepasst und sollte ungefähr dieselbe wie bei Pferden sein (bestimmt wie lange man es sehen kann).
        // todo: boolean sendVelocityUpdates noch einmal angucken.
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "unicorn"), EntityUnicorn.class, "unicorn", 0, GaymerCraft.instance, 80, 3, true, 0xEFB1B1, 0x674EA7);
    }
}