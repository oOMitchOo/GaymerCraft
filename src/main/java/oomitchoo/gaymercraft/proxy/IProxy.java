package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public interface IProxy {
    void registerItemRendererNoSubt(Item item, int metaData, String regNameAddition, String id);
    void registerItemRendererWithSubt(ItemBlock itemBlock, String id);
    void mapFluidState(Block block, Fluid fluid);
    void registerEntityRenderer();
    void registerColoredBlocks();
    void registerColoredItems();
    void drawLinesForVertSlabPlacement(EntityPlayer player, IBlockState targetBlock, BlockPos blockpos, EnumFacing sideHit, boolean isVertSlab, float partialTicks);
}
