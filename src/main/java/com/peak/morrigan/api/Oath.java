package com.peak.morrigan.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;

/**
 * @author Chemthunder
 */
public record Oath(String id, Text title, Text description) {
    public static final Oath EMPTY = new Oath("", Text.empty(), Text.empty());

    public static final Codec<Oath> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.optionalFieldOf("ID", "").forGetter(Oath::id),
            TextCodecs.CODEC.optionalFieldOf("title", Text.empty()).forGetter(Oath::title),
            TextCodecs.CODEC.optionalFieldOf("description", Text.empty()).forGetter(Oath::description)
    ).apply(instance, Oath::new));

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
