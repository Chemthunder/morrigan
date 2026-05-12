package com.peak.morrigan.impl.networking.c2s;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.util.MorriganKeybindingsManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record TriggerAbilityPayload() implements CustomPayload {
    public static final Id<TriggerAbilityPayload> ID = new Id<>(Morrigan.id("trigger_ability"));
    public static final PacketCodec<RegistryByteBuf, TriggerAbilityPayload> CODEC = PacketCodec.unit(new TriggerAbilityPayload());

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send() {
        ClientPlayNetworking.send(new TriggerAbilityPayload());
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<TriggerAbilityPayload> {
        public void receive(TriggerAbilityPayload payload, ServerPlayNetworking.Context context) {
            PlayerEntity player = context.player();
            if (player != null) {
                CultistComponent cultistComponent = CultistComponent.KEY.get(player);

                if (cultistComponent.isCultist()) {
                    if (cultistComponent.canUseKeybind()) {
                        Oath oath = cultistComponent.getOath();

                        if (MorriganOaths.HAS_ABILITY.contains(oath)) {
                            if (oath.equals(MorriganOaths.RETURNING_ROOTS)) {
                                MorriganKeybindingsManager.morrigan$returningroots(
                                        player,
                                        player.getWorld()
                                );
                            }

                            cultistComponent.setKeybindCooldownTicks(400);
                        }
                    }
                }
            }
        }
    }
}