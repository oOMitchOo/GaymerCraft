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
 * Created by oOMitchOo on 29.11.2018.
 */
public abstract class BlockStoneVertSlab2 extends BlockVertSlabBase {
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.<EnumType>create("variant", EnumType.class);

    public BlockStoneVertSlab2(String unlName, String regName) {
        super(Material.ROCK, unlName, regName);

        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);

        IBlockState iblockstate = this.blockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumType.BRICK).withProperty(FACING, EnumFacing.SOUTH));
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.STONE_VERT_SLAB_2);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModBlocks.STONE_VERT_SLAB_2, 1, ((EnumType)state.getValue(VARIANT)).getMetadata());
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
        return EnumType.byMetadata(stack.getMetadata() & 3); // bit-wise &. Da 3 in bits 11 ist, können Werte bis 3 erreicht werden, also die Materialien BRICK,SMOOTHBRICK,NETHERBRICK,QUARTZ.
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, EnumType.BRICK.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.SMOOTHBRICK.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.NETHERBRICK.getMetadata()));
        items.add(new ItemStack(this, 1, EnumType.QUARTZ.getMetadata()));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta & 3)); // 0,4,8,12=0:BRICK; 1,5,9,13=1:SMOOTHBRICK; 2,6,10,14=2:NETHERBRICK; 3,7,11,15=3:QUARTZ

        switch (meta / 4) { // Für 0..3 = 0 ; für 4..7 = 1 ; für 8..11 = 2 ; für 12..15 = 3
            case 0:
                return iblockstate.withProperty(FACING, EnumFacing.NORTH);
            case 1:
                return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            case 2:
                return iblockstate.withProperty(FACING, EnumFacing.WEST);
            case 3:
                return iblockstate.withProperty(FACING, EnumFacing.EAST);
            default:
                return iblockstate;
        }
    }

    public int getMetaFromState(IBlockState state)
    {
        int j = 0;
        j = ((EnumType)state.getValue(VARIANT)).getMetadata();
        // Erst Variant und somit j zw. 0 und 3
        // Dann je nach Ausrichtung NORTH=+0:0,1,2,3; SOUTH=+4:4,5,6,7; WEST=+8:8,9,10,11; EAST=+12:12,13,14,15

        // Folgende if-statements könnte man auch mit dem jeweiligen Index der Richtungen lösen, so liest es sich aber besser.
        if (state.getValue(FACING) == EnumFacing.SOUTH) { // NORTH wird nicht gecheckt, denn in dem Fall muss j ja sowieso nicht geändert werden.
            j = j+4;
        } else if (state.getValue(FACING) == EnumFacing.WEST) {
            j = j+8;
        } else if (state.getValue(FACING) == EnumFacing.EAST) {
            j = j+12;
        }

        return j;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[]{VARIANT, FACING});
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
        BRICK(0, MapColor.RED, "brick"),
        SMOOTHBRICK(1, MapColor.STONE, "stone_brick", "smoothStoneBrick"),
        NETHERBRICK(2, MapColor.NETHERRACK, "nether_brick", "netherBrick"),
        QUARTZ(3, MapColor.QUARTZ, "quartz");

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
            for (BlockStoneVertSlab2.EnumType blockstoneslab$enumtype : values())
            {
                META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
            }
        }
    }
}