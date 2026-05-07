package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.networking.LoadDeathScreenPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

/**
 * @author Chemthunder
 */
public interface MorriganNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playS2C().register(LoadDeathScreenPayload.ID, LoadDeathScreenPayload.CODEC);
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LoadDeathScreenPayload.ID, new LoadDeathScreenPayload.Receiver());
    }
}
