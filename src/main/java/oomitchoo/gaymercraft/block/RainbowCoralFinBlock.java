package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.state.properties.ColoredWaterlogged;

import java.util.Random;

public class RainbowCoralFinBlock extends RainbowCoralFanBlock {
    private final Block deadBlock;

    public RainbowCoralFinBlock(Block deadCoralFanBlock, Block.Properties builder) {
        super(builder);
        this.deadBlock = deadCoralFanBlock;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        this.updateIfDry(state, worldIn, pos);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!isInWater(state, worldIn, pos)) {
            worldIn.setBlockState(pos, this.deadBlock.getDefaultState().with(FLUID, ColoredWaterlogged.EMPTY), 2);
        }
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        } else {
            this.updateIfDry(stateIn, worldIn, currentPos);
            ColoredWaterlogged waterType = stateIn.get(FLUID);
            if (waterType != ColoredWaterlogged.EMPTY) {
                Fluid stateInFluid = waterType.getFluid();
                worldIn.getPendingFluidTicks().scheduleTick(currentPos, stateInFluid, stateInFluid.getTickRate(worldIn));
            }

            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }
}
