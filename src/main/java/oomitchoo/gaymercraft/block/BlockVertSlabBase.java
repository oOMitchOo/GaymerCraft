package oomitchoo.gaymercraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.helper.LogHelper;

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

    protected boolean canSilkHarvest()
    {
        return false;
    }

    // TODO: 1.13: Change the facings, when I fix the blogstates of the vertical slabs.
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = this.getActualState(state, source, pos).getValue(FACING);

        if (this.isDouble()) {
            return FULL_BLOCK_AABB;
        } else {
            switch (facing) {
                case NORTH:
                    return VERT_NSWE_HALF_SLAB_BB[0];
                case SOUTH:
                    return VERT_NSWE_HALF_SLAB_BB[1];
                case WEST:
                    return VERT_NSWE_HALF_SLAB_BB[2];
                case EAST:
                    return VERT_NSWE_HALF_SLAB_BB[3];
                default: return FULL_BLOCK_AABB; // This shouldn't happen.
            }
        }
    }

    public boolean isTopSolid(IBlockState state) { return ((BlockVertSlabBase)state.getBlock()).isDouble(); }

    // Häuptsächlich zum Platzieren von Knöpfen etc.
    // TODO: 1.13: Change the facings, when I fix the blogstates of the vertical slabs.
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        if (((BlockVertSlabBase)state.getBlock()).isDouble())
        {
            return BlockFaceShape.SOLID;
        } else if (face == EnumFacing.NORTH && state.getValue(FACING) == EnumFacing.SOUTH)
        {
            return BlockFaceShape.SOLID;
        } else if (face == EnumFacing.SOUTH && state.getValue(FACING) == EnumFacing.NORTH)
        {
            return BlockFaceShape.SOLID;
        } else if (face == EnumFacing.WEST && state.getValue(FACING) == EnumFacing.EAST)
        {
            return BlockFaceShape.SOLID;
        } else if (face == EnumFacing.EAST && state.getValue(FACING) == EnumFacing.WEST)
        {
            return BlockFaceShape.SOLID;
        } else {
            return BlockFaceShape.UNDEFINED;
        }
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return this.isDouble();
    }

    // TODO: 1.13: Change the facings, when I fix the blogstates of the vertical slabs.
    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        if (net.minecraftforge.common.ForgeModContainer.disableStairSlabCulling)
            return super.doesSideBlockRendering(state, world, pos, face);

        if ( state.isOpaqueCube() )
            return true;

        EnumFacing facing = state.getValue(FACING);
        if (facing == EnumFacing.NORTH && face == EnumFacing.SOUTH) {
            return true;
        } else if (facing == EnumFacing.SOUTH && face == EnumFacing.NORTH) {
            return true;
        } else if (facing == EnumFacing.WEST && face == EnumFacing.EAST) {
            return true;
        } else if (facing == EnumFacing.EAST && face == EnumFacing.WEST) { // this is for EAST and everything (there shouldn't be more) else.
            return true;
        } else {
            return false;
        }
    }

    // TODO: 1.13: Change the facings, when I fix the blogstates of the vertical slabs.
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);

        // If placer is placing on a vertical slab, use its FACING.
        IBlockState blockPlacingOn = worldIn.getBlockState(pos.offset(facing.getOpposite()));
        if (blockPlacingOn.getBlock() instanceof BlockVertSlabBase && !blockPlacingOn.isFullBlock() && facing != blockPlacingOn.getValue(FACING).getOpposite())
            return iblockstate.withProperty(FACING, blockPlacingOn.getValue(FACING));

        if(this.isDouble()) { // This should never be called since there are only ItemBlocks for the NOT isDouble version.
            return iblockstate;
        } else {
            switch (facing) { // Abhängig welche Seite vom Block man anguckt.
                case NORTH:
                    if (hitX < 0.75) {
                        if (hitX < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        }
                    } else /* hitX >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.WEST);
                    }
                case SOUTH:
                    if (hitX < 0.75) {
                        if (hitX < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        } else /* hitX between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        }
                    } else /* hitX >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.WEST);
                    }
                case WEST:
                    if (hitZ < 0.75) {
                        if (hitZ < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                    }
                case EAST:
                    if (hitZ < 0.75) {
                        if (hitZ < 0.25) {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        } else /* hitZ between 0.25 and 0.75 */ {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        }
                    } else /* hitZ >= 0.75 */ {
                        return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                    }
                case UP:
                    //  hitX and hitZ are between 0 and 1 and basically tell us which coordinate
                    //  of the face (which the block is being placed on) is right-clicked by the player.
                    //
                    //  SOUTH and WEST: hitZ < hitX             |   SOUTH and EAST: hitZ < (1-hitX)
                    //  NORTH and EAST: hitX < hitZ             |   NORTH and WEST: (1-hitX) < hitZ
                    //
                    //  NORTH: hitX < hitZ && (1-hitX) < hitZ   |   SOUTH: X < Z && (1-X) < Z
                    //  WEST: hitZ < hitX && (1-hitX) < hitZ    |   EAST: hitX < hitZ && hitZ < (1-hitX)
                {
                    // else (look big comment section above)
                    if (hitX < hitZ) {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        }
                    } else /* hitZ < hitX and unlikely event of hitX = hitZ */ {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        }
                    }
                }
                case DOWN:
                {
                    // Same as UP case.
                    if (hitX < hitZ) {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.EAST);
                        }
                    } else /* hitZ < hitX and unlikely event of hitX = hitZ */ {
                        if ((1f-hitX) < hitZ) {
                            return iblockstate.withProperty(FACING, EnumFacing.WEST);
                        } else /* hitZ < (1f-hitX) and unlikely event of hitX = hitZ */ {
                            return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                        }
                    }
                }
                default:
                    return iblockstate; // Default ist SOUTH, aber sollte nie aufgerufen werden.
            }
        }
    }

    public int quantityDropped(Random random)
    {
        return this.isDouble() ? 2 : 1;
    }

    public boolean isFullCube(IBlockState state)
    {
        return this.isDouble();
    }

    /**
     * This basically determines, if a block touching this block on the @side (or facing WITH the @side???)
     * gets rendered on the side that touches this block.
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        // super method should be good enough here. todo: check it maybe.
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        /* if (this.isDouble())
        {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        } else {
            return false;
        }
        */
    }


}
