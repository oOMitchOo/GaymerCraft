package oomitchoo.gaymercraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RainbowMagmaBlock extends MagmaBlock {
    private static final ResourceLocation locationColoredWaterTag = new ResourceLocation(Reference.MOD_ID, "colored_water");
    public RainbowMagmaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        Tag<Fluid> coloredWaterTag = FluidTags.getCollection().get(locationColoredWaterTag);
        if(coloredWaterTag != null && worldIn.getFluidState(pos.up()).isTagged(coloredWaterTag)) {
            Block blockUp = worldIn.getBlockState(pos.up()).getBlock();
            if (blockUp instanceof ColoredFlowingFluidBlock) {
                ((ColoredFlowingFluidBlock) blockUp).getBubbleBlock().placeBubbleColumn(worldIn, pos.up(), true);
            }
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      if (facing == Direction.UP && facingState.getFluidState().isTagged(FluidTags.WATER)) {
         worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
      }
      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   @Override
   @OnlyIn(Dist.CLIENT)
   public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
       tooltip.add(new TranslationTextComponent("message.rainbow.magma.block"));
   }
}
