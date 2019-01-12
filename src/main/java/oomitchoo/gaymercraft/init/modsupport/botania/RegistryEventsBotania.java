package oomitchoo.gaymercraft.init.modsupport.botania;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import oomitchoo.gaymercraft.block.modsupport.botania.EnumVariantSlabBotania;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 12.01.2019.
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class RegistryEventsBotania {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        if (Reference.ModSupport.isBotaniaLoaded) {
            IForgeRegistry<Block> r = event.getRegistry();
            for (int i = 0; i < EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero(); i++) {
                r.register(ModBlocks.BOTANIA_SLABS[i]);
                r.register(ModBlocks.DOUBLE_BOTANIA_SLABS[i]);
            }
        }
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        if (Reference.ModSupport.isBotaniaLoaded) {
            IForgeRegistry<Item> r = event.getRegistry();
            for (int i = 0; i < EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero(); i++) {
                r.register(ModBlocks.ITEMBLOCK_BOTANIA_SLABS[i]);
            }
        }
    }
}