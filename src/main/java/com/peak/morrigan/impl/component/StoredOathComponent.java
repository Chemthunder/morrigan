package com.peak.morrigan.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.peak.morrigan.api.Oath;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

/**
 * @author Chemthunder
 */
public record StoredOathComponent(Oath oath) {
    public static final Codec<StoredOathComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Oath.CODEC.optionalFieldOf("oath", Oath.EMPTY).forGetter(StoredOathComponent::oath)
    ).apply(instance, StoredOathComponent::new));

    public static final PacketCodec<ByteBuf, StoredOathComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}