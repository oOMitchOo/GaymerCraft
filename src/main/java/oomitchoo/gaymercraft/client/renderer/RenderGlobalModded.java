package oomitchoo.gaymercraft.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT)
public class RenderGlobalModded {
    public static void drawLinesForVertSlabPlacement(ActiveRenderInfo info, PlayerEntity player, BlockState targetBlock, BlockPos blockpos, Direction sideHit, boolean isVertSlab, float partialTicks) {
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.lineWidth(Math.max(5.0F, (float) Minecraft.getInstance().mainWindow.getFramebufferWidth() / 1920.0F * 2.5F));
        GlStateManager.disableTexture();
        GlStateManager.depthMask(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.scalef(1.0F, 1.0F, 0.999F);

        double d3 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
        double d4 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
        double d5 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;

        double d0 = info.getProjectedView().getX();
        double d1 = info.getProjectedView().getY();
        double d2 = info.getProjectedView().getZ();

        drawLinesOnTargetedBlockFace(sideHit, targetBlock.getShape(player.getEntityWorld(), blockpos, ISelectionContext.forEntity(info.getRenderViewEntity())).getBoundingBox(),(double)blockpos.getX() - d0, (double)blockpos.getY() - d1, (double)blockpos.getZ() - d2, 1.0F, 1.0F, 1.0F, 0.5F, !isVertSlab);

        GlStateManager.depthMask(true);
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }

    private static void drawLinesOnTargetedBlockFace(Direction face, AxisAlignedBB boundingBox, double xIn, double yIn, double zIn, float red, float green, float blue, float alpha, boolean drawExtraLines) {
        double offset = 0.001D;
        double minX = boundingBox.minX + xIn;
        double minY = boundingBox.minY + yIn;
        double minZ = boundingBox.minZ + zIn;
        double maxX = boundingBox.maxX + xIn;
        double maxY = boundingBox.maxY + yIn;
        double maxZ = boundingBox.maxZ + zIn;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);

        switch (face) {

            case DOWN:
                drawOutlinesOfUpOrDownFace(bufferbuilder, minY-offset, minX+offset, minZ+offset, maxX-offset, maxZ-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case UP:
                drawOutlinesOfUpOrDownFace(bufferbuilder, maxY+offset, minX+offset, minZ+offset, maxX-offset, maxZ-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case NORTH:
                drawOutlinesOfNorthOrSouthFace(bufferbuilder, minZ-offset, minX+offset, minY+offset, maxX-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case SOUTH:
                drawOutlinesOfNorthOrSouthFace(bufferbuilder, maxZ+offset, minX+offset, minY+offset, maxX-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case WEST:
                drawOutlinesOfWestOrEastFace(bufferbuilder, minX-offset, minZ+offset, minY+offset, maxZ-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
                break;
            case EAST:
                drawOutlinesOfWestOrEastFace(bufferbuilder, maxX+offset, minZ+offset, minY+offset, maxZ-offset, maxY-offset, red, green, blue, alpha, drawExtraLines);
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
