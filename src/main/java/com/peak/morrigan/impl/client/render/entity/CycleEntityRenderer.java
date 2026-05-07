package com.peak.morrigan.impl.client.render.entity;

import com.nitron.nitrogen.render.RenderUtils;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.entity.CycleEntity;
import com.peak.morrigan.impl.util.MorriganContext;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;

/**
 * @author Chemthunder
 */
public class CycleEntityRenderer extends EntityRenderer<CycleEntity> {
    private static final Identifier BOX_TEXTURE = Morrigan.id("textures/render/box_border.png");

    public CycleEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public void render(CycleEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.translate(-entity.getPos().getX(), -entity.getPos().getY(), -entity.getPos().getZ());

        MorriganContext.renderTexturedCube(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(BOX_TEXTURE)),
                entity.getX() - 0.5f,
                entity.getY(),
                entity.getZ() - 0.5f,
                entity.getBoxSize(),
                Vec2f.ZERO,
                16
        );

        matrices.pop();
    }

    public Identifier getTexture(CycleEntity entity) {
        return null;
    }

    public boolean shouldRender(CycleEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
