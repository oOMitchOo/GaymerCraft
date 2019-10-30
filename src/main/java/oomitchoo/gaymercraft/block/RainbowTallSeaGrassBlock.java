package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.IWorldReader;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;

import javax.annotation.Nullable;

public class RainbowTallSeaGrassBlock extends ShearableDoublePlantBlock implements ILiquidContainer {
    public static final EnumProperty<DoubleBlockHalf> BLOCK_HALF = ShearableDoublePlantBlock.PLANT_HALF;
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public RainbowTallSeaGrassBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)(BlockState) this.stateContainer.getBaseState().with(BLOCK_HALF, DoubleBlockHalf.LOWER).with(FLUID, ColoredWaterlogged.VANILLA));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.func_224755_d(worldIn, pos, Direction.UP) && (state.getBlock() != Blocks.MAGMA_BLOCK && state.getBlock() != ModBlocks.RAINBOW_MAGMA_BLOCK);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{BLOCK_HALF, FLUID});
    }

    // todo: change this
    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.RAINBOW_SEAGRASS);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        if (blockstate != null) {
            IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos().up());
            ColoredWaterlogged waterType = ColoredWaterlogged.byFluid(ifluidstate.getFluid());
            // Fluids.EMPTY is not in FluidTags.Water. So, everything is fine.
            if (ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8) {
                return blockstate.with(FLUID, waterType);
            }
        }
        return null;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if (state.get(BLOCK_HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockstate = worldIn.getBlockState(pos.down());
            return blockstate.getBlock() == this && blockstate.get(BLOCK_HALF) == DoubleBlockHalf.LOWER;
        } else {
            IFluidState ifluidstate = worldIn.getFluidState(pos);
            return super.isValidPosition(state, worldIn, pos) && ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8;
        }
    }

    public IFluidState getFluidState(BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);
        // This if should never be true, but just to make sure (Debug-Tool).
        if(waterType == ColoredWaterlogged.EMPTY) {
            return waterType.getFluid().getDefaultState();
        }
        return ((FlowingFluid) waterType.getFluid()).getStillFluidState(false);
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        return false;
    }
}
