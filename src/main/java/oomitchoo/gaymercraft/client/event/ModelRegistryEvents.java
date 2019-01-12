package oomitchoo.gaymercraft.client.event;

import net.minecraft.block.BlockPlanks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab2;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab2;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 30.12.2018.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class ModelRegistryEvents {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        // ITEM-MODELS
        registerItemRenderer(ModItems.RAINBOW_STAR, 0, "_loaded");
        registerItemRenderer(ModItems.RAINBOW_STAR, 1, "_unloaded");

        // ITEMBLOCK-MODELS VERT SLABS
        for (BlockStoneVertSlab1.EnumType blockslabs$enumtype : BlockStoneVertSlab1.EnumType.values()) {
            registerItemRenderer(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_1, blockslabs$enumtype.getMetadata(), "_" + blockslabs$enumtype.getName());
        }
        for (BlockStoneVertSlab2.EnumType blockslabs$enumtype : BlockStoneVertSlab2.EnumType.values()) {
            registerItemRenderer(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_2, blockslabs$enumtype.getMetadata(), "_" + blockslabs$enumtype.getName());
        }
        for (BlockWoodVertSlab1.EnumType blockslabs$enumtype : BlockWoodVertSlab1.EnumType.values()) {
            registerItemRenderer(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_1, blockslabs$enumtype.getMetadata(), "_" + blockslabs$enumtype.getName());
        }
        for (BlockWoodVertSlab2.EnumType blockslabs$enumtype : BlockWoodVertSlab2.EnumType.values()) {
            registerItemRenderer(ModBlocks.ITEMBLOCK_WOOD_VERT_SLAB_2, blockslabs$enumtype.getMetadata(), "_" + blockslabs$enumtype.getName());
        }
        registerItemRenderer(ModBlocks.ITEMBLOCK_STONE_VERT_SLAB_NEW, 0, "");
        registerItemRenderer(ModBlocks.ITEMBLOCK_PURPUR_VERT_SLAB, 0, "");

        // ITEMBLOCK MODELS HEDGE
        for (BlockPlanks.EnumType blockleaves$enumtype : BlockPlanks.EnumType.values()) {
            registerItemRenderer(ModBlocks.ITEMBLOCK_HEDGE, blockleaves$enumtype.getMetadata(), "_" + blockleaves$enumtype.getName());
        }

        // ============================ TESTING / DEBUGGING HELPER ============================

        if (Reference.isDevEnvironment)
            registerItemRenderer(ModItems.DEBUG_TOOL, 0, "");
    }

    // ================= HELPING METHODS ============================================================================================

    private static void registerItemRenderer(Item item, int metaData, String regNameAddition) {
        ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(item.getRegistryName()+regNameAddition, "inventory"));
    }
}