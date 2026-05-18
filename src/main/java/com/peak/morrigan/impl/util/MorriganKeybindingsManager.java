package com.peak.morrigan.impl.util;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.EnchancementDataComponent;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganParticles;
import com.peak.morrigan.impl.index.MorriganStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class MorriganKeybindingsManager {
    public static void morrigan$returningroots(PlayerEntity player, World world) {
        world.getEntitiesByClass(
                PlayerEntity.class,
                new Box(
                        player.getBlockPos()
                ).expand(15),
                entity -> true
        ).forEach(capture -> {
            EnchancementDataComponent data = EnchancementDataComponent.KEY.get(capture);

            data.setMovementRemovedTicks(120);

            if (capture instanceof ScreenShaker shaker) {
                shaker.addScreenShake(0.5f, 10);
            }
        });

        if (world.isClient()) {
            player.swingHand(player.getActiveHand());
        }

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.TRIAL_OMEN,
                    player.getX(),
                    player.getY() + 1,
                    player.getZ(),
                    15,
                    0,
                    1,
                    0,
                    0.2f
            );
        }

        ModUtils.getCultistInstance(player).setKeybindCooldownTicks(500);
    }

    public static void morrigan$sysMartyr(PlayerEntity player, World world) {
        AshProfileComponent profile = AshProfileComponent.KEY.get(player);

        if (player.isSneaking()) {
            profile.setCurrentProfile(
                    profile.getCurrentProfile() != MorriganAshProfiles.PROFILES.getLast()
                            ?
                            MorriganAshProfiles.PROFILES.get(MorriganAshProfiles.PROFILES.indexOf(profile.getCurrentProfile()) + 1)
                            :
                            MorriganAshProfiles.PROFILES.getFirst()
            );

            ModUtils.getCultistInstance(player).setKeybindCooldownTicks(50);
        } else {
            if (profile.getCurrentProfile().hasAbility()) {
                if (profile.getCurrentProfile() == MorriganAshProfiles.FLORACIDE) {
                    // ensnare
                }

                if (profile.getCurrentProfile() == MorriganAshProfiles.PYROCIDE) {
                    if (world instanceof ServerWorld serverWorld) {
                        player.addStatusEffect(
                                new StatusEffectInstance(
                                        MorriganStatusEffects.RAMPAGE,
                                        1200
                                )
                        );

                        serverWorld.spawnParticles(
                                MorriganParticles.RAGING,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                15,
                                0,
                                0.9f,
                                0,
                                0.2f
                        );
                    }

                    //ModUtils.getCultistInstance(player).setKeybindCooldownTicks(9600);
                }

                if (profile.getCurrentProfile() == MorriganAshProfiles.AEROCIDE) {
                    // fearful child
                }
            }
        }
    }
}