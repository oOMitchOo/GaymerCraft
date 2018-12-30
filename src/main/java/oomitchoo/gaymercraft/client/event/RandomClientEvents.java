package oomitchoo.gaymercraft.client.event;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
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
        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlockVertSlab) {
            RayTraceResult target = event.getTarget();
            if (target.typeOfHit == RayTraceResult.Type.BLOCK) { // check if player is looking at a block.
                BlockPos blockpos = target.getBlockPos();
                IBlockState targetBlock = player.getEntityWorld().getBlockState(blockpos);
                EnumFacing sideHit = target.sideHit;
                if (player.world.getBlockState(blockpos.offset(sideHit)).getMaterial() == Material.AIR) { // check if block is unobstructed on that face the player is looking at.
                    if(targetBlock.getMaterial() != Material.AIR && !targetBlock.getBlock().isReplaceable(player.world, blockpos) && player.getEntityWorld().getWorldBorder().contains(blockpos)) {
                        if (targetBlock.getBlock() instanceof BlockVertSlabBase && !((BlockVertSlabBase) targetBlock.getBlock()).isDouble() && sideHit != targetBlock.getValue(BlockVertSlabBase.FACING).getOpposite()) // todo: 1.13: get rid of the getOpposite().
                            GaymerCraft.proxy.drawLinesForVertSlabPlacement(player, targetBlock, blockpos, sideHit, true, event.getPartialTicks());

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