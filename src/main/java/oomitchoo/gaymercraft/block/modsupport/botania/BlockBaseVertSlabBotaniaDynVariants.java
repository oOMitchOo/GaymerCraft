package oomitchoo.gaymercraft.block.modsupport.botania;

import net.minecraft.block.material.MapColor;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by oOMitchOo on 12.01.2019.
 */
public abstract class BlockBaseVertSlabBotaniaDynVariants extends BlockVertSlabBase {
    private final BlockVertSlabBase singleSlab;
    private final int indexOfFirstEnum;
    private final int numberOfVariants;
    public final PropertyEnum<EnumVariantSlabBotania> VARIANT;
    private final BlockStateContainer overrideBlockState;

    public BlockBaseVertSlabBotaniaDynVariants(int indexOfFirstEnum, int numberOfVariants, @Nullable BlockVertSlabBase singleSlab, String unlName, String regName) {
        super(EnumVariantSlabBotania.byIndex(indexOfFirstEnum).getMaterial(), unlName, regName);
        this.singleSlab = singleSlab;

        EnumVariantSlabBotania firstEnumVariant = EnumVariantSlabBotania.byIndex(indexOfFirstEnum);
        this.setResistance(firstEnumVariant.getResistance());
        this.setSoundType(firstEnumVariant.getSoundType());

        this.indexOfFirstEnum = indexOfFirstEnum;
        this.numberOfVariants = numberOfVariants;
        this.VARIANT = injectEnumVariants();
        this.overrideBlockState = this.createNewBlockState();
        IBlockState iblockstate = this.overrideBlockState.getBaseState();
        this.setDefaultState(iblockstate.withProperty(VARIANT, firstEnumVariant).withProperty(FACING, EnumFacing.SOUTH));
    }

    private PropertyEnum<EnumVariantSlabBotania> injectEnumVariants() {
        Collection<EnumVariantSlabBotania> collectionEnumVariants = new ArrayList<>(this.numberOfVariants);
        for (int meta = 0; meta < this.numberOfVariants; meta++) {
            collectionEnumVariants.add(EnumVariantSlabBotania.byIndex(this.indexOfFirstEnum + meta));
        }

        return PropertyEnum.create("variant", EnumVariantSlabBotania.class, collectionEnumVariants);
    }

    public int getNumberOfVariants () {
        return this.numberOfVariants;
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (!isDouble() || singleSlab == null)
            return Item.getItemFromBlock(this);
        else
            return Item.getItemFromBlock(singleSlab);
    }

    @Override
    @Nonnull
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!isDouble() || singleSlab == null)
            return new ItemStack(this, 1, state.getValue(VARIANT).getMetadata());
        else
            return new ItemStack(singleSlab, 1, state.getValue(VARIANT).getMetadata());
    }

    @Override
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName()+ "." + EnumVariantSlabBotania.byIndex(this.indexOfFirstEnum + (meta % this.numberOfVariants)).getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        int meta = stack.getMetadata();
        return EnumVariantSlabBotania.byIndex(this.indexOfFirstEnum + (meta % this.numberOfVariants));
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (int meta = 0; meta < this.numberOfVariants; meta++) {
            items.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumVariantSlabBotania.byIndex(this.indexOfFirstEnum + (meta % this.numberOfVariants)));

        switch (meta / 4) {
            case 0:
                return iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            case 1:
                return iblockstate.withProperty(FACING, EnumFacing.WEST);
            case 2:
                return iblockstate.withProperty(FACING, EnumFacing.NORTH);
            case 3:
                return iblockstate.withProperty(FACING, EnumFacing.EAST);
            default:
                return iblockstate;
        }
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int j;
        j = (state.getValue(VARIANT)).getMetadata();

        int i;
        i = state.getValue(FACING).getHorizontalIndex(); // SOUTH 0; WEST 1; NORTH 2; EAST 3

        j = j+(i*4);
        return j;
    }

    @Nonnull
    private BlockStateContainer createNewBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, VARIANT});
    }

    @Override
    @Nonnull
    public BlockStateContainer getBlockState()
    {
        return this.overrideBlockState;
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    @Nonnull
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.getValue(VARIANT).getMapColor();
    }

    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return blockState.getValue(VARIANT).getHardness();
    }
}