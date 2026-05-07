package com.peak.morrigan.impl.item;

import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.morrigan.impl.entity.CycleEntity;
import com.peak.morrigan.impl.index.MorriganSounds;
import net.chemthunder.lux.api.LuxFlashRenderer;
import net.chemthunder.lux.impl.util.Easing;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CelestialPocketwatchItem extends Item {
    public CelestialPocketwatchItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        CycleEntity cycleEntity = new CycleEntity(world);

        cycleEntity.setPos(user.getX(), user.getY() + 2.0f, user.getZ());

        cycleEntity.setOwner(user);

        world.spawnEntity(cycleEntity);

        world.getPlayers().forEach(playerEntity -> {
            playerEntity.playSoundToPlayer(MorriganSounds.POCKETWATCH_TICK, SoundCategory.PLAYERS, 1, 1);
        });

        List<PlayerEntity> nearbyPlayers = world.getEntitiesByClass(PlayerEntity.class, new Box(user.getBlockPos()).expand(250), entity -> true);
        nearbyPlayers.forEach(playerEntity -> {
            if (playerEntity instanceof ScreenShaker shaker) {
                shaker.addScreenShake(1.0f, 90);
                LuxFlashRenderer.sendFlash(playerEntity, 0xc882ff, Easing.linear, 0);
            }
        });

        return super.use(world, user, hand);
    }
}
