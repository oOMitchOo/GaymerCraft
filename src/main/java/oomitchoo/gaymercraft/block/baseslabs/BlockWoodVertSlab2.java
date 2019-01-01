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
public abstract class BlockWoodVertSlab2 extends BlockVertSlabBase {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", BlockWoodVertSlab2.EnumType.class);

    public BlockWoodVertSlab2 (String unlName, String regName) {
        super(Material.WOOD, unlName, regName);

        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.WOOD);

        IBlockState iblockstate = this.blockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(VARIANT, BlockWoodVertSlab2.EnumType.ACACIA).withProperty(FACING, EnumFacing.NORTH));
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.WOOD_VERT_SLAB_2);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.WOOD_VERT_SLAB_2, 1, ((EnumType)state.getValue(VARIANT)).getMetadata());
    }

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
        return EnumType.byMetadata(stack.getMetadata() & 1); // bit-wise &. Es können Werte bis 1 erreicht werden.
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, EnumType.ACACIA.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.DARK_OAK.getMetadata()));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 1)); // 0,4,8,12=0:STONE; 1,5,9,13=1:SAND; 2,6,10,14=2:WOOD; 3,7,11,15=3:COBBLESTONE

        switch (meta / 2) { // Für 0..1 = 0 ; für 2..3 = 1 ; für 4..5 = 2 ; für 6..7 = 3 usw.
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
    public int getMetaFromState(IBlockState state)
    {
        int j = 0;
        j = ((EnumType)state.getValue(VARIANT)).getMetadata();
        // Erst Variant und somit j zw. 0 und 3
        // Dann je nach Ausrichtung NORTH=+0:0,1; SOUTH=+2:2,3; WEST=+4:4,5; EAST=+6:6,7

        // Folgende if-statements könnte man auch mit dem jeweiligen Index der Richtungen lösen, so liest es sich aber besser.
        if (state.getValue(FACING) == EnumFacing.NORTH) { // SOUTH wird nicht gecheckt, denn in dem Fall muss j ja sowieso nicht geändert werden.
            j = j+2;
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
            j = j+4;
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
            j = j+6;
        }

        return j;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{FACING, VARIANT});
    }

    public int damageDropped(IBlockState state)
    {
        return ((EnumType)state.getValue(VARIANT)).getMetadata();
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((EnumType)state.getValue(VARIANT)).getMapColor();
    }

    public static enum EnumType implements IStringSerializable
    {
        // OAK(0, "oak", MapColor.WOOD),
        // SPRUCE(1, "spruce", MapColor.OBSIDIAN),
        // BIRCH(2, "birch", MapColor.SAND),
        // JUNGLE(3, "jungle", MapColor.DIRT);
        ACACIA(0, "acacia", MapColor.ADOBE),
        DARK_OAK(1, "dark_oak", "big_oak", MapColor.BROWN);

        private static final BlockWoodVertSlab2.EnumType[] META_LOOKUP = new BlockWoodVertSlab2.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockWoodVertSlab2.EnumType byMetadata(int meta)
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
            for (BlockWoodVertSlab2.EnumType blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }
}