package oomitchoo.gaymercraft.block.baseslabs;


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
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.init.ModBlocks;

import java.util.Random;

/**
 * Created by oOMitchOo on 28.11.2018.
 */
public abstract class BlockStoneVertSlab1 extends BlockVertSlabBase {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);

    public BlockStoneVertSlab1(String unlName, String regName) {
        super(Material.ROCK, unlName, regName);

        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);

        IBlockState iblockstate = this.blockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumType.STONE).withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.STONE_VERT_SLAB_1);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.STONE_VERT_SLAB_1, 1, ((EnumType)state.getValue(VARIANT)).getMetadata());
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName()+ "." +EnumType.byMetadata(meta).getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        int meta = stack.getMetadata();
        switch (meta) {
            case 0:
                return EnumType.byMetadata(meta); // STONE
            case 1:
                return EnumType.byMetadata(meta); // SAND
            case 2:
                return EnumType.byMetadata(meta); // COBBLESTONE
            default:
                return EnumType.byMetadata(0); // Should never be called.
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, EnumType.STONE.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.SAND.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.COBBLESTONE.getMetadata()));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) // 0,3,6,9=STONE; 1,4,7,10=SAND; 2,5,8,11=COBBLESTONE
    {                                             // Also 0..2=NORTH; 3..5=SOUTH; 6..8=WEST; 9..11=EAST
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta % 3));

        switch (meta / 3) {
            case 0:
                return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            case 1:
                return iblockstate.withProperty(FACING, EnumFacing.NORTH);
            case 2:
                return iblockstate.withProperty(FACING, EnumFacing.EAST);
            case 3:
                return iblockstate.withProperty(FACING, EnumFacing.WEST);
            default:
                return iblockstate;
        }
    }

    // TODO: 1.13: Combine this with EnumFacing.VALUE.getHorizontalIndex(). Change getStateFromMeta() then too.
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int j = 0;
        j = ((EnumType)state.getValue(VARIANT)).getMetadata();
        // Erst Variant und somit j zw. 0 und 2
        // Dann je nach Ausrichtung SOUTH=+0:0,1,2; NORTH=+3:3,4,5; EAST=+6:6,7,8; WEST=+9:9,10,11

        // Folgende if-statements könnte man auch mit dem jeweiligen Index der Richtungen lösen, so liest es sich aber besser.
        if (state.getValue(FACING) == EnumFacing.NORTH) { // SOUTH wird nicht gecheckt, denn in dem Fall muss j ja sowieso nicht geändert werden.
            j = j+3;
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
            j = j+6;
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
            j = j+9;
        }

        return j;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, VARIANT});
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((EnumType)state.getValue(VARIANT)).getMapColor();
    }

    public static enum EnumType implements IStringSerializable
    {
        STONE(0, MapColor.STONE, "stone"),
        SAND(1, MapColor.SAND, "sandstone", "sand"),
        COBBLESTONE(2, MapColor.STONE, "cobblestone", "cobble");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final MapColor mapColor;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, MapColor mColor, String name)
        {
            this(meta, mColor, name, name);
        }

        private EnumType(int meta, MapColor mColor, String name, String unlName)
        {
            this.meta = meta;
            this.mapColor = mColor;
            this.name = name;
            this.unlocalizedName = unlName;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BlockStoneVertSlab1.EnumType blockstoneslab$enumtype : values())
            {
                META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
            }
        }
    }
}