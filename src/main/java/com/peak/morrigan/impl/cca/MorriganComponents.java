package com.peak.morrigan.impl.cca;

import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.InBoxComponent;
import com.peak.morrigan.impl.cca.entity.LockMovementComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.cca.entity.roots.RootsEmitterComponent;
import com.peak.morrigan.impl.cca.entity.roots.RootsVictimComponent;
import com.peak.morrigan.impl.cca.world.CultDataComponent;
import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

/**
 * @author Chemthunder
 */
public class MorriganComponents implements EntityComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(
                CultistComponent.KEY,
                CultistComponent::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );

        registry.registerForPlayers(
                RootsVictimComponent.KEY,
                RootsVictimComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );

        registry.registerForPlayers(
                RootsEmitterComponent.KEY,
                RootsEmitterComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );

        registry.registerForPlayers(
                AshProfileComponent.KEY,
                AshProfileComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );

        registry.beginRegistration(
                LivingEntity.class,
                InBoxComponent.KEY
        ).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(InBoxComponent::new);

        registry.beginRegistration(
                LivingEntity.class,
                LockMovementComponent.KEY
        ).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(LockMovementComponent::new);
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(
                CultDataComponent.KEY,
                CultDataComponent::new
        );
    }
}