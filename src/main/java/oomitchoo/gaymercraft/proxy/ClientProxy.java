package oomitchoo.gaymercraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import oomitchoo.gaymercraft.block.BlockHedge;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockStoneVertSlab2;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab1;
import oomitchoo.gaymercraft.block.baseslabs.BlockWoodVertSlab2;
import oomitchoo.gaymercraft.client.BlockColorsHedge;
import oomitchoo.gaymercraft.client.ItemColorsHedge;
import oomitchoo.gaymercraft.client.renderer.entity.RenderUnicorn;
import oomitchoo.gaymercraft.entity.EntityUnicorn;
import oomitchoo.gaymercraft.init.ModBlocks;
import oomitchoo.gaymercraft.reference.Reference;

import javax.annotation.Nonnull;

/**
 * Created by oOMitchOo on 21.11.2018.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerItemRendererNoSubt(Item item, int metaData, String regNameAddition, String id) {
        ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(item.getRegistryName()+regNameAddition, id));
    }

    @Override
    public void registerItemRendererWithSubt(ItemBlock itemBlock, String id) { //todo: UNBEDINGT elegantere Lösung, damit ich nicht für jedes Item(-Block) mit Subtypes ein eigenes if-statement machen muss.
        if (itemBlock.getBlock() instanceof BlockStoneVertSlab1) {
            for (BlockStoneVertSlab1.EnumType blockslabs$enumtype : BlockStoneVertSlab1.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockStoneVertSlab2) {
            for (BlockStoneVertSlab2.EnumType blockslabs$enumtype : BlockStoneVertSlab2.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockWoodVertSlab1) {
            for (BlockWoodVertSlab1.EnumType blockslabs$enumtype : BlockWoodVertSlab1.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockWoodVertSlab2) {
            for (BlockWoodVertSlab2.EnumType blockslabs$enumtype : BlockWoodVertSlab2.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockslabs$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockslabs$enumtype.getName(), id));
            }
        } else if (itemBlock.getBlock() instanceof BlockHedge) {
            for (BlockPlanks.EnumType blockleaves$enumtype : BlockPlanks.EnumType.values()) {
                ModelLoader.setCustomModelResourceLocation(itemBlock, blockleaves$enumtype.getMetadata(), new ModelResourceLocation(itemBlock.getRegistryName()+"_"+blockleaves$enumtype.getName(), id));
            }
        }
    }

    @Override
    public void mapFluidState(Block block, Fluid fluid) {
        Item item = Item.getItemFromBlock(block);
        FluidStateMapper mapper = new FluidStateMapper(fluid);
        if(item!= Items.AIR)
        {
            ModelLoader.registerItemVariants(item);
            ModelLoader.setCustomMeshDefinition(item, mapper);
        }
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    @Override
    public void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityUnicorn.class, RenderUnicorn::new);
    }

    @Override
    public void registerColoredBlocks() {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new BlockColorsHedge(), ModBlocks.BLOCK_HEDGE);
    }

    @Override
    public void registerColoredItems() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ItemColorsHedge(), ModBlocks.BLOCK_HEDGE);
    }

    @Override
    public void drawLinesForVertSlabPlacement(EntityPlayer player, IBlockState targetBlock, BlockPos blockpos, EnumFacing sideHit, boolean isVertSlab, float partialTicks) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(5.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);

        double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;

        drawLinesOnTargetedBlockFace(sideHit, targetBlock.getSelectedBoundingBox(player.getEntityWorld(), blockpos).offset(-d3, -d4, -d5), 1.0F, 1.0F, 1.0F, 0.5F, !isVertSlab);

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private static void drawLinesOnTargetedBlockFace(EnumFacing face, AxisAlignedBB targetBlockFullBoundingBox, float red, float green, float blue, float alpha, boolean drawExtraLines) {
        double minX = targetBlockFullBoundingBox.minX;
        double minY = targetBlockFullBoundingBox.minY;
        double minZ = targetBlockFullBoundingBox.minZ;
        double maxX = targetBlockFullBoundingBox.maxX;
        double maxY = targetBlockFullBoundingBox.maxY;
        double maxZ = targetBlockFullBoundingBox.maxZ;
        double offset = 0.0010000000474974513D;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);

        switch (face) {

            case DOWN:
                drawOutlinesOfUpOrDownFace(buffer, minY-offset, minX+offset, minZ+offset, maxX-offset, maxZ-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case UP:
                drawOutlinesOfUpOrDownFace(buffer, maxY+offset, minX+offset, minZ+offset, maxX-offset, maxZ-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case NORTH:
                drawOutlinesOfNorthOrSouthFace(buffer, minZ-offset, minX+offset, minY+offset, maxX-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case SOUTH:
                drawOutlinesOfNorthOrSouthFace(buffer, maxZ+offset, minX+offset, minY+offset, maxX-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case WEST:
                drawOutlinesOfWestOrEastFace(buffer, minX-offset, minZ+offset, minY+offset, maxZ-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case EAST:
                drawOutlinesOfWestOrEastFace(buffer, maxX+offset, minZ+offset, minY+offset, maxZ-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
        }

        tessellator.draw();
    }

    private static void drawOutlinesOfUpOrDownFace(BufferBuilder buffer, double constantY, double minX, double minZ, double maxX, double maxZ, float red, float green, float blue, float alpha, boolean drawCross) {
        buffer.pos(minX, constantY, minZ).color(red, green, blue, 0.0F).endVertex(); // Anf-Punkt
        buffer.pos(minX, constantY, minZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(maxX, constantY, minZ).color(red, green, blue, alpha).endVertex(); // Quadrat
        buffer.pos(maxX, constantY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(minX, constantY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(minX, constantY, minZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(minX, constantY, minZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt

        if (drawCross)
            drawCrossOnUpOrDownFace(buffer, constantY, minX, minZ, maxX, maxZ, red, green, blue, alpha);
    }

    private static void drawOutlinesOfNorthOrSouthFace(BufferBuilder buffer, double constantZ, double minX, double minY, double maxX, double maxY, float red, float green, float blue, float alpha, boolean drawSections) {
        buffer.pos(minX, minY, constantZ).color(red, green, blue, 0.0F).endVertex(); // Anf-Punkt
        buffer.pos(minX, minY, constantZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(maxX, minY, constantZ).color(red, green, blue, alpha).endVertex(); // Quadrat
        buffer.pos(maxX, maxY, constantZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(minX, maxY, constantZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(minX, minY, constantZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(minX, minY, constantZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt

        if (drawSections)
            drawThreeSectionsOnNorthOrSouthFace(buffer, constantZ, minX, minY, maxX, maxY, red, green, blue, alpha);
    }

    private static void drawOutlinesOfWestOrEastFace(BufferBuilder buffer, double constantX, double minZ, double minY, double maxZ, double maxY, float red, float green, float blue, float alpha, boolean drawSections) {
        buffer.pos(constantX, minY, minZ).color(red, green, blue, 0.0F).endVertex(); // Anf-Punkt
        buffer.pos(constantX, minY, minZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(constantX, minY, maxZ).color(red, green, blue, alpha).endVertex(); // Quadrat
        buffer.pos(constantX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(constantX, maxY, minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(constantX, minY, minZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(constantX, minY, minZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt

        if (drawSections)
            drawThreeSectionsOnWestOrEastFace(buffer, constantX, minZ, minY, maxZ, maxY, red, green, blue, alpha);
    }

    private static void drawCrossOnUpOrDownFace (BufferBuilder buffer, double constantY, double minX, double minZ, double maxX, double maxZ, float red, float green, float blue, float alpha) {
        buffer.pos(maxX, constantY, maxZ).color(red, green, blue, alpha).endVertex(); // Diagonalen
        buffer.pos(minX, constantY, maxZ).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(maxX, constantY, minZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(maxX, constantY, minZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt
    }

    private static void drawThreeSectionsOnNorthOrSouthFace (BufferBuilder buffer, double constantZ, double minX, double minY, double maxX, double maxY, float red, float green, float blue, float alpha) {
        buffer.pos(minX+((maxX-minX)*0.25), minY, constantZ).color(red, green, blue, 0.0F).endVertex(); // Vertikal-Balken
        buffer.pos(minX+((maxX-minX)*0.25), maxY, constantZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(minX+((maxX-minX)*0.75), maxY, constantZ).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(minX+((maxX-minX)*0.75), minY, constantZ).color(red, green, blue, alpha).endVertex();

        buffer.pos(minX, minY, constantZ).color(red, green, blue, 0.0F).endVertex();
    }

    private static void drawThreeSectionsOnWestOrEastFace (BufferBuilder buffer, double constantX, double minZ, double minY, double maxZ, double maxY, float red, float green, float blue, float alpha) {
        buffer.pos(constantX, minY, minZ+((maxZ-minZ)*0.25)).color(red, green, blue, 0.0F).endVertex(); // Vertikal-Balken
        buffer.pos(constantX, maxY, minZ+((maxZ-minZ)*0.25)).color(red, green, blue, alpha).endVertex();
        buffer.pos(constantX, maxY, minZ+((maxZ-minZ)*0.75)).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(constantX, minY, minZ+((maxZ-minZ)*0.75)).color(red, green, blue, alpha).endVertex();

        buffer.pos(constantX, minY, minZ).color(red, green, blue, 0.0F).endVertex();
    }

    private static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {
        public final ModelResourceLocation location;

        public FluidStateMapper(Fluid fluid) {
            this.location = new ModelResourceLocation(Reference.MOD_ID+":fluid_block", fluid.getName());
        }

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return location;
        }

        @Nonnull
        @Override
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            return location;
        }
    }
}
