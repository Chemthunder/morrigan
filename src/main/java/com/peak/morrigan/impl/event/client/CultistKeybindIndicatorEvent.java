package com.peak.morrigan.impl.event.client;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.custom.MorriganAshProfiles;
import com.peak.morrigan.impl.index.custom.MorriganOaths;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class CultistKeybindIndicatorEvent implements HudRenderCallback {
    public static final Identifier EYE_OPEN = Morrigan.id("hud/eye_open");
    public static final Identifier EYE_CLOSED = Morrigan.id("hud/eye_closed");

    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        CultistComponent component = CultistComponent.KEY.get(player);
        AshProfileComponent ashProfileComponent = AshProfileComponent.KEY.get(player);

        if (MorriganOaths.HAS_ABILITY.contains(component.getOath())) {
            if (ashProfileComponent.getCurrentProfile() != MorriganAshProfiles.CRYOCIDE) {
                drawContext.drawGuiTexture(
                        component.canUseKeybind() ? EYE_OPEN : EYE_CLOSED,
                        drawContext.getScaledWindowWidth() / 2 - 8,
                        drawContext.getScaledWindowHeight() / 2 + 20,
                        15,
                        15
                );
            }
        }
    }
}