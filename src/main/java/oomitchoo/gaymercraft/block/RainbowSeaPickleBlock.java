package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;

import javax.annotation.Nullable;
import java.util.Random;

public class RainbowSeaPickleBlock extends BushBlock implements IGrowable, IBucketPickupHandler, ILiquidContainer {
    public static final IntegerProperty PICKLES = BlockStateProperties.PICKLES_1_4;
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    private static final VoxelShape ONE_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
    private static final VoxelShape TWO_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
    private static final VoxelShape THREE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    private static final VoxelShape FOUR_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);

    public RainbowSeaPickleBlock(Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(PICKLES, 1)).with(FLUID, ColoredWaterlogged.VANILLA));
    }

    public int getLightValue(BlockState state) {
        return this.isInBadEnvironment(state) ? 0 : super.getLightValue(state) + 3 * (Integer)state.get(PICKLES);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        if (state.getBlock() == this) {
            return (BlockState)state.with(PICKLES, Math.min(4, (Integer)state.get(PICKLES) + 1));
        } else {
            IFluidState fluidState = context.getWorld().getFluidState(context.getPos());
            boolean isHighWater = fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8;
            if(isHighWater) {
                return (BlockState)super.getStateForPlacement(context).with(FLUID, ColoredWaterlogged.byFluid(fluidState.getFluid()));
            } else {
                return (BlockState)super.getStateForPlacement(context).with(FLUID, ColoredWaterlogged.EMPTY);
            }
        }
    }

    /**
     * Basically checks if PickleBlock is on land, which is BadEnvironment for a water creature...
     */
    private boolean isInBadEnvironment(BlockState state) { return state.get(FLUID) == ColoredWaterlogged.EMPTY; }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos) {
        return !state.getCollisionShape(world, pos).project(Direction.UP).isEmpty();
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos posDown = pos.down();
        return this.isValidGround(world.getBlockState(posDown), world, posDown);
    }

    @Override
    public BlockState updatePostPlacement(BlockState placementState, Direction direction, BlockState state, IWorld world, BlockPos pos1, BlockPos pos2) {
        if (!placementState.isValidPosition(world, pos1)) {
            return Blocks.AIR.getDefaultState();
        } else {
            ColoredWaterlogged waterType = placementState.get(FLUID);
            if (!(waterType == ColoredWaterlogged.EMPTY)) {
                Fluid stateFluid = waterType.getFluid();
                world.getPendingFluidTicks().scheduleTick(pos1, stateFluid, stateFluid.getTickRate(world));
            }

            return super.updatePostPlacement(placementState, direction, state, world, pos1, pos2);
        }
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
        return context.getItem().getItem() == this.asItem() && (Integer)state.get(PICKLES) < 4 ? true : super.isReplaceable(state, context);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        switch((Integer)state.get(PICKLES)) {
            case 1:
            default:
                return ONE_SHAPE;
            case 2:
                return TWO_SHAPE;
            case 3:
                return THREE_SHAPE;
            case 4:
                return FOUR_SHAPE;
        }
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        ColoredWaterlogged waterType = state.get(FLUID);

        if(waterType == ColoredWaterlogged.EMPTY) {
            return waterType.getFluid().getDefaultState();
        }
        return ((FlowingFluid) waterType.getFluid()).getStillFluidState(false);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{PICKLES, FLUID});
    }

    @Override
    public boolean canGrow(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return true;
    }

    public void grow(World world, Random rand, BlockPos pos, BlockState state) {
        if (!this.isInBadEnvironment(state) && world.getBlockState(pos.down()).isIn(BlockTags.CORAL_BLOCKS)) {
            int zMax = 1;
            int countTo2 = 0;
            int xLevelMinus2 = pos.getX() - 2;
            int zVariable = 0;

            for(int x = 0; x < 5; ++x) {
                for(int z = 0; z < zMax; ++z) {
                    int yLevelPlus1 = 2 + pos.getY() - 1;

                    for(int y = yLevelPlus1 - 2; y < yLevelPlus1; ++y) {
                        BlockPos posToGrowTo = new BlockPos(xLevelMinus2 + x, y, pos.getZ() - zVariable + z);
                        if (posToGrowTo != pos && rand.nextInt(6) == 0 && world.getBlockState(posToGrowTo).getBlock() instanceof FlowingFluidBlock && world.getFluidState(posToGrowTo).isTagged(FluidTags.WATER)) {
                            BlockState stateToGrowOn = world.getBlockState(posToGrowTo.down());
                            if (stateToGrowOn.isIn(BlockTags.CORAL_BLOCKS)) {
                                // If FlowingFluidBlock is another Mods FluidBlock, ColoredWaterlogged.byFluid() will be VANILLA, which is fine, I guess.
                                world.setBlockState(posToGrowTo, (BlockState) ModBlocks.RAINBOW_SEA_PICKLE.getDefaultState().with(PICKLES, rand.nextInt(4) + 1)
                                        .with(FLUID, ColoredWaterlogged.byFluid(world.getFluidState(posToGrowTo).getFluid())), 3);
                            }
                        }
                    }
                }

                if (countTo2 < 2) {
                    zMax += 2;
                    ++zVariable;
                } else {
                    zMax -= 2;
                    --zVariable;
                }

                ++countTo2;
            }

            world.setBlockState(pos, (BlockState)state.with(PICKLES, 4), 2);
        }
    }

    //============ ILiquidContainer, IBucketPickupHandler

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
            if(fluid == Fluids.WATER || (waterType != ColoredWaterlogged.EMPTY && waterType != ColoredWaterlogged.VANILLA)) {
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
