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
import net.minecraft.world.IBlockAccess;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        this.setLightOpacity(90);
    }

    protected boolean canSilkHarvest()
    {
        return false;
    }

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

    // Häuptsächlich zum platzieren von Knöpfen etc.
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

    // todo: Gucken, ob die Seiten der benachbarten Blöcke richtig gerendert (bzw. nicht gerendert) werden.
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
            return false; // todo: vlt sollte es standardmäßig true sein? Muss ich mir näher angucken.
        }
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, EnumFacing.SOUTH);

        if(this.isDouble()) { // When isDouble dann ist facing egal, also nehmen wir einfach SOUTH. (Wird sowieso Doppelblock-Modell erhalten)
            return iblockstate;
        } else {
            switch (facing) { // Ansonsten abhängig welche Seite vom Block man anguckt.
                case NORTH:
                    return iblockstate.withProperty(FACING, EnumFacing.NORTH);
                case SOUTH:
                    return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                case WEST:
                    return iblockstate.withProperty(FACING, EnumFacing.WEST);
                case EAST:
                    return iblockstate.withProperty(FACING, EnumFacing.EAST);
                case UP:
                    return iblockstate.withProperty(FACING, placer.getHorizontalFacing().getOpposite()); // Und für UP/DOWN abhängig von der Ausrichtung des Platzierers.
                case DOWN:
                    return iblockstate.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
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

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side); // todo: FAAAAAAAAAAAAALSCH. Muss ich noch checken.
        /*if (this.isDouble())
        {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        } else {
            return false;
        }*/
    }


}
