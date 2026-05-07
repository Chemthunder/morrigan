package com.peak.morrigan.impl.cca;

import com.peak.morrigan.impl.cca.entity.PocketwatchBoxComponent;
import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author Chemthunder
 */
public class MorriganComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(LivingEntity.class, PocketwatchBoxComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(PocketwatchBoxComponent::new);
    }
}
