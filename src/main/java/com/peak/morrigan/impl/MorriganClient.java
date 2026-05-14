package com.peak.morrigan.impl;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.event.client.CultistKeybindIndicatorEvent;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.index.MorriganEntities;
import com.peak.morrigan.impl.index.MorriganItems;
import com.peak.morrigan.impl.index.MorriganNetworking;
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

        HudRenderCallback.EVENT.register(new CultistKeybindIndicatorEvent());
    }
}
