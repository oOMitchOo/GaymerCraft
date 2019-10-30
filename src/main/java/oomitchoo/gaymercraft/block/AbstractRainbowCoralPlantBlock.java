package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;

import javax.annotation.Nullable;

public class AbstractRainbowCoralPlantBlock extends Block implements IBucketPickupHandler, ILiquidContainer {
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    private static final VoxelShape field_212559_a = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);

    protected AbstractRainbowCoralPlantBlock(Block.Properties p_i49810_1_) {
        super(p_i49810_1_);
        this.setDefaultState(this.stateContainer.getBaseState().with(FLUID, ColoredWaterlogged.VANILLA));
    }

    protected void updateIfDry(BlockState state, IWorld worldIn, BlockPos pos) {
        if (!isInWater(state, worldIn, pos)) {
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60 + worldIn.getRandom().nextInt(40));
        }

    }

    protected static boolean isInWater(BlockState state, IBlockReader worldIn, BlockPos pos) {
        if (state.get(FLUID) != ColoredWaterlogged.EMPTY) {
            return true;
        } else {
            // Does this mean it is good enough for corals being beside water? Even flowing water?
            for(Direction direction : Direction.values()) {
                if (worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
        boolean isHighWater = fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8;
        if(isHighWater) {
            return (BlockState)this.getDefaultState().with(FLUID, ColoredWaterlogged.byFluid(fluidState.getFluid()));
        } else {
            return (BlockState)this.getDefaultState().with(FLUID, ColoredWaterlogged.EMPTY);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return field_212559_a;
    }

    /**
     * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
     * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
     */
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Fluid stateInFluid = stateIn.get(FLUID).getFluid();

        if(stateInFluid != Fluids.EMPTY) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, stateInFluid, stateInFluid.getTickRate(worldIn));
        }

        return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        return worldIn.getBlockState(blockpos).func_224755_d(worldIn, blockpos, Direction.UP);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{FLUID});
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);

        if(waterType == ColoredWaterlogged.EMPTY) {
            return waterType.getFluid().getDefaultState();
        }
        return ((FlowingFluid) waterType.getFluid()).getStillFluidState(false);
    }

    // ================= IBucketPickupHandler, ILiquidContainer

    // This method is used, before placing a Fluid into this Block by bucket... but also when a fluid is flowing into this blockPos.
    // Deshalb ist der check hier einigermaßen kompliziert. Da ein check in ColoredWaterlogged.byFluid() immer VANILLA ausgibt, wenn wir nach
    // einer flowing Fluid suchen (nicht die normale stille Version).
    @Override
    public boolean canContainFluid(IBlockReader world, BlockPos pos, BlockState state, Fluid fluid) {
        if (state.get(FLUID) == ColoredWaterlogged.EMPTY) { // Nur in Empty ist true möglich
            if(fluid == Fluids.WATER) {
                return true;
            }
            // Since it only spits out Vanilla, when it is an unknown Fluid (or vanilla, which was already tested in last if), this is a good test.
            // I can't test for the colored_water Tag here, since also the flowing versions will be found.
            return ColoredWaterlogged.byFluid(fluid) != ColoredWaterlogged.VANILLA;
        }
        return false;
    }

    @Override
    public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, IFluidState iFluidState) {
        if(state.get(FLUID) == ColoredWaterlogged.EMPTY) {
            Fluid fluid = iFluidState.getFluid();
            ColoredWaterlogged waterType = ColoredWaterlogged.byFluid(fluid);
            // Again, it is NOT vanilla, when any colored Fluid (not flowingFluid and we're testing against empty) was found.
            if(fluid == Fluids.WATER || (!fluid.isEmpty() && waterType != ColoredWaterlogged.VANILLA)) {
                if(!world.isRemote()) {
                    world.setBlockState(pos, state.with(FLUID, waterType), 3);
                    world.getPendingFluidTicks().scheduleTick(pos, fluid, fluid.getTickRate(world));
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public Fluid pickupFluid(IWorld world, BlockPos pos, BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);

        if(waterType != ColoredWaterlogged.EMPTY) {
            world.setBlockState(pos, state.with(FLUID, ColoredWaterlogged.EMPTY), 3);
            return waterType.getFluid();
        } else {
            return Fluids.EMPTY;
        }
    }
}
