package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.networking.s2c.LoadDeathScreenPayload;
import com.peak.morrigan.impl.networking.c2s.TriggerAbilityPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/**
 * @author Chemthunder
 */
public interface MorriganNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playS2C().register(LoadDeathScreenPayload.ID, LoadDeathScreenPayload.CODEC);
    }

    static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(TriggerAbilityPayload.ID, TriggerAbilityPayload.CODEC);
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LoadDeathScreenPayload.ID, new LoadDeathScreenPayload.Receiver());
        ServerPlayNetworking.registerGlobalReceiver(TriggerAbilityPayload.ID, new TriggerAbilityPayload.Receiver());
    }
}
