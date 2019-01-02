package oomitchoo.gaymercraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public abstract class BlockVertSlabBase extends BlockBase {

    public abstract boolean isDouble();
    public abstract String getUnlocalizedName(int meta);
    public abstract IProperty<?> getVariantProperty();
    public abstract Comparable<?> getTypeForItem(ItemStack stack);
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    protected static final AxisAlignedBB[] VERT_NSWE_HALF_SLAB_BB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D),new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D),new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D)};

    public BlockVertSlabBase (Material materialIn, String unlName, String regName) { this(materialIn, materialIn.getMaterialMapColor(), unlName, regName); }

    public BlockVertSlabBase(Material materialIn, MapColor mapColorIn, String unlName, String regName) {
        super(materialIn, mapColorIn, unlName, regName);

        this.fullBlock = this.isDouble();
        if (!this.isDouble())
            setLightOpacity(0); // TODO: Change this, if I fixed the light issue with the vertical half slabs.
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = this.getActualState(state, source, pos).getValue(FACING);

        if (this.isDouble()) {
            return FULL_BLOCK_AABB;
        } else {
            switch (facing) {
                case SOUTH:
                    return VERT_NSWE_HALF_SLAB_BB[0];
                case NORTH:
                    return VERT_NSWE_HALF_SLAB_BB[1];
                case EAST:
                    return VERT_NSWE_HALF_SLAB_BB[2];
                case WEST:
                    return VERT_NSWE_HALF_SLAB_BB[3];
                default: return FULL_BLOCK_AABB; // This shouldn't happen.
            }
        }
    }

    public boolean isTopSolid(IBlockState state) { return ((BlockVertSlabBase)state.getBlock()).isDouble(); }

    // Häuptsächlich zum Platzieren von Knöpfen etc.
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        if (((BlockVertSlabBase)state.getBlock()).isDouble())
        {
            return BlockFaceShape.SOLID;
        } else if (face == state.getValue(FACING)) {
            return BlockFaceShape.SOLID;
        } else {
            return BlockFaceShape.UNDEFINED;
        }
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return this.isDouble();
    }

    // Checks if a face (of this block) can block the rendering of the faces of adjacent blocks.
    // This is done for every @face of the blockspace this blocks occupies and it is @state sensitive.
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
            return super.doesSideBlockRendering(state, world, pos, face);

        if ( state.isOpaqueCube() )
            return true;

        EnumFacing facing = state.getValue(FACING);
        if (facing == face) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facePlacingOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facePlacingOn, hitX, hitY, hitZ, meta, placer);

        // If placer is placing on a vertical slab, use its FACING.
        IBlockState blockPlacingOn = worldIn.getBlockState(pos.offset(facePlacingOn.getOpposite()));
        if (blockPlacingOn.getBlock() instanceof BlockVertSlabBase && !blockPlacingOn.isFullBlock()) {
            EnumFacing vertSlabFacing = blockPlacingOn.getValue(FACING);
            // but only for the half sized faces
            if (facePlacingOn != vertSlabFacing && facePlacingOn != vertSlabFacing.getOpposite())
                return iblockstate.withProperty(FACING, blockPlacingOn.getValue(FACING));
        }

        if(this.isDouble()) { // This should never be called since there are only ItemBlocks for the NOT isDouble version.
            return iblockstate;
        } else {
            switch (facePlacingOn) { // Abhängig welche Seite vom Block man anguckt.
                case NORTH:
                    if (hitX < 0.75) {
                        if (hitX < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        }
                    } else /* hitX >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.EAST);
                    }
                case SOUTH:
                    if (hitX < 0.75) {
                        if (hitX < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        }
                    } else /* hitX >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.EAST);
                    }
                case WEST:
                    if (hitZ < 0.75) {
                        if (hitZ < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                    }
                case EAST:
                    if (hitZ < 0.75) {
                        if (hitZ < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                    }
                case UP:
                    //  hitX and hitZ are between 0 and 1 and basically tell us which coordinate
                    //  of the face (which the block is being placed on) is right-clicked by the player.
                    //
                    //  SOUTH and WEST: hitX < hitZ             |   SOUTH and EAST: (1-hitX) < hitZ
                    //  NORTH and EAST: hitZ < hitX             |   NORTH and WEST: hitZ < (1-hitX)
                    //
                    //  NORTH: hitZ < hitX && hitZ < (1-hitX)   |   SOUTH: hitX < hitZ && (1-hitX) < hitZ
                    //  WEST: hitX < hitZ && hitZ < (1-hitX)    |   EAST: hitZ < hitX && (1-hitX) < hitZ
                {
                    // else (look big comment section above)
                    if (hitX < hitZ) {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        }
                    } else /* hitZ < hitX and unlikely event of hitX = hitZ */ {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        }
                    }
                }
                case DOWN:
                {
                    // Same as UP case.
                    if (hitX < hitZ) {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        }
                    } else /* hitZ < hitX and unlikely event of hitX = hitZ */ {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        }
                    }
                }
                default:
                    return iblockstate; // Default ist NORTH, aber sollte nie aufgerufen werden.
            }
        }
    }

    @Override
    public int quantityDropped(Random random)
    {
        return this.isDouble() ? 2 : 1;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return this.isDouble();
    }
}
