package com.peak.morrigan.mixin.client;

import com.peak.morrigan.impl.cca.entity.LightDarkComponent;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    private <E extends Entity> void morrigan$toggleInvisOath(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {

        if (entity != null) {
            if (entity instanceof PlayerEntity player) {
                if (LightDarkComponent.KEY.get(player).isInvis()) {
                    ci.cancel();
                }
            }
        }
    }
}
