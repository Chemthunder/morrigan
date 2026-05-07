package com.peak.morrigan.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.morrigan.impl.index.MorriganItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static com.peak.morrigan.impl.Morrigan.applyFormatting;

/**
 * @author Chemthunder
 */
@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin {
    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V",
                    ordinal = 2
            )
    )
    private void morrigan$deathScreenShenanigans(DrawContext instance, TextRenderer textRenderer, Text text, int centerX, int y, int color, Operation<Void> original) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        if (client.player != null) {
            if (client.player.getLastAttacker() != null) {
                if (client.player.getLastAttacker().getMainHandStack().isOf(MorriganItems.SACRIFICES_EFFIGY)) {
                    original.call(instance, textRenderer, Text.literal("your purpose is complete.").withColor(0xFFc882ff).setStyle(applyFormatting(Text.literal("your purpose is complete."))), centerX, y, color);
                } else {
                    original.call(instance, textRenderer, text, centerX, y, color);
                }
            } else {
                original.call(instance, textRenderer, text, centerX, y, color);
            }
        }
    }
}
