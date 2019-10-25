package oomitchoo.gaymercraft.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import oomitchoo.gaymercraft.client.BlockColorsHedge;
import oomitchoo.gaymercraft.client.ItemColorsHedge;
import oomitchoo.gaymercraft.client.renderer.entity.UnicornRenderer;
import oomitchoo.gaymercraft.entity.UnicornEntity;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.init.ModItems;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerColoredBlocks() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        IBlockColor blockColorsHedge = BlockColorsHedge.getInstance();

        blockColors.register(blockColorsHedge, ModBlocks.OAK_HEDGE);
        blockColors.register(blockColorsHedge, ModBlocks.SPRUCE_HEDGE);
        blockColors.register(blockColorsHedge, ModBlocks.BIRCH_HEDGE);
        blockColors.register(blockColorsHedge, ModBlocks.JUNGLE_HEDGE);
        blockColors.register(blockColorsHedge, ModBlocks.ACACIA_HEDGE);
        blockColors.register(blockColorsHedge, ModBlocks.DARK_OAK_HEDGE);
    }

    @Override
    public void registerColoredItems() {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();
        IItemColor itemColorsHedge = ItemColorsHedge.getInstance();

        itemColors.register(itemColorsHedge, ModItems.OAK_HEDGE_BOCKITEM);
        itemColors.register(itemColorsHedge, ModItems.SPRUCE_HEDGE_BOCKITEM);
        itemColors.register(itemColorsHedge, ModItems.BIRCH_HEDGE_BOCKITEM);
        itemColors.register(itemColorsHedge, ModItems.JUNGLE_HEDGE_BOCKITEM);
        itemColors.register(itemColorsHedge, ModItems.ACACIA_HEDGE_BOCKITEM);
        itemColors.register(itemColorsHedge, ModItems.DARK_OAK_HEDGE_BOCKITEM);
    }

    @Override
    public void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(UnicornEntity.class, new UnicornRenderer.RenderFactory());
    }
}
