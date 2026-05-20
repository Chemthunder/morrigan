package com.peak.morrigan.impl.util;

import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.LightDarkComponent;
import com.peak.morrigan.impl.cca.entity.LockMovementComponent;
import com.peak.morrigan.impl.cca.entity.roots.RootsEmitterComponent;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganParticles;
import com.peak.morrigan.impl.index.MorriganStatusEffects;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class MorriganKeybindingsManager {
    public static void morrigan$returningroots(PlayerEntity player, World world) {
        RootsEmitterComponent emitter = RootsEmitterComponent.KEY.get(player);
        emitter.setEmitting(!emitter.isEmitting());

        if (world.isClient()) {
            player.swingHand(player.getActiveHand());
            player.sendMessage(Text.translatable("oath.morrigan.returning_roots.heads_up", emitter.isEmitting()), true);
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

            player.sendMessage(Text.literal(MiscUtils.formatString(profile.getCurrentProfile().id())).withColor(profile.getCurrentProfile().color()), true);

            ModUtils.getCultistInstance(player).setKeybindCooldownTicks(10);
        } else {
            if (profile.getCurrentProfile().hasAbility()) {
                if (profile.getCurrentProfile() == MorriganAshProfiles.FLORACIDE) {
                    LivingEntity target = null;


                    if (MinecraftClient.getInstance().targetedEntity instanceof LivingEntity entity) {
                        target = entity;
                    }

                    if (target != null) {
                        LockMovementComponent lock = LockMovementComponent.KEY.get(target);

                        player.sendMessage(Text.of(target.getNameForScoreboard()), true);

                        lock.setTicks(90);
                        player.sendMessage(Text.of(lock.getTicks() + ""), false);
                    } else {
                        LockMovementComponent lock = LockMovementComponent.KEY.get(player);
                        lock.setTicks(90);
                    }
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

                    ModUtils.getCultistInstance(player).setKeybindCooldownTicks(9600);
                }

                if (profile.getCurrentProfile() == MorriganAshProfiles.AEROCIDE) {
                    if (world instanceof ServerWorld serverWorld) {
                        player.addStatusEffect(
                                new StatusEffectInstance(
                                        MorriganStatusEffects.FEARFUL,
                                        100
                                )
                        );

                        serverWorld.spawnParticles(
                                ParticleTypes.GUST_EMITTER_SMALL,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                3,
                                0.4f,
                                0.9f,
                                0.4f,
                                0.2f
                        );
                    }

                    ModUtils.getCultistInstance(player).setKeybindCooldownTicks(4800);
                }
            }
        }
    }

    public static void morrigan$sharpenedStar(PlayerEntity player, World world) {

    }

    public static void morrigan$lightDark(PlayerEntity player, World world) {
        if (world.isDay()) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.STRENGTH,
                    600,
                    1
            ));

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.RESISTANCE,
                    600,
                    1
            ));

            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOWNESS,
                    600,
                    1
            ));

            ModUtils.getCultistInstance(player).setKeybindCooldownTicks(900);
        } else {
            LightDarkComponent.KEY.get(player).setInvis(!LightDarkComponent.KEY.get(player).isInvis());
            ModUtils.getCultistInstance(player).setKeybindCooldownTicks(50);
        }
    }
}