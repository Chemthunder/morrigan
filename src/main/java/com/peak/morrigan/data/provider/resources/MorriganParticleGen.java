package com.peak.morrigan.data.provider.resources;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.MorriganParticles;
import net.acoyt.acornlib.data.provider.resources.AcornParticleProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

/**
 * @author Chemthunder
 */
public class MorriganParticleGen extends AcornParticleProvider {
    public MorriganParticleGen(FabricDataOutput output) {
        super(output);
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(MorriganParticles.RAGING, Morrigan.id("raging"));
    }
}
