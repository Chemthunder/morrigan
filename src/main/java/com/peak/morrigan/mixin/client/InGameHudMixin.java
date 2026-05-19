package com.peak.morrigan.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.InBoxComponent;
import com.peak.morrigan.impl.util.ModUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {
    @Unique private static final Identifier CITADEL_VIGNETTE = Morrigan.id("textures/gui/overlays/citadel_vignette.png");

    @Shadow protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Inject(method = "renderMiscOverlays", at = @At(value = "TAIL"))
    private void morrigan$miscOverlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof PlayerEntity player) {
            int ticks = InBoxComponent.KEY.get(player).getInBoxTicks();
            if (ticks > 0) {
                float opacity = ticks > 50 ? 1f : ticks / 50.0f;
                this.renderOverlay(context, CITADEL_VIGNETTE, opacity);
            }
        }
    }

    @WrapMethod(method = "renderChat")
    private void morrigan$disableChatIfHeretic(DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (!ModUtils.getCultistInstance(player).isHeretic()) {
            original.call(context, tickCounter);
        }
    }
}