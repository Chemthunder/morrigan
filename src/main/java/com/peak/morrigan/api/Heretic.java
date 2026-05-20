package com.peak.morrigan.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * @author Chemthunder
 */
public record Heretic(String name, String uuid) {
    public static final Codec<Heretic> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("name", "").forGetter(Heretic::name),
                    Codec.STRING.optionalFieldOf("uuid", "").forGetter(Heretic::uuid)
            ).apply(instance, Heretic::new)
    );
}