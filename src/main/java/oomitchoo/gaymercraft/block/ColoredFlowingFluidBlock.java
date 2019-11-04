package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class ColoredFlowingFluidBlock extends FlowingFluidBlock implements IBubblyFluidBlock {
    private final Supplier<? extends Block> bubbleBlockSupplier;

    public ColoredFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties, Supplier<? extends Block> bubbleBlockSupplier) {
        super(supplier, properties);
        this.bubbleBlockSupplier = bubbleBlockSupplier;
    }

    public ICanPlaceBubbleColumn getBubbleColumnPlacer() {
        return (ICanPlaceBubbleColumn) this.bubbleBlockSupplier.get();
    }
}
