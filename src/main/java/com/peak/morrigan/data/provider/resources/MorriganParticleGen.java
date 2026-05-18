package com.peak.morrigan.data.provider.resources;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.MorriganParticles;
import net.acoyt.acornlib.data.provider.resources.AcornParticleProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class MorriganParticleGen extends AcornParticleProvider {
    public MorriganParticleGen(FabricDataOutput output) {
        super(output);
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(MorriganParticles.RAGING, Morrigan.id("raging"));

      //  consumer.accept(MorriganParticles.SHOCKWAVE, morrigan$rangeBetween(Morrigan.id("shockwave/shockwave"), 1, 8));
    }

    public final Identifier[] morrigan$rangeBetween(Identifier texture, int minInclusive, int maxInclusive) {
        Identifier[] textures = new Identifier[maxInclusive - minInclusive + 1];
        for(int i = minInclusive; i <= maxInclusive; i++) {
            textures[i] = texture.withSuffixedPath("_" + i);
        }

        return textures;
    }
}
