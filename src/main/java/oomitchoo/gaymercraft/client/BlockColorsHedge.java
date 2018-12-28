package oomitchoo.gaymercraft.client;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oomitchoo.gaymercraft.block.BlockHedge;

import javax.annotation.Nullable;

/**
 * Created by oOMitchOo on 27.12.2018.
 */
@SideOnly(Side.CLIENT)
public class BlockColorsHedge implements IBlockColor {
    private static final IBlockColor INSTANCE = new BlockColorsHedge();
    public static IBlockColor getInstance() {
        return INSTANCE;
    }

    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(BlockHedge.VARIANT);

        if (blockplanks$enumtype == BlockPlanks.EnumType.SPRUCE)
        {
            return ColorizerFoliage.getFoliageColorPine();
        }
        else if (blockplanks$enumtype == BlockPlanks.EnumType.BIRCH)
        {
            return ColorizerFoliage.getFoliageColorBirch();
        }
        else
        {
            return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
        }
    }
}