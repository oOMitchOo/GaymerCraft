package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;

import java.util.Random;
import java.util.function.Supplier;

public class RainbowKelpBlock extends Block implements ILiquidContainer {
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    private final Supplier<? extends Block> topBlockSupplier;

    public RainbowKelpBlock(Supplier<? extends Block> topBlockSupplier, Block.Properties properties) {
        super(properties);
        this.topBlockSupplier = topBlockSupplier;
        this.setDefaultState((BlockState)(BlockState) this.stateContainer.getBaseState().with(FLUID, ColoredWaterlogged.VANILLA));
    }

    /**
     * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
     * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
     */
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);
        // This if should never be true, but just to make sure (Debug-Tool).
        if(waterType == ColoredWaterlogged.EMPTY) {
            return waterType.getFluid().getDefaultState();
        }
        return ((FlowingFluid) waterType.getFluid()).getStillFluidState(false);
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }

        super.tick(state, worldIn, pos, random);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        Fluid stateInFluid = stateIn.getFluidState().getFluid();

        if (facing == Direction.UP) {
            Block blockUp = facingState.getBlock();
            // This basically turns this block into a topKep, if it hasn't a kelp or topKelp above it.
            if (blockUp != this && blockUp != this.getTopKelpBlock()) {
                return ((RainbowKelpTopBlock) this.getTopKelpBlock()).randomAge(worldIn).with(FLUID, ColoredWaterlogged.byFluid(stateInFluid));
            }
        }

        worldIn.getPendingFluidTicks().scheduleTick(currentPos, stateInFluid, stateInFluid.getTickRate(worldIn));
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos posDown = pos.down();
        BlockState stateDown = worldIn.getBlockState(posDown);
        Block blockDown = stateDown.getBlock();
        return blockDown != Blocks.MAGMA_BLOCK && blockDown != ModBlocks.RAINBOW_MAGMA_BLOCK && (blockDown == this || stateDown.func_224755_d(worldIn, posDown, Direction.UP));
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(this.getTopKelpBlock());
    }

    public Block getTopKelpBlock() { return this.topBlockSupplier.get(); }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{FLUID});
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        return false;
    }
}
