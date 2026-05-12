package com.peak.morrigan.impl.util;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.morrigan.impl.cca.entity.EnchancementDataComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class MorriganKeybindingsManager {

    public static void morrigan$returningroots(PlayerEntity player, World world) {
        player.sendMessage(Text.of("aaa"));

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
    }
}
