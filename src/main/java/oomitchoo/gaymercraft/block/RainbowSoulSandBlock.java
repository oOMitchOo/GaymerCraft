package oomitchoo.gaymercraft.block;

import net.minecraft.block.*;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oomitchoo.gaymercraft.init.ModBlocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RainbowSoulSandBlock extends SoulSandBlock {
    public RainbowSoulSandBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        Block blockUp = worldIn.getBlockState(pos.up()).getBlock();

        // todo: Change this to vanilla bubble column method, if it is no longer hardcoded to only work with vanilla Soul Sand / Magma Block?
        if(blockUp == Blocks.WATER) {
            ((ColoredBubbleColumnBlock) ModBlocks.MODDED_VANILLA_BUBBLE_COLUMN).placeBubbleColumn(worldIn, pos.up(), false);
        } else if (blockUp instanceof IBubblyFluidBlock) {
            ((IBubblyFluidBlock) blockUp).getBubbleColumnPlacer().placeBubbleColumn(worldIn, pos.up(), false);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("message.rainbow.soul.sand"));
    }
}
