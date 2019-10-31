package oomitchoo.gaymercraft.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

/**
 * This is thought to be implemented by your own BubbleColumnBlock (for you own Fluid).
 * Don't forget to implement IBubblyFluidBlock with your FlowingFluidBlock.
 */
public interface ICanPlaceBubbleColumn {
    void placeBubbleColumn(IWorld world, BlockPos pos, boolean drag);
    boolean canHoldBubbleColumn(IWorld world, BlockPos pos);
}
