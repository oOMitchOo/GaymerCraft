package oomitchoo.gaymercraft.client.event;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.BlockVertSlabBase;
import oomitchoo.gaymercraft.helper.handlers.ConfigHandler;
import oomitchoo.gaymercraft.item.ItemBlockVertSlab;
import oomitchoo.gaymercraft.reference.Reference;

/**
 * Created by oOMitchOo on 30.12.2018.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MOD_ID)
public class RandomClientEvents {

    // todo: I should probably get rid of the proxy, since this already is in the client package (plus EventBusSubscriber value is Side.Client).
    @SubscribeEvent
    public static void onDrawBlockHighlightEvent (DrawBlockHighlightEvent event) {
        EntityPlayer player = event.getPlayer();
        // This if-statement will result in some helping lines (for placement of the vertical slab) on the block, the player is looking at.
        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlockVertSlab) {
            RayTraceResult target = event.getTarget();
            // Check if player is looking at a block.
            if (target.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = target.getBlockPos();
                IBlockState targetBlock = player.getEntityWorld().getBlockState(blockpos);
                EnumFacing sideHit = target.sideHit;
                // Check if block is unobstructed on that face the player is looking at.
                if (targetBlock.getBlock().isReplaceable(player.world, blockpos.offset(sideHit))) {
                    // Some checks on the block we want to draw the helping lines on, before it happens
                    if(targetBlock.getMaterial() != Material.AIR && !targetBlock.getBlock().isReplaceable(player.world, blockpos) && player.getEntityWorld().getWorldBorder().contains(blockpos)) {
                        // If it is a vert half slab (and the face hit isn't the one full face oriented out of the blockspace), set the boolean in the method (isVertSlab) true.
                        if (targetBlock.getBlock() instanceof BlockVertSlabBase && !((BlockVertSlabBase) targetBlock.getBlock()).isDouble() && sideHit != targetBlock.getValue(BlockVertSlabBase.FACING).getOpposite()) // todo: 1.13: get rid of the getOpposite().
                            GaymerCraft.proxy.drawLinesForVertSlabPlacement(player, targetBlock, blockpos, sideHit, true, event.getPartialTicks());
                        // If it isn't a vert half slab, only draw the lines on the block face, if it is solid face and set the boolean (isVertSlab) to false.
                        else if (targetBlock.getBlockFaceShape(player.world, blockpos, sideHit) == BlockFaceShape.SOLID)
                            GaymerCraft.proxy.drawLinesForVertSlabPlacement(player, targetBlock, blockpos, sideHit, false, event.getPartialTicks());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onConfigChanged (ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)) {
            // Resync configs
            ConfigHandler.loadConfig();
        }
    }
}