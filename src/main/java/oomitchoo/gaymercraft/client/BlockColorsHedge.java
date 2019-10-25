package oomitchoo.gaymercraft.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.biome.BiomeColors;

import javax.annotation.Nullable;

public class BlockColorsHedge implements IBlockColor {
    private static final IBlockColor INSTANCE = new BlockColorsHedge();
    public static IBlockColor getInstance() {
        return INSTANCE;
    }

    @Override
    public int getColor(BlockState blockState, @Nullable IEnviromentBlockReader worldIn, @Nullable BlockPos pos, int tintIndex) {
        ResourceLocation regName = blockState.getBlock().getRegistryName();

        if (regName.equals(new ResourceLocation("gaymercraft", "spruce_hedge"))) {
            return FoliageColors.getSpruce();
        } else if (regName.equals(new ResourceLocation("gaymercraft", "birch_hedge"))) {
            return FoliageColors.getBirch();
        } else {
            return worldIn != null && pos != null ? BiomeColors.getFoliageColor(worldIn, pos) : FoliageColors.getDefault();
        }
    }
}
