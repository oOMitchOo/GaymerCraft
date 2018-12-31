package oomitchoo.gaymercraft.block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by oOMitchOo on 03.12.2018.
 */
public class BlockColoredWater extends BlockFluidClassic {

    public BlockColoredWater(String unlName, String regName, Fluid fluid, Material material) {
        super(fluid, material);
        this.setUnlocalizedName(unlName);
        this.setRegistryName(regName);
        this.setCreativeTab(Reference.creativeTab);
        this.canCreateSources = true;
    }

    // TODO: remove, when fixed in Forge (for a long enough time).
    // ================================= some fixes to Forge methods =============================================

    // Fixes a bug in the Forge method, which this method overrides (see next comment)
    @Override
    public float getFluidHeightForRender(IBlockAccess world, BlockPos pos, @Nonnull IBlockState up)
    {
        IBlockState here = world.getBlockState(pos);
        if (here.getBlock() == this)
        {
            if (isFluid(up))
            {
                return 1;
            }

            if (getMetaFromState(here) == getMaxRenderHeightMeta())
            {
                return quantaFraction;
            }
        }
        // BlockLiquid is the vanilla Block, I instead use isFluid() which also checks for Forge IFluidBlock. This makes that fluid blocks render correctly (fluid surfaces connect).
        if (isFluid(here))
        {
            return Math.min(1 - BlockLiquid.getLiquidHeightPercent(here.getValue(BlockLiquid.LEVEL)), quantaFraction);
        }
        return !here.getMaterial().isSolid() && up.getBlock() == this ? 1 : this.getQuantaPercentage(world, pos) * quantaFraction;
    }

    // Had to copy this method from BlockFluidBase because the access was private.
    private static boolean isFluid(@Nonnull IBlockState blockstate)
    {
        return blockstate.getMaterial().isLiquid() || blockstate.getBlock() instanceof IFluidBlock;
    }

    // Fixes a bug in the Forge method, which this method overrides (see next comment).
    @Override
    public float getFilledPercentage(IBlockAccess world, BlockPos pos)
    {
        IBlockState blockAbove = world.getBlockState(pos.up());
        // Checks if the above Block is a Forge FluidBlock and returns 1 if true... this means, that the ForgeHooks method isInsideOfMaterial() correctly says that the whole block space is filled with a fluid.
        if (isFluid(blockAbove)) {
            return 1f;
        }

        int quantaRemaining = getQuantaValue(world, pos); // quantaPerBlock - Level .... or -1 if this is not a fluid block / 0 if it is Air.
        float remaining = (quantaRemaining + 1f) / (quantaPerBlockFloat + 1f); // I don't get why + 1f on both. So that, if quantaRemaining is 0, we still have 0.11 (when quantaPerBlockFloat is 8 for example)
        return remaining * (density > 0 ? 1 : -1);
    }

    // ======================= END Forge method fixes =========================================================
}