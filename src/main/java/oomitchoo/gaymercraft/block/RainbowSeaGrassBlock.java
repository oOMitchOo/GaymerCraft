package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;

import javax.annotation.Nullable;
import java.util.Random;

public class RainbowSeaGrassBlock extends BushBlock implements IGrowable, ILiquidContainer, net.minecraftforge.common.IShearable {
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public RainbowSeaGrassBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState((BlockState) this.getDefaultState().with(FLUID, ColoredWaterlogged.VANILLA));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{FLUID});
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.func_224755_d(worldIn, pos, Direction.UP) && (state.getBlock() != Blocks.MAGMA_BLOCK && state.getBlock() != ModBlocks.RAINBOW_MAGMA_BLOCK);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        // If it would be placed in a fluid other than vanilla water or colored water, ColoredWaterlogged.byFluid() would return VANILLA.
        ColoredWaterlogged waterType = ColoredWaterlogged.byFluid(ifluidstate.getFluid());
        // Fluids.Empty is not in FluidTags.Water
        return ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8 ? this.getDefaultState().with(FLUID, waterType) : null;
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockstate = super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        if (!blockstate.isAir()) {
            Fluid fluidAtCurrentPos = worldIn.getFluidState(currentPos).getFluid();
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, fluidAtCurrentPos, fluidAtCurrentPos.getTickRate(worldIn));
        }
        return blockstate;
    }

    /**
     * Whether this IGrowable can grow
     */
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    public IFluidState getFluidState(BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);
        // This if should never be true, but just to make sure (Debug-Tool).
        if(waterType == ColoredWaterlogged.EMPTY) {
            return waterType.getFluid().getDefaultState();
        }
        return ((FlowingFluid) waterType.getFluid()).getStillFluidState(false);
    }

    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos posUp = pos.up();
        IFluidState iFluidState = worldIn.getFluidState(posUp);

        BlockState blockstate = ModBlocks.RAINBOW_TALL_SEAGRASS.getDefaultState().with(FLUID, state.get(FLUID));
        BlockState blockstate1 = blockstate.with(RainbowTallSeaGrassBlock.BLOCK_HALF, DoubleBlockHalf.UPPER);

        // Searching for iFluidState.isTagged alone here is not enough. Since waterlogged Blocks would return true too, but we want a water block here.
        // todo: Maybe check, if the FlowingFluidBlock is full?
        if (worldIn.getBlockState(posUp).getBlock() instanceof FlowingFluidBlock && iFluidState.isTagged(FluidTags.WATER)) {
            // If the FlowingFluidBlock where the Seagrass is growing into is a fluid from another Mod, ColoredWaterlogged.byFluid() would return VANILLA.
            // ... which is fine, I guess.
            blockstate1 = blockstate1.with(FLUID, ColoredWaterlogged.byFluid(iFluidState.getFluid()));
            worldIn.setBlockState(pos, blockstate, 2);
            worldIn.setBlockState(posUp, blockstate1, 2);
        }
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        return false;
    }
}
