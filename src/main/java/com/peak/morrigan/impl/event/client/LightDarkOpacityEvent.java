package com.peak.morrigan.impl.event.client;

import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.util.ModUtils;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Optional;

/**
 * @author Chemthunder
 */
public class LightDarkOpacityEvent implements PlayerOpacityEvent {
    public Optional<Double> getOpacity(PlayerEntity player) {
        CultistComponent c = ModUtils.getCultistInstance(player);

        if (c.getOath().equals(MorriganOaths.LIGHT_DARK)) {
            return Optional.of(player.getWorld().isDay() ? 0.6 : 0.4);
        }

        return Optional.empty();
    }
}