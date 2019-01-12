package oomitchoo.gaymercraft.client.event.modsupport.botania;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import oomitchoo.gaymercraft.block.modsupport.botania.EnumVariantSlabBotania;
import oomitchoo.gaymercraft.init.modsupport.botania.ModBlocks;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 12.01.2019.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ModelRegistryEventsBotania {
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        if (Reference.ModSupport.isBotaniaLoaded) {
            int indexBigVariantCollection = 0;

            for (int i = 0; i < EnumVariantSlabBotania.getNumberOfVariantsWithMetaZero(); i++) {
                int numberOfSubBlocks = ModBlocks.ITEMBLOCK_BOTANIA_SLABS[i].getNumberOfVariants();

                for (int meta = 0; meta < numberOfSubBlocks; meta++) {
                    registerItemRenderer(ModBlocks.ITEMBLOCK_BOTANIA_SLABS[i], meta, "_" + EnumVariantSlabBotania.byIndex(indexBigVariantCollection + meta).getName());
                }

                indexBigVariantCollection = indexBigVariantCollection + numberOfSubBlocks;
            }
        }
    }

    // ================= HELPING METHODS ============================================================================================

    private static void registerItemRenderer(Item item, int metaData, String regNameAddition) {
        ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(item.getRegistryName()+regNameAddition, "inventory"));
    }
}