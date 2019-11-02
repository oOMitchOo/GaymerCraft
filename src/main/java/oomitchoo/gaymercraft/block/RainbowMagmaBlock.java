package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RainbowMagmaBlock extends MagmaBlock {
    public RainbowMagmaBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        Block blockUp = worldIn.getBlockState(pos.up()).getBlock();

        // todo: This doesn't work, since BubbleColumnBlock checks for vanilla Soul Sand / Magma Block. It would work, if this was the vanilla Magma Block.
        if(blockUp == Blocks.WATER) {
            BubbleColumnBlock.placeBubbleColumn(worldIn, pos.up(), true);
        }

        if (blockUp instanceof IBubblyFluidBlock) {
            ((IBubblyFluidBlock) blockUp).getBubbleColumnPlacer().placeBubbleColumn(worldIn, pos.up(), true);
        }
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Block facingBlock = facingState.getBlock();
        if (facing == Direction.UP && (facingBlock == Blocks.WATER || facingBlock instanceof IBubblyFluidBlock)) {
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
