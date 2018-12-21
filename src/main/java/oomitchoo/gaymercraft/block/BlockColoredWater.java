package oomitchoo.gaymercraft.block;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidBlock;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Created by oOMitchOo on 03.12.2018.
 */
public class BlockColoredWater extends BlockFluidClassic {
    protected boolean canCreateSourcesMod = true;

    public BlockColoredWater(String unlName, String regName, Fluid fluid, Material material) {
        super(fluid, material);
        this.setUnlocalizedName(unlName);
        this.setRegistryName(regName);
        this.setCreativeTab(Reference.creativeTab);
    }

    @Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand)
    {
        int quantaRemaining = quantaPerBlock - state.getValue(LEVEL);
        int expQuanta = -101;

        // check adjacent block levels if non-source
        if (quantaRemaining < quantaPerBlock)
        {
            int adjacentSourceBlocks = 0;

            if (ForgeEventFactory.canCreateFluidSource(world, pos, state, canCreateSourcesMod))
            {
                for (EnumFacing side : EnumFacing.Plane.HORIZONTAL)
                {
                    if (isSourceBlock(world, pos.offset(side))) adjacentSourceBlocks++;
                }
            }

            // new source block
            if (adjacentSourceBlocks >= 2 && (world.getBlockState(pos.up(densityDir)).getMaterial().isSolid() || isSourceBlock(world, pos.up(densityDir))))
            {
                expQuanta = quantaPerBlock;
            }
            // unobstructed flow from 'above'
            else if (world.getBlockState(pos.down(densityDir)).getBlock() == this
                    || hasDownhillFlow(world, pos, EnumFacing.EAST)
                    || hasDownhillFlow(world, pos, EnumFacing.WEST)
                    || hasDownhillFlow(world, pos, EnumFacing.NORTH)
                    || hasDownhillFlow(world, pos, EnumFacing.SOUTH))
            {
                expQuanta = quantaPerBlock - 1;
            }
            else
            {
                int maxQuanta = -100;
                for (EnumFacing side : EnumFacing.Plane.HORIZONTAL)
                {
                    maxQuanta = getLargerQuanta(world, pos.offset(side), maxQuanta);
                }
                expQuanta = maxQuanta - 1;
            }

            // decay calculation
            if (expQuanta != quantaRemaining)
            {
                quantaRemaining = expQuanta;

                if (expQuanta <= 0)
                {
                    world.setBlockToAir(pos);
                }
                else
                {
                    world.setBlockState(pos, state.withProperty(LEVEL, quantaPerBlock - expQuanta), 2);
                    world.scheduleUpdate(pos, this, tickRate);
                    world.notifyNeighborsOfStateChange(pos, this, false);
                }
            }
        }

        // Flow vertically if possible
        if (canDisplace(world, pos.up(densityDir)))
        {
            flowIntoBlock(world, pos.up(densityDir), 1);
            return;
        }

        // Flow outward if possible
        int flowMeta = quantaPerBlock - quantaRemaining + 1;
        if (flowMeta >= quantaPerBlock)
        {
            return;
        }

        if (isSourceBlock(world, pos) || !isFlowingVertically(world, pos))
        {
            if (world.getBlockState(pos.down(densityDir)).getBlock() == this)
            {
                flowMeta = 1;
            }
            boolean flowTo[] = getOptimalFlowDirections(world, pos);
            for (int i = 0; i < 4; i++)
            {
                if (flowTo[i]) flowIntoBlock(world, pos.offset(SIDES.get(i)), flowMeta);
            }
        }
    }

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
}