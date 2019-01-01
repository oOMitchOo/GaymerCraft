package oomitchoo.gaymercraft.client.renderer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by oOMitchOo on 01.01.2019.
 */
@SideOnly(Side.CLIENT)
public class RenderGlobalModded {

    public static void drawLinesForVertSlabPlacement(EntityPlayer player, IBlockState targetBlock, BlockPos blockpos, EnumFacing sideHit, boolean isVertSlab, float partialTicks) {
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

        buffer.pos(minX, minY, constantZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt
    }

    private static void drawThreeSectionsOnWestOrEastFace (BufferBuilder buffer, double constantX, double minZ, double minY, double maxZ, double maxY, float red, float green, float blue, float alpha) {
        buffer.pos(constantX, minY, minZ+((maxZ-minZ)*0.25)).color(red, green, blue, 0.0F).endVertex(); // Vertikal-Balken
        buffer.pos(constantX, maxY, minZ+((maxZ-minZ)*0.25)).color(red, green, blue, alpha).endVertex();
        buffer.pos(constantX, maxY, minZ+((maxZ-minZ)*0.75)).color(red, green, blue, 0.0F).endVertex();
        buffer.pos(constantX, minY, minZ+((maxZ-minZ)*0.75)).color(red, green, blue, alpha).endVertex();

        buffer.pos(constantX, minY, minZ).color(red, green, blue, 0.0F).endVertex(); // End-Punkt
    }
}