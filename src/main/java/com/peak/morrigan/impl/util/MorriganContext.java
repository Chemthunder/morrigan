package com.peak.morrigan.impl.util;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;

/**
 * @author Chemthunder
 * @author n1tr0nr
 */
// Tweaks to existing Nitrogen methods to allow for more specific placement.
public class MorriganContext {
    public static void renderTexturedCube(MatrixStack matrices, VertexConsumer vertices, double x, double y, double z, float inflation, Vec2f timeOffset, float tileSize) {
        MatrixStack.Entry entry = matrices.peek();
        float x0 = (float) x + 0.5F - inflation;
        float x1 = (float) x + 0.5F + inflation;
        float y0 = (float) y + 0.5F - inflation;
        float y1 = (float) y + 0.5F + inflation;
        float z0 = (float) z + 0.5F - inflation;
        float z1 = (float) z + 0.5F + inflation;
        float u0 = 0.0F;
        float u1 = 1.0F;
        float v0 = 0.0F;
        float v1 = 1.0F;
        u0 *= tileSize;
        u1 *= tileSize;
        v0 *= tileSize;
        v1 *= tileSize;
        float textureOffsetX = timeOffset.x;
        v0 += textureOffsetX;
        v1 += textureOffsetX;
        float textureOffsetY = timeOffset.y;
        u0 += textureOffsetY;
        u1 += textureOffsetY;
        renderQuad(entry, vertices, x0, y0, z1, x1, y0, z1, x1, y1, z1, x0, y1, z1, u0, v0, u1, v1);
        renderQuad(entry, vertices, x1, y0, z0, x0, y0, z0, x0, y1, z0, x1, y1, z0, u0, v0, u1, v1);
        renderQuad(entry, vertices, x0, y0, z0, x0, y0, z1, x0, y1, z1, x0, y1, z0, u0, v0, u1, v1);
        renderQuad(entry, vertices, x1, y0, z1, x1, y0, z0, x1, y1, z0, x1, y1, z1, u0, v0, u1, v1);
        renderQuad(entry, vertices, x0, y1, z1, x1, y1, z1, x1, y1, z0, x0, y1, z0, u0, v0, u1, v1);
        renderQuad(entry, vertices, x0, y0, z0, x1, y0, z0, x1, y0, z1, x0, y0, z1, u0, v0, u1, v1);
    }

    private static void renderQuad(MatrixStack.Entry matrix, VertexConsumer vertices, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float u1, float v1, float u2, float v2) {
        renderVertex(matrix, vertices, x1, y1, z1, u1, v1);
        renderVertex(matrix, vertices, x2, y2, z2, u2, v1);
        renderVertex(matrix, vertices, x3, y3, z3, u2, v2);
        renderVertex(matrix, vertices, x4, y4, z4, u1, v2);
    }

    private static void renderVertex(MatrixStack.Entry matrix, VertexConsumer vertices, int color, float x, float y, float z) {
        int a = color >> 24 & 255;
        int r = color >> 16 & 255;
        int g = color >> 8 & 255;
        int b = color & 255;
        vertices.vertex(matrix, x, y, z).color(r, g, b, a).texture(0.0F, 0.0F).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(matrix, 0.0F, 1.0F, 0.0F);
    }

    private static void renderVertex(MatrixStack.Entry matrix, VertexConsumer vertices, float x, float y, float z, float u, float v) {
        vertices.vertex(matrix, x, y, z).color(1.0F, 1.0F, 1.0F, 1.0F).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(matrix, 0.0F, 1.0F, 0.0F);
    }
}