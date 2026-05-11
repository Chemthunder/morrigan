package com.peak.morrigan.impl.cca;

import com.peak.morrigan.impl.cca.entity.CultistComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author Chemthunder
 */
public class MorriganComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(
                CultistComponent.KEY,
                CultistComponent::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );
    }
}
