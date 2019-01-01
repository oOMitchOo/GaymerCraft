package oomitchoo.gaymercraft.block.baseslabs;


import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.init.ModBlocks;

import java.util.Random;

/**
 * Created by oOMitchOo on 30.11.2018.
 */
public abstract class BlockPurpurVertSlab extends BlockVertSlabBase {
    public static final PropertyEnum<BlockPurpurSlab.Variant> VARIANT = PropertyEnum.<BlockPurpurSlab.Variant>create("variant", BlockPurpurSlab.Variant.class);

    public BlockPurpurVertSlab(String unlName, String regName) {
        super(Material.ROCK, MapColor.MAGENTA , unlName, regName);

        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);

        IBlockState iblockstate = this.blockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(VARIANT, BlockPurpurSlab.Variant.DEFAULT));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.PURPUR_VERT_SLAB);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.PURPUR_VERT_SLAB, 1, 0);
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return BlockPurpurSlab.Variant.DEFAULT;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, 0));
    }

    // TODO: 1.13: Change the facings, 0: south, 1: west, 2: north, 3: east (see getMetaFromState())
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockPurpurSlab.Variant.DEFAULT);

        switch (meta) {
            case 0:
                return iblockstate.withProperty(FACING, EnumFacing.NORTH);
            case 1:
                return iblockstate.withProperty(FACING, EnumFacing.EAST);
            case 2:
                return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            case 3:
                return iblockstate.withProperty(FACING, EnumFacing.WEST);
            default:
                return iblockstate;
        }
    }

    // TODO: 1.13: Use "j = state.getValue(FACING).getHorizontalIndex();" instead to get the meta-value.
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int j = 0;
        switch (state.getValue(FACING)) {
            case NORTH:
                return 0;
            case EAST:
                return 1;
            case SOUTH:
                return 2;
            case WEST:
                return 3;
            default:
                return 0;
        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, VARIANT});
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return MapColor.MAGENTA;
    }
}