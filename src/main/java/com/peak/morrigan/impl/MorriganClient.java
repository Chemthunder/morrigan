package com.peak.morrigan.impl;

import com.peak.morrigan.impl.index.MorriganEntities;
import com.peak.morrigan.impl.index.MorriganNetworking;
import net.fabricmc.api.ClientModInitializer;

/**
 * @author Chemthunder
 */
public class MorriganClient implements ClientModInitializer {
    public void onInitializeClient() {
        MorriganNetworking.registerS2CPackets();

        MorriganEntities.clientInit();
    }
}
