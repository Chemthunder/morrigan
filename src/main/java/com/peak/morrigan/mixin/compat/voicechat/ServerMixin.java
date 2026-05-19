package com.peak.morrigan.mixin.compat.voicechat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.morrigan.impl.util.ModUtils;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import de.maxhenkel.voicechat.voice.common.SoundPacket;
import de.maxhenkel.voicechat.voice.server.ClientConnection;
import de.maxhenkel.voicechat.voice.server.Server;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author AcoYT
 * @author Chemthunder
 */
@Mixin(value = Server.class)
public abstract class ServerMixin {
    @WrapMethod(method = "sendSoundPacket")
    private void morrigan$deafenDissenters(ServerPlayerEntity sender, PlayerState senderState, ServerPlayerEntity receiver, PlayerState receiverState, ClientConnection connection, SoundPacket<?> soundPacket, String source, Operation<Void> original) {
        if (ModUtils.getCultistInstance(receiver).isHeretic()) {
            return;
        }

        original.call(sender, senderState, receiver, receiverState, connection, soundPacket, source);
    }
}