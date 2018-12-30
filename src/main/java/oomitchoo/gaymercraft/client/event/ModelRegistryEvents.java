package oomitchoo.gaymercraft.client.event;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 30.12.2018.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ModelRegistryEvents {

    // todo: Needs some tidy up. I don't think, I need to use a proxy here, since it already is in the client package (plus EventBusSubscriber value is Side.Client).
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        // ITEM-MODELS
        GaymerCraft.proxy.registerItemRendererNoSubt(ModItems.RAINBOW_STAR, 0, "_loaded", "inventory");
        GaymerCraft.proxy.registerItemRendererNoSubt(ModItems.RAINBOW_STAR, 1, "_unloaded", "inventory");
        // ITEMBLOCK-MODELS
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_1, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_2, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_1, "inventory");
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_2, "inventory");
        GaymerCraft.proxy.registerItemRendererNoSubt(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_NEW, 0, "", "inventory");
        GaymerCraft.proxy.registerItemRendererNoSubt(ModBlocks.ITEMBLOCK_PURPUR_VERT_SLAB, 0, "", "inventory");
        // ITEMBLOCK MODELS HEDGE
        GaymerCraft.proxy.registerItemRendererWithSubt(ModBlocks.ITEMBLOCK_HEDGE, "inventory");
    }
}