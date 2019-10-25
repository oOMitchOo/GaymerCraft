package oomitchoo.gaymercraft.client.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import oomitchoo.gaymercraft.block.VertSlabBlock;
import oomitchoo.gaymercraft.init.ModItems;
import oomitchoo.gaymercraft.reference.Reference;
import oomitchoo.gaymercraft.state.properties.VertSlabType;

import static oomitchoo.gaymercraft.client.renderer.RenderGlobalModded.drawLinesForVertSlabPlacement;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID)
public class RandomClientEvents {

    @SubscribeEvent
    public static void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        Entity player = event.getInfo().getRenderViewEntity();

        // todo: Check, if there is a better solution than comparing the registry name to vert_slab.
        if(player instanceof PlayerEntity && (((PlayerEntity) player).getHeldItemMainhand().getItem().getRegistryName().getPath().contains("vert_slab") || (((PlayerEntity) player).getHeldItemMainhand().isEmpty()) && ((PlayerEntity) player).getHeldItemOffhand().getItem().getRegistryName().getPath().contains("vert_slab")) ) {
            RayTraceResult target = event.getTarget();
            // Check if player is looking at a block.
            if(target.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = ((BlockRayTraceResult)event.getTarget()).getPos();
                BlockState targetBlock = player.getEntityWorld().getBlockState(blockpos);
                Direction sidePlacingOn = ((BlockRayTraceResult) event.getTarget()).getFace();
                //BlockState offsetBlock = player.getEntityWorld().getBlockState(blockpos.offset(sidePlacingOn));
                // Check if block is unobstructed on that face the player is looking at.
                if(player.getEntityWorld().getBlockState(blockpos.offset(sidePlacingOn)).getMaterial().isReplaceable()) {
                    // Some checks on the block we want to draw the helping lines on, before it happens
                    if (!targetBlock.isAir(player.getEntityWorld(), blockpos) && !targetBlock.getMaterial().isReplaceable() && player.getEntityWorld().getWorldBorder().contains(blockpos)) {
                        // If it is a vert half slab, set the boolean in the method (isVertSalb) true. (isVertSlab is more like isVertSlabAndHalfFace)
                        if (targetBlock.getBlock() instanceof VertSlabBlock && targetBlock.get(VertSlabBlock.TYPE) != VertSlabType.DOUBLE && !sidePlacingOn.getName().equals(targetBlock.get(VertSlabBlock.TYPE).getName()) && !sidePlacingOn.getOpposite().getName().equals(targetBlock.get(VertSlabBlock.TYPE).getName())) {
                            drawLinesForVertSlabPlacement(event.getInfo(), (PlayerEntity) player, targetBlock, blockpos, sidePlacingOn, true, event.getPartialTicks());
                        }

                        // If it isn't a vert half slab, only draw the lines on the block face, if it is solid face and set the boolean (isVertSlab) to false.
                        else if (Block.doesSideFillSquare(targetBlock.getShape(player.getEntityWorld(), blockpos), sidePlacingOn))
                            drawLinesForVertSlabPlacement(event.getInfo(), (PlayerEntity) player, targetBlock, blockpos, sidePlacingOn, false, event.getPartialTicks());
                    }

                }
            }
        }
    }
}
