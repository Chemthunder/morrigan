package com.peak.morrigan.impl.status_effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import com.peak.morrigan.impl.Morrigan;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class FearfulStatusEffect extends StatusEffect implements StatusEffectBackground {
    public FearfulStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public Identifier smallInventorySprite() {
        return Morrigan.id("effect/fearful_small");
    }

    public Identifier largeInventorySprite() {
        return Morrigan.id("effect/fearful_large");
    }

    public Identifier hudSprite() {
        return Morrigan.id("effect/fearful_bg");
    }
}
