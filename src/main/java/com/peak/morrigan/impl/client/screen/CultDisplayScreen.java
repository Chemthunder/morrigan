package com.peak.morrigan.impl.client.screen;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public class CultDisplayScreen extends Screen {
    private int ticks = 0;
    private int lineWidth = 0;

    public CultDisplayScreen() {
        super(Text.literal("Cultist Display Screen"));
    }

    public void tick() {
        this.ticks++;

        if (this.lineWidth < 60) {
            this.lineWidth += 7;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.applyBlur(delta);

        if (this.client == null) return;

        PlayerEntity player = this.client.player;
        if (player == null) return;

        CultistComponent cultistComponent = CultistComponent.KEY.get(player);
        Oath oath = cultistComponent.getOath();

        if (cultistComponent.isCultist()) {
            context.drawCenteredTextWithShadow(
                    this.textRenderer,
                    Text.translatable("morrigan.cult_screen.title").setStyle(Morrigan.applyFormatting(Text.translatable("morrigan.cult_screen.title"))),
                    context.getScaledWindowWidth() / 2,
                    30,
                    0xb600ff
            );

            context.drawCenteredTextWithShadow(
                    this.textRenderer,
                    oath.title(),
                    context.getScaledWindowWidth() / 2,
                    50,
                    oath.color()
            );

            context.drawCenteredTextWithShadow(
                    this.textRenderer,
                    Text.literal("Bound under " + cultistComponent.getLeader()),
                    context.getScaledWindowWidth() / 2,
                    80,
                    oath.color()
            );

            context.drawCenteredTextWithShadow(
                    this.textRenderer,
                    Text.literal("\"").append(oath.description()).append(Text.literal("\"")),
                    context.getScaledWindowWidth() / 2,
                    100,
                    oath.color()
            );

            context.drawHorizontalLine(
                    context.getScaledWindowWidth() / 2 - this.lineWidth,
                    context.getScaledWindowWidth() / 2 + this.lineWidth,
                    70,
                    oath.color()
            );

            for (int i = 0; i < 2; i++) this.renderDarkening(context);
        }
    }
}
