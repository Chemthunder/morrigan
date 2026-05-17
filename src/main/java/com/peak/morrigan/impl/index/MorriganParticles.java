package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.SimpleParticleType;

/**
 * @author Chemthunder
 */
public interface MorriganParticles {
    ParticleTypeRegistrant PARTICLES = new ParticleTypeRegistrant(Morrigan.MOD_ID);

    SimpleParticleType RAGING = PARTICLES.register("raging", FabricParticleTypes.simple(true));

    static void init() {}

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(RAGING, EndRodParticle.Factory::new);
    }
}
