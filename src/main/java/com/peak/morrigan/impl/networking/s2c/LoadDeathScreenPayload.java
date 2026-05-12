package com.peak.morrigan.impl.networking.s2c;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.client.screen.SeverDeathScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

/**
 * @author Chemthunder
 */
public record LoadDeathScreenPayload() implements CustomPayload {
    public static final Id<LoadDeathScreenPayload> ID = new Id<>(Morrigan.id("load_death_screen"));

    public static final PacketCodec<RegistryByteBuf, LoadDeathScreenPayload> CODEC = PacketCodec.unit(new LoadDeathScreenPayload());

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<LoadDeathScreenPayload> {
        public void receive(LoadDeathScreenPayload loadDeathScreenPayload, ClientPlayNetworking.Context context) {
            context.client().setScreen(new SeverDeathScreen());
        }
    }
}
