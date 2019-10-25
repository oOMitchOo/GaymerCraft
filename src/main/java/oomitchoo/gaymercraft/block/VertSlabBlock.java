package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import oomitchoo.gaymercraft.reference.Reference;
import oomitchoo.gaymercraft.state.properties.ModBlockStateProperties;
import oomitchoo.gaymercraft.state.properties.VertSlabType;

import javax.annotation.Nullable;

public class VertSlabBlock extends Block implements IWaterLoggable {
    public static final EnumProperty<VertSlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape EAST_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;

    public VertSlabBlock(String name, Properties properties) {
        super(properties);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(TYPE, VertSlabType.NORTH)).with(WATERLOGGED, false));
    }

    @Override // true if not double slab block. todo: Find out what exactly this function does.
    public boolean func_220074_n(BlockState blockState)  {
        return blockState.get(TYPE) != VertSlabType.DOUBLE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{TYPE, WATERLOGGED});
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VertSlabType type = (VertSlabType)state.get(TYPE);
        switch(type) {
            case DOUBLE:
                return VoxelShapes.fullCube();
            case EAST:
                return EAST_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return NORTH_SHAPE;
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        BlockState state = context.getWorld().getBlockState(pos);
        Double hitX = context.getHitVec().getX() - pos.getX(); // todo: Gucken, ob hitX und hitY so richtig definiert sind.
        Double hitZ = context.getHitVec().getZ() - pos.getZ();

        if (state.getBlock() == this) { // this is only for when the VertSlabBlock is replaced (see isReplaceable method)
            return (BlockState) ((BlockState) state.with(TYPE, VertSlabType.DOUBLE)).with(WATERLOGGED, false);
        } else {
            IFluidState fluidState = context.getWorld().getFluidState(pos);
            BlockState defaultState = (BlockState) ((BlockState) this.getDefaultState().with(TYPE, VertSlabType.NORTH)).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            Direction face = context.getFace();
            BlockState placingOn = context.getWorld().getBlockState(pos.offset(face.getOpposite())); // this gets the blockState on which face we're placing.

            // If player is placing on a non-double vertical slab, use its FACING. (for fast building of walls for example)
            if (placingOn.getBlock() == this) {
                VertSlabType type = (VertSlabType)placingOn.get(TYPE);
                if (type != VertSlabType.DOUBLE) {
                    // but only for the half sized faces
                    if (!face.getName().equals(type.getName()) && !face.getOpposite().getName().equals(type.getName()))
                        return defaultState.with(TYPE, type);
                }
            }


            switch (face) { // Abhängig welche Seite vom Block man anguckt.
                case NORTH:
                    if (hitX < 0.75D) {
                        if (hitX < 0.25D) {
                            return defaultState.with(TYPE, VertSlabType.WEST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return defaultState.with(TYPE, VertSlabType.SOUTH);
                        }
                    } else /* hitX >= 0.75 */ {
                        return defaultState.with(TYPE, VertSlabType.EAST);
                    }
                case SOUTH:
                    if (hitX < 0.75D) {
                        if (hitX < 0.25D) {
                            return defaultState.with(TYPE, VertSlabType.WEST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return defaultState; // NORTH
                        }
                    } else /* hitX >= 0.75 */ {
                        return defaultState.with(TYPE, VertSlabType.EAST);
                    }
                case WEST:
                    if (hitZ < 0.75D) {
                        if (hitZ < 0.25D) {
                            return defaultState; // NORTH
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return defaultState.with(TYPE, VertSlabType.EAST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return defaultState.with(TYPE, VertSlabType.SOUTH);
                    }
                case EAST:
                    if (hitZ < 0.75D) {
                        if (hitZ < 0.25D) {
                            return defaultState; // NORTH
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return defaultState.with(TYPE, VertSlabType.WEST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return defaultState.with(TYPE, VertSlabType.SOUTH);
                    }
                default: // Hierunter fallen case UP und DOWN (also beim platzieren an einer Ober- bzw. Unterseite eines Blocks.)
                    //  hitX and hitZ are between 0 and 1 and basically tell us which coordinate
                    //  of the face (which the block is being placed on) is right-clicked by the player.
                    //
                    //  SOUTH and WEST: hitX < hitZ             |   SOUTH and EAST: (1-hitX) < hitZ
                    //  NORTH and EAST: hitZ < hitX             |   NORTH and WEST: hitZ < (1-hitX)
                    //
                    //  NORTH: hitZ < hitX && hitZ < (1-hitX)   |   SOUTH: hitX < hitZ && (1-hitX) < hitZ
                    //  WEST: hitX < hitZ && hitZ < (1-hitX)    |   EAST: hitZ < hitX && (1-hitX) < hitZ

                    // else (look big comment section above)
                    if (hitX < hitZ) {
                        if ((1.0D - hitX) < hitZ) {
                            return defaultState.with(TYPE, VertSlabType.SOUTH);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return defaultState.with(TYPE, VertSlabType.WEST);
                        }
                    } else /* hitZ < hitX and unlikely event of hitX = hitZ */ {
                        if ((1.0D - hitX) < hitZ) {
                            return defaultState.with(TYPE, VertSlabType.EAST);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return defaultState; // NORTH
                        }
                    }
            }
        }
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
        ItemStack itemStack = context.getItem();
        VertSlabType type = (VertSlabType)state.get(TYPE);

        if (type != VertSlabType.DOUBLE && itemStack.getItem() == this.asItem()) {
            if (context.replacingClickedOnBlock()) {
                BlockPos pos = context.getPos();
                Vec3d hitVector = context.getHitVec();
                boolean isClickedMoreEast = hitVector.getX() - (double)pos.getX() > 0.5D;
                boolean isClickedMoreSouth = hitVector.getZ() - (double)pos.getZ() > 0.5D;
                Direction face = context.getFace();

                switch (type) {
                    case NORTH:
                        return face == Direction.SOUTH || (isClickedMoreSouth && (face == Direction.EAST || face == Direction.WEST));
                    case EAST:
                        return face == Direction.WEST || (!isClickedMoreEast && (face == Direction.NORTH || face == Direction.SOUTH));
                    case SOUTH:
                        return face == Direction.NORTH || (!isClickedMoreSouth && (face == Direction.EAST || face == Direction.WEST));
                    case WEST:
                        return face == Direction.EAST || (isClickedMoreEast && (face == Direction.NORTH || face == Direction.SOUTH));
                    default: // todo: Don't know, if I need this... and don't know if the else-statement is needed for this overarching if-statement.
                        return true;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public IFluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean receiveFluid(IWorld world, BlockPos pos, BlockState state, IFluidState fluidState) {
        return state.get(TYPE) != VertSlabType.DOUBLE ? IWaterLoggable.super.receiveFluid(world, pos, state, fluidState) : false;
    }

    @Override
    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluid) {
        return state.get(TYPE) != VertSlabType.DOUBLE ? IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluid) : false;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if ((Boolean)stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override // todo: take a closer look at this method.
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType pathType) {
        switch(pathType) {
            case LAND:
                return false;
            case WATER:
                return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
    }

    static {
        TYPE = ModBlockStateProperties.VERT_SLAB_TYPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        NORTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
        EAST_SHAPE = Block.makeCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        SOUTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
        WEST_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    }
}
