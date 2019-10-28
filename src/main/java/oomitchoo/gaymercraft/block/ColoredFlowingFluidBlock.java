package oomitchoo.gaymercraft.block;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;

import java.util.function.Supplier;

public class ColoredFlowingFluidBlock extends FlowingFluidBlock {
    private final ColoredBubbleColumnBlock bubbleBlock;

    public ColoredFlowingFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties, ColoredBubbleColumnBlock bubbleBlock) {
        super(supplier, properties);
        this.bubbleBlock = bubbleBlock;
    }

    public ColoredBubbleColumnBlock getBubbleBlock() {
        return this.bubbleBlock;
    }
}
