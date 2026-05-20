package com.peak.morrigan.impl;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.event.client.CultistKeybindIndicatorEvent;
import com.peak.morrigan.impl.event.client.LightDarkOpacityEvent;
import com.peak.morrigan.impl.index.*;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * @author Chemthunder
 */
public class MorriganClient implements ClientModInitializer {
    public void onInitializeClient() {
        MorriganNetworking.registerS2CPackets();

        MorriganEntities.clientInit();
        MorriganParticles.clientInit();

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            StoredOathComponent oathComponent = stack.get(MorriganDataComponents.STORED_OATH);

            if (tintIndex == 1) {
                if (oathComponent != null) {
                    Oath oath = oathComponent.oath();
                    return oath.color();
                }
            }
            return -1;
        }, MorriganItems.SACRIFICIAL_CLEAVER);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            StoredOathComponent oathComponent = stack.get(MorriganDataComponents.STORED_OATH);

            if (tintIndex == 1) {
                if (oathComponent != null) {
                    Oath oath = oathComponent.oath();
                    if (!oath.isEmpty()) {
                        return oath.color();
                    } else {
                        return 0xFF000000;
                    }
                }
            }
            return -1;
        }, MorriganItems.SCRYING_PAPER);

        HudRenderCallback.EVENT.register(new CultistKeybindIndicatorEvent());
        PlayerOpacityEvent.EVENT.register(new LightDarkOpacityEvent());
    }
}