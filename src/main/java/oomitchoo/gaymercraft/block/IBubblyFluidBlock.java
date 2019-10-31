package oomitchoo.gaymercraft.block;

/**
 * This interface is thought to be implemented by a modded FlowingFluidBlock.
 */
public interface IBubblyFluidBlock {
    /**
     *  ICanPlaceBubbleColumn should ideally be the BubbleColumnBlock, which then can place itself with the methods in ICanPlaceBubbleColumn.
     */
    ICanPlaceBubbleColumn getBubbleColumnPlacer();
}
