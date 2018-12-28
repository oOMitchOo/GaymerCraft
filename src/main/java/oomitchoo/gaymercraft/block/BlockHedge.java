package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by oOMitchOo on 25.12.2018.
 */
public class BlockHedge extends BlockBase{
    /** Whether this hedge connects in the northern direction */
    private static final PropertyBool NORTH = PropertyBool.create("north");
    /** Whether this hedge connects in the eastern direction */
    private static final PropertyBool EAST = PropertyBool.create("east");
    /** Whether this hedge connects in the southern direction */
    private static final PropertyBool SOUTH = PropertyBool.create("south");
    /** Whether this hedge connects in the western direction */
    private static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class);
    private boolean leavesFancy = true;
    private static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D), // unconnected
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 1.0D), // oneside SOUTH
            new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D), // oneside WEST
            new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.875D, 1.0D, 1.0D), // corner SOUTH-WEST
            new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 0.875D), // oneside NORTH
            new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D), // twosides NORTH-SOUTH
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 0.875D), // corner WEST-NORTH
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D), // tjunktion SOUTH-WEST-NORTH
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D), // oneside EAST
            new AxisAlignedBB(0.125D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D), // corner EAST-SOUTH
            new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D), // twosides WEST-EAST
            new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 1.0D), // tjunktion EAST-SOUTH-WEST
            new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D), // corner NORTH-EAST
            new AxisAlignedBB(0.125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D), // tjunktion NORTH-EAST-SOUTH
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.875D), // tjunktion WEST-NORTH-EAST
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) // connected
    };
    private static final AxisAlignedBB MIDDLE_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.5D, 0.875D);
    private static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.875D, 0.875D, 1.5D, 1.0D);
    private static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 0.125D, 1.5D, 0.875D);
    private static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.5D, 0.125D);
    private static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.875D, 0.0D, 0.125D, 1.0D, 1.5D, 0.875D);

    public BlockHedge (String unlName, String regName)
    {
        super(Material.LEAVES, Material.LEAVES.getMaterialMapColor(), unlName, regName);
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        int meta = state.getBlock().getMetaFromState(state);
        if (meta < 0 || meta > 5)
            return new ItemStack(this, 1, 0); // Default
        else
            return new ItemStack(this, 1, meta);
    }

    @Override
    protected boolean canSilkHarvest()
    {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        if (meta < 0 || meta > 5)
            return this.getDefaultState();
        else
            return this.getDefaultState().withProperty(VARIANT, this.getWoodType(meta));
    }

    private BlockPlanks.EnumType getWoodType(int meta)
    {
        return BlockPlanks.EnumType.byMetadata(meta);
    }


    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
        items.add(new ItemStack(this, 1, 2));
        items.add(new ItemStack(this, 1, 3));
        items.add(new ItemStack(this, 1, 4));
        items.add(new ItemStack(this, 1, 5));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, canHedgeConnectTo(worldIn, pos, EnumFacing.NORTH))
                .withProperty(EAST,  canHedgeConnectTo(worldIn, pos, EnumFacing.EAST))
                .withProperty(SOUTH, canHedgeConnectTo(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST,  canHedgeConnectTo(worldIn, pos, EnumFacing.WEST));
    }

    private boolean canHedgeConnectTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        BlockPos other = pos.offset(facing);
        Block block = world.getBlockState(other).getBlock();
        return block.canBeConnectedTo(world, other, facing.getOpposite()) || canConnectTo(world, other, facing.getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {EAST, NORTH, SOUTH, VARIANT, WEST});
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
        if (!isActualState)
        {
            state = state.getActualState(worldIn, pos);
        }

        addCollisionBoxToList(pos, entityBox, collidingBoxes, MIDDLE_AABB);

        if ((Boolean) state.getValue(NORTH))
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB);
        }

        if (((Boolean)state.getValue(EAST)))
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB);
        }

        if (((Boolean)state.getValue(SOUTH)))
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB);
        }

        if (((Boolean)state.getValue(WEST)))
        {
            addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = this.getActualState(state, source, pos);
        return BOUNDING_BOXES[getBoundingBoxIdx(state)];
    }

    /**
     * Returns the correct index into boundingBoxes, based on what the hedge is connected to.
     */
    private static int getBoundingBoxIdx(IBlockState state)
    {
        int i = 0;

        if ((Boolean) state.getValue(NORTH))
        {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if ((Boolean) state.getValue(EAST))
        {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if ((Boolean) state.getValue(SOUTH))
        {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if ((Boolean) state.getValue(WEST))
        {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 1;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return !this.leavesFancy;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean causesSuffocation(IBlockState state)
    {
        return false;
    }

    /**
     * Determines if this block is considered a leaf block, used to apply the leaf decay and generation system.
     */
    @Override public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos){ return false; }

    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
    {
        return canConnectTo(world, pos.offset(facing), facing.getOpposite());
    }

    private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, pos, facing);
        Block block = iblockstate.getBlock();
        // Only connects horizontally to other hedges/fences (MIDDLE_POLE) or Leaves Block and when it is the same Material (LEAVES) or a FenceGate.
        boolean flag = (blockfaceshape == BlockFaceShape.MIDDLE_POLE || block instanceof BlockLeaves) && (iblockstate.getMaterial() == this.blockMaterial || block instanceof BlockFenceGate);
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
    }

    private static boolean isExcepBlockForAttachWithPiston(Block p_194142_0_) // todo: look in the MC wiki for these exceptions.
    {
        return Block.isExceptBlockForAttachWithPiston(p_194142_0_) || p_194142_0_ == Blocks.BARRIER || p_194142_0_ == Blocks.MELON_BLOCK || p_194142_0_ == Blocks.PUMPKIN || p_194142_0_ == Blocks.LIT_PUMPKIN;
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     *
     * @return an approximation of the form of the given face
     */
    @Override // todo: check this.
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face != EnumFacing.UP && face != EnumFacing.DOWN ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.CENTER;
    }

    // ===================== SERVER SIDE - RENDERING ==============================================================================

    @SideOnly(Side.CLIENT) // todo: Isn't used yet. Leaves are always fancy for now. Need to use an event - maybe put it in the GaymerCraft config and use OnConfigChanged event?
    public void setGraphicsLevel(boolean fancy)
    {
        this.leavesFancy = fancy;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        // NORTH-EAST-SOUTH-WEST does not get rendered, if two hedges connect. That way hedges look more connected than leaves blocks for example.
        if(blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockHedge && side != EnumFacing.DOWN && side != EnumFacing.UP) {
            return false;
        } else {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }

    @Override
    @SideOnly(Side.CLIENT) // todo: check if this must be changed (dripping water on leave blocks).
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (worldIn.isRainingAt(pos.up()) && !worldIn.getBlockState(pos.down()).isTopSolid() && rand.nextInt(15) == 1)
        {
            double d0 = (double)((float)pos.getX() + rand.nextFloat());
            double d1 = (double)pos.getY() - 0.05D;
            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
            worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return this.leavesFancy ? BlockRenderLayer.CUTOUT_MIPPED : BlockRenderLayer.SOLID;
    }
}