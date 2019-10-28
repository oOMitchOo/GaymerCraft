package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import oomitchoo.gaymercraft.init.ModBlocks;

import java.util.Random;

public class ColoredBubbleColumnBlock extends Block implements IBucketPickupHandler {
    public static final BooleanProperty DRAG = BlockStateProperties.DRAG;
    private final FlowingFluid fluid;
    // todo: The water block always seems to be null, if I want to give it (because it wasn't registered yet? Or because it has no regName yet?) That's why I used the ResourceLocation instead. Find something more elegant?
    // todo: One idea is to get the block from the block supplier saved in ForgeFlowingFluid? I need to extend the class for that and write a method to get the Block out of it.
    private final ResourceLocation waterBlockLocation;

    public ColoredBubbleColumnBlock(net.minecraft.block.Block.Properties properties, FlowingFluid fluid, ResourceLocation waterBlockLocation) {
        super(properties);
        this.fluid = fluid;
        this.waterBlockLocation = waterBlockLocation;
        this.setDefaultState(this.stateContainer.getBaseState().with(DRAG, Boolean.valueOf(true)));
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        BlockState blockstate = worldIn.getBlockState(pos.up());
        if (blockstate.isAir()) {
            entityIn.onEnterBubbleColumnWithAirAbove(state.get(DRAG));
            if (!worldIn.isRemote) {
                ServerWorld serverworld = (ServerWorld)worldIn;

                for(int i = 0; i < 2; ++i) {
                    serverworld.spawnParticle(ParticleTypes.SPLASH, (double)((float)pos.getX() + worldIn.rand.nextFloat()), (double)(pos.getY() + 1), (double)((float)pos.getZ() + worldIn.rand.nextFloat()), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    serverworld.spawnParticle(ParticleTypes.BUBBLE, (double)((float)pos.getX() + worldIn.rand.nextFloat()), (double)(pos.getY() + 1), (double)((float)pos.getZ() + worldIn.rand.nextFloat()), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        } else {
            entityIn.onEnterBubbleColumn(state.get(DRAG));
        }

    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos.down()));
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        placeBubbleColumn(worldIn, pos.up(), getDrag(worldIn, pos));
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return fluid.getStillFluidState(false);
    }

    public void placeBubbleColumn(IWorld world, BlockPos pos, boolean drag) {
        if (canHoldBubbleColumn(world, pos)) {
            world.setBlockState(pos, this.getDefaultState().with(DRAG, Boolean.valueOf(drag)), 2);
        }
    }

    public boolean canHoldBubbleColumn(IWorld world, BlockPos pos) {
        IFluidState ifluidstate = world.getFluidState(pos);
        return world.getBlockState(pos).getBlock() == ForgeRegistries.BLOCKS.getValue(waterBlockLocation) && ifluidstate.getLevel() >= 8 && ifluidstate.isSource();
    }

    private boolean getDrag(IBlockReader world, BlockPos pos) {
        BlockState blockstate = world.getBlockState(pos);
        Block block = blockstate.getBlock();
        if (block == this) {
            return blockstate.get(DRAG);
        } else {
            return block != ModBlocks.RAINBOW_SOUL_SAND;
        }
    }

    /**
     * How many world ticks before ticking
     */
    @Override
    public int tickRate(IWorldReader worldIn) {
        return 5;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
     * this method is unrelated to {randomTick} and {#needsRandomTick}, and will always be called regardless
     * of whether the block can receive random update ticks
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        if (stateIn.get(DRAG)) {
            worldIn.addOptionalParticle(ParticleTypes.CURRENT_DOWN, d0 + 0.5D, d1 + 0.8D, d2, 0.0D, 0.0D, 0.0D);
            if (rand.nextInt(200) == 0) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        } else {
            worldIn.addOptionalParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
            worldIn.addOptionalParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)rand.nextFloat(), d1 + (double)rand.nextFloat(), d2 + (double)rand.nextFloat(), 0.0D, 0.04D, 0.0D);
            if (rand.nextInt(200) == 0) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 0.2F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F, false);
            }
        }

    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            return ForgeRegistries.BLOCKS.getValue(waterBlockLocation).getDefaultState();
        } else {
            if (facing == Direction.DOWN) {
                worldIn.setBlockState(currentPos, this.getDefaultState().with(DRAG, Boolean.valueOf(getDrag(worldIn, facingPos))), 2);
            } else if (facing == Direction.UP && facingState.getBlock() != this && canHoldBubbleColumn(worldIn, facingPos)) {
                worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
            }

            worldIn.getPendingFluidTicks().scheduleTick(currentPos, fluid, fluid.getTickRate(worldIn));
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos.down()).getBlock();
        return block == this || block == ModBlocks.RAINBOW_MAGMA_BLOCK || block == ModBlocks.RAINBOW_SOUL_SAND;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    /**
     * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
     * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
     */
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     * @deprecated call via {IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
     */
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    protected void fillStateContainer(StateContainer.Builder<net.minecraft.block.Block, BlockState> builder) {
        builder.add(DRAG);
    }

    @Override
    public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
        return fluid;
    }
}
