package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.networking.c2s.TriggerAbilityPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/**
 * @author Chemthunder
 */
public interface MorriganNetworking {
    static void registerTypes() {}

    static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(TriggerAbilityPayload.ID, TriggerAbilityPayload.CODEC);
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {
        ServerPlayNetworking.registerGlobalReceiver(TriggerAbilityPayload.ID, new TriggerAbilityPayload.Receiver());
    }
}