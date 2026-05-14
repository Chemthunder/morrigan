package com.peak.morrigan.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;

/**
 * @author Chemthunder
 */
public record AshProfile(String id, int color, boolean hasAbility, Text description) {
    public static final AshProfile EMPTY = new AshProfile("", 0xFFfbe3d5, false, Text.empty());

    public static final Codec<AshProfile> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.optionalFieldOf("ID", "").forGetter(AshProfile::id),
            Codec.INT.optionalFieldOf("color", 0xFFfbe3d5).forGetter(AshProfile::color),
            Codec.BOOL.optionalFieldOf("hasAbility", false).forGetter(AshProfile::hasAbility),
            TextCodecs.CODEC.optionalFieldOf("description", Text.empty()).forGetter(AshProfile::description)
    ).apply(instance, AshProfile::new));

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
