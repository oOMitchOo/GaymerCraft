package oomitchoo.gaymercraft.client.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import oomitchoo.gaymercraft.GaymerCraft;
import oomitchoo.gaymercraft.block.VertSlabBlock;
import oomitchoo.gaymercraft.reference.Reference;
import oomitchoo.gaymercraft.state.properties.VertSlabType;

import static oomitchoo.gaymercraft.client.renderer.RenderGlobalModded.drawLinesForVertSlabPlacement;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID)
public class RandomClientEvents {

    @SubscribeEvent
    public static void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        Entity player = event.getInfo().getRenderViewEntity();

        // todo: Think about making the vertical_slabs Tag<Item> code-based. The only down-side is maybe that it would be harder for others to see that my items have a tag.
        if(player instanceof PlayerEntity ) {
            ResourceLocation locationVertSlabItemTag = new ResourceLocation(Reference.MOD_ID, "vertical_slabs");
            ItemStack heldItemMainHand = ((PlayerEntity) player).getHeldItemMainhand();
            Tag<Item> vertSlabItemTag = ItemTags.getCollection().get(locationVertSlabItemTag);
            if(vertSlabItemTag != null) {
                // todo: When holding the vertical slab in the offHand (everything behind ||) find a better check for the mainHand than 'instanceof BlockItem' since there are also useable items (like spawn eggs) which would get right-clicked instead of placing the vertical slab from the offHand.
                if (vertSlabItemTag.contains(heldItemMainHand.getItem()) || (!(heldItemMainHand.getItem() instanceof BlockItem) && vertSlabItemTag.contains(((PlayerEntity) player).getHeldItemOffhand().getItem()))) {
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
            } else {
                GaymerCraft.LOGGER.warn("Couldn't find vertical_slabs Tag<Item>. This should never happen.");
            }
        }
    }
}
