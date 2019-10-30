package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
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

public class RainbowKelpTopBlock extends Block implements ILiquidContainer {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_25;
    public static final EnumProperty<ColoredWaterlogged> FLUID = ModBlockStateProperties.COLORED_WATERLOGGED;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public RainbowKelpTopBlock(Block.Properties builder) {
        super(builder);
        this.setDefaultState((BlockState)(BlockState) this.stateContainer.getBaseState().with(FLUID, ColoredWaterlogged.VANILLA).with(AGE, Integer.valueOf(0)));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        // If it would be placed in a fluid other than vanilla water or colored water, ColoredWaterlogged.byFluid() would return VANILLA.
        ColoredWaterlogged waterType = ColoredWaterlogged.byFluid(ifluidstate.getFluid());
        // Fluids.Empty is not in FluidTags.Water
        return ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8 ? this.randomAge(context.getWorld()).with(FLUID, waterType) : null;
    }

    public BlockState randomAge(IWorld p_209906_1_) {
        return this.getDefaultState().with(AGE, Integer.valueOf(p_209906_1_.getRandom().nextInt(25)));
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

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        } else {
            BlockPos posUp = pos.up();
            BlockState blockAbove = worldIn.getBlockState(posUp);
            IFluidState iFluidStateAbove = worldIn.getFluidState(posUp);
            // When placed by hand, kelp has a random age. It then grows (with a chance) as long, as there's water above, and the age is < 25
            // Cycle will make it older each time until it stops growing. Which means each kelp will have a random growing height based on their initial
            // starting age between 0 and 24... and it has a chance of 14% to grow each tick? That's quite much, isn't it? How often does it tick per second?
            if (blockAbove.getBlock() instanceof FlowingFluidBlock && iFluidStateAbove.isTagged(FluidTags.WATER) && state.get(AGE) < 25 && random.nextDouble() < 0.14D) {
                worldIn.setBlockState(posUp, state.cycle(AGE).with(FLUID, ColoredWaterlogged.byFluid(iFluidStateAbove.getFluid())));
            }
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        // todo: import Blocks, if everything else was checked.
        if (block == Blocks.MAGMA_BLOCK || block == ModBlocks.RAINBOW_MAGMA_BLOCK) {
            return false;
        } else {
            return block == this || block == ModBlocks.RAINBOW_KELP_PLANT || blockstate.func_224755_d(worldIn, blockpos, Direction.UP);
        }
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        // facing is in which direction of this block the facingState lies in.
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            if (facing == Direction.DOWN) {
                return Blocks.AIR.getDefaultState();
            }

            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        Fluid stateInFluid = stateIn.get(FLUID).getFluid();

        // It checks the Block above itself. If it is a topKelp this one changes to a normal Kelp (KELP_PLANT)
        if (facing == Direction.UP && facingState.getBlock() == this) {
            return ModBlocks.RAINBOW_KELP_PLANT.getDefaultState().with(FLUID, ColoredWaterlogged.byFluid(stateInFluid));
        } else {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, stateInFluid, stateInFluid.getTickRate(worldIn));
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{AGE, FLUID});
    }

    @Override
    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return false;
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        return false;
    }
}
