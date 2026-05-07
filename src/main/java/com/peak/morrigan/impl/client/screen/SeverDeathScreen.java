package com.peak.morrigan.impl.client.screen;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import net.chemthunder.lux.api.LuxFlashRenderer;
import net.chemthunder.lux.impl.util.Easing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Random;

/**
 * @author Chemthunder
 */
public class SeverDeathScreen extends Screen {

    private int age = 0;

    public SeverDeathScreen() {
        super(Text.literal(""));
    }

    public void tick() {
        this.age++;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        Text error = Text.literal("error").setStyle(apply(Text.literal("error")));

        int color = 0xffffff;
        int y = 40;

        if (this.age > 30) {
            if (this.age > 90) {
                color = 0xFFc882ff;
            }

            if (this.age > 110) {
                Random random = new Random();
                String s = "";

                if (random.nextInt(7) == 0) {
                    s = "ERROR";
                }
                if (random.nextInt(7) == 1) {
                    s = "Error";
                }
                if (random.nextInt(7) == 2) {
                    s = "eRror";
                }
                if (random.nextInt(7) == 3) {
                    s = "erRor";
                }
                if (random.nextInt(7) == 4) {
                    s = "errOr";
                }
                if (random.nextInt(7) == 5) {
                    s = "erroR";
                }

                error = Text.literal(s).setStyle(apply(Text.literal(s)));
            }

            if (this.age > 150) {
                context.getMatrices().push();

                context.drawCenteredTextWithShadow(
                        this.textRenderer,
                        Text.literal("DEATH IS ONLY THE BEGINNING.").setStyle(apply(Text.literal("DEATH IS ONLY THE BEGINNING"))).withColor(0xFFc882ff),
                        context.getScaledWindowWidth() / 2,
                        y - 20,
                        0xffffff
                );

                context.getMatrices().pop();
            }

            if (this.age > 155) {
                this.renderDarkening(context);
            }
            if (this.age > 160) {
                this.renderDarkening(context);
            }
            if (this.age > 165) {
                this.renderDarkening(context);
            }
            if (this.age > 170) {
                this.renderDarkening(context);
            }
            if (this.age > 175) {
                this.renderDarkening(context);
            }
            if (this.age > 180) {
                this.renderDarkening(context);
            }
            if (this.age > 185) {
                this.renderDarkening(context);
            }
            if (this.age > 190) {
                this.renderDarkening(context);
            }
            if (this.age > 195) {
                this.renderDarkening(context);
            }
            if (this.age > 200) {
                this.renderDarkening(context);
            }
            if (this.age > 205) {
                this.renderDarkening(context);
            }
            if (this.age > 210) {
                this.renderDarkening(context);
            }
            if (this.age > 215) {
                this.renderDarkening(context);
            }

            if (this.age > 225) {
                this.close();
            }

            if (this.age > 50) {
                context.drawCenteredTextWithShadow(
                        this.textRenderer,
                        error,
                        context.getScaledWindowWidth() / 2,
                        y,
                        color
                );
            }

            if (this.age > 55) {
                context.drawCenteredTextWithShadow(
                        this.textRenderer,
                        error,
                        context.getScaledWindowWidth() / 2,
                        y + 10,
                        color
                );
            }

            if (this.age > 57) {
                context.drawCenteredTextWithShadow(
                        this.textRenderer,
                        error,
                        context.getScaledWindowWidth() / 2,
                        y + 20,
                        color
                );
            }

            if (this.age > 60) {
                context.drawCenteredTextWithShadow(
                        this.textRenderer,
                        error,
                        context.getScaledWindowWidth() / 2,
                        y + 30,
                        color
                );
            }
        }
    }

    public boolean shouldPause() {
        return false;
    }

    private Style apply(Text text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.LERP_WAVE_EFFECT));
    }

    public boolean shouldCloseOnEsc() {
        return true;
    }
}
