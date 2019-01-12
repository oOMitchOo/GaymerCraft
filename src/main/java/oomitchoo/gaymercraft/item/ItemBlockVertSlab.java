package oomitchoo.gaymercraft.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 28.11.2018.
 */
public class ItemBlockVertSlab extends ItemBlock {
    private final BlockVertSlabBase singleSlab;
    private final BlockVertSlabBase doubleSlab;
    private final int numberOfVariants;

    public ItemBlockVertSlab(Block block, BlockVertSlabBase singleSlab, BlockVertSlabBase doubleSlab, int numberOfVariants, String unlName, String regName){
        super(block);
        this.singleSlab = singleSlab;
        this.doubleSlab = doubleSlab;
        this.numberOfVariants = numberOfVariants;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(unlName);
        this.setRegistryName(Reference.MOD_ID, regName);
    }

    public int getNumberOfVariants() {
        return this.numberOfVariants;
    }

    @Override
    public int getMetadata(int dmg) { return dmg; }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.singleSlab.getUnlocalizedName(stack.getMetadata());
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    // TODO: Test the making of a double vertical slab more. It's sometimes a little buggy?
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            Comparable<?> comparable = this.singleSlab.getTypeForItem(itemstack); // 0 bis 3 wird zurückgegeben je nach Variante.
            IBlockState iblockstate = worldIn.getBlockState(pos); // Das ist der Block an dem gesetzt wird (der right-clicked wird).

            if (iblockstate.getBlock() == this.singleSlab)
            {
                IProperty<?> iproperty = this.singleSlab.getVariantProperty(); // getVariantProperty() Erhält Enum VARIANT.
                Comparable<?> comparable1 = iblockstate.getValue(iproperty); // holt also den Value von VARIANT (STONE, SAND, WOOD, etc.)
                EnumFacing blockslab$enumfacing = (EnumFacing)iblockstate.getValue(BlockVertSlabBase.FACING);

                if ((facing == EnumFacing.NORTH && blockslab$enumfacing == EnumFacing.SOUTH) && comparable1 == comparable) {
                    IBlockState iblockstate1 = this.makeState(iproperty, comparable1).withProperty(BlockVertSlabBase.FACING, EnumFacing.SOUTH);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
                    return EnumActionResult.SUCCESS;
                } else if ((facing == EnumFacing.SOUTH && blockslab$enumfacing == EnumFacing.NORTH) && comparable1 == comparable) {
                    IBlockState iblockstate1 = this.makeState(iproperty, comparable1).withProperty(BlockVertSlabBase.FACING, EnumFacing.NORTH);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
                    return EnumActionResult.SUCCESS;
                } else if ((facing == EnumFacing.WEST && blockslab$enumfacing == EnumFacing.EAST) && comparable1 == comparable) {
                    IBlockState iblockstate1 = this.makeState(iproperty, comparable1).withProperty(BlockVertSlabBase.FACING, EnumFacing.EAST);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
                    return EnumActionResult.SUCCESS;
                } else if ((facing == EnumFacing.EAST && blockslab$enumfacing == EnumFacing.WEST) && comparable1 == comparable) {
                    IBlockState iblockstate1 = this.makeState(iproperty, comparable1).withProperty(BlockVertSlabBase.FACING, EnumFacing.WEST);
                    AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);
                    if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                    {
                        SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                        worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                        itemstack.shrink(1);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
                        }
                    }
                    return EnumActionResult.SUCCESS;
                }
            }

            return this.tryPlace(player, itemstack, worldIn, pos.offset(facing), comparable) ? EnumActionResult.SUCCESS : super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack)
    {
        BlockPos blockpos = pos;
        IProperty<?> iproperty = this.singleSlab.getVariantProperty();
        Comparable<?> comparable = this.singleSlab.getTypeForItem(stack);
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.singleSlab)
        {
            // boolean flag = iblockstate.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;
            EnumFacing vertSlabFacing = iblockstate.getValue(BlockVertSlabBase.FACING);

            if ((side == EnumFacing.NORTH && vertSlabFacing == EnumFacing.SOUTH) && comparable == iblockstate.getValue(iproperty)) {
                return true;
            } else if ((side == EnumFacing.SOUTH && vertSlabFacing == EnumFacing.NORTH) && comparable == iblockstate.getValue(iproperty)) {
                return true;
            } else if ((side == EnumFacing.WEST && vertSlabFacing == EnumFacing.EAST) && comparable == iblockstate.getValue(iproperty)) {
                return true;
            } else if ((side == EnumFacing.EAST && vertSlabFacing == EnumFacing.WEST) && comparable == iblockstate.getValue(iproperty)) {
                return true;
            }
        }

        pos = pos.offset(side);
        IBlockState iblockstate1 = worldIn.getBlockState(pos);
        return iblockstate1.getBlock() == this.singleSlab && comparable == iblockstate1.getValue(iproperty) ? true : super.canPlaceBlockOnSide(worldIn, blockpos, side, player, stack);
    }

    private boolean tryPlace(EntityPlayer player, ItemStack stack, World worldIn, BlockPos pos, Object itemSlabType)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);

        if (iblockstate.getBlock() == this.singleSlab)
        {
            Comparable<?> comparable = iblockstate.getValue(this.singleSlab.getVariantProperty());

            if (comparable == itemSlabType)
            {
                IBlockState iblockstate1 = this.makeState(this.singleSlab.getVariantProperty(), comparable);
                AxisAlignedBB axisalignedbb = iblockstate1.getCollisionBoundingBox(worldIn, pos);

                if (axisalignedbb != Block.NULL_AABB && worldIn.checkNoEntityCollision(axisalignedbb.offset(pos)) && worldIn.setBlockState(pos, iblockstate1, 11))
                {
                    SoundType soundtype = this.doubleSlab.getSoundType(iblockstate1, worldIn, pos, player);
                    worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    stack.shrink(1);
                }

                return true;
            }
        }

        return false;
    }

    protected <T extends Comparable<T>> IBlockState makeState(IProperty<T> prop, Comparable<?> comp)
    {
        return this.doubleSlab.getDefaultState().withProperty(prop, (T)comp);
    }
}