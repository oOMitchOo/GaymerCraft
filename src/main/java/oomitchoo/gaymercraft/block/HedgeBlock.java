package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oomitchoo.gaymercraft.reference.Reference;

public class HedgeBlock extends FourWayBlock {
    private final VoxelShape[] renderShapes;
    protected static boolean renderTranslucent = true; //todo: unset this here and build a method where it is set at start and every time the config is changed...

    public HedgeBlock(String name) {
        super(6.0F, 6.0F, 16.0F, 16.0F, 24.0F, Block.Properties.create(Material.LEAVES, MaterialColor.FOLIAGE).hardnessAndResistance(0.2F).sound(SoundType.PLANT));
        this.setRegistryName(Reference.MOD_ID, name);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateContainer.getBaseState()).with(NORTH, false)).with(EAST, false)).with(SOUTH, false)).with(WEST, false)).with(WATERLOGGED, false));
        this.renderShapes = this.makeShapes(6.0F, 6.0F, 16.0F, 6.0F, 16.0F); //todo: It seems as if I've guessed the makeShape-values right, but I should check them again, if method is clearer in later mappings.
    }

    @Override
    public VoxelShape getRenderShape(BlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return this.renderShapes[this.getIndex(p_196247_1_)];
    }

    @Override
    public boolean allowsMovement(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }

    /**
     * I think this function determines to what the hedge can connect. It returns TRUE if canAttach.
     */
    public boolean canConnectTo(BlockState blockState, boolean hasSolidSide, Direction direction) {
        Block block = blockState.getBlock();
        boolean isHedgeOrLeaves = block instanceof HedgeBlock || block.isIn(BlockTags.LEAVES);
        boolean isGate = block instanceof FenceGateBlock && FenceGateBlock.isParallel(blockState, direction);
        return !cannotAttach(block) && hasSolidSide || isHedgeOrLeaves || isGate;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext) {
        IBlockReader world = blockItemUseContext.getWorld();
        BlockPos blockPos = blockItemUseContext.getPos();
        IFluidState fluidState = blockItemUseContext.getWorld().getFluidState(blockItemUseContext.getPos());
        BlockPos blockPosNorth = blockPos.north();
        BlockPos blockPosEast = blockPos.east();
        BlockPos blockPosSouth = blockPos.south();
        BlockPos blockPosWest = blockPos.west();
        BlockState blockStateNorth = world.getBlockState(blockPosNorth);
        BlockState blockStateEast = world.getBlockState(blockPosEast);
        BlockState blockStateSouth = world.getBlockState(blockPosSouth);
        BlockState blockStateWest = world.getBlockState(blockPosWest);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(blockItemUseContext).with(NORTH, this.canConnectTo(blockStateNorth, blockStateNorth.func_224755_d(world, blockPosNorth, Direction.SOUTH), Direction.SOUTH))).with(EAST, this.canConnectTo(blockStateEast, blockStateEast.func_224755_d(world, blockPosEast, Direction.WEST), Direction.WEST))).with(SOUTH, this.canConnectTo(blockStateSouth, blockStateSouth.func_224755_d(world, blockPosSouth, Direction.NORTH), Direction.NORTH))).with(WEST, this.canConnectTo(blockStateWest, blockStateWest.func_224755_d(world, blockPosWest, Direction.EAST), Direction.EAST))).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if ((Boolean)p_196271_1_.get(WATERLOGGED)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        return p_196271_2_.getAxis().getPlane() == Direction.Plane.HORIZONTAL ? (BlockState)p_196271_1_.with((IProperty)FACING_TO_PROPERTY_MAP.get(p_196271_2_), this.canConnectTo(p_196271_3_, p_196271_3_.func_224755_d(p_196271_4_, p_196271_6_, p_196271_2_.getOpposite()), p_196271_2_.getOpposite())) : super.updatePostPlacement(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return renderTranslucent ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        Block adjacentBlock = adjacentBlockState.getBlock();

        // sideIndex > 1 excludes UP and DOWN.
        return side.getIndex() > 1 && (adjacentBlock instanceof HedgeBlock || adjacentBlock.isIn(BlockTags.LEAVES));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{NORTH, EAST, WEST, SOUTH, WATERLOGGED});
    }
}
