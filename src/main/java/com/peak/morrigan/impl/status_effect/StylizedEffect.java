package com.peak.morrigan.impl.status_effect;

import com.nitron.nitrogen.util.interfaces.StatusEffectBackground;
import com.peak.morrigan.impl.Morrigan;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class StylizedEffect extends StatusEffect implements StatusEffectBackground {
    public StylizedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public Identifier smallInventorySprite() {
        return Morrigan.id("effect/" + this.getName().getString().toLowerCase() + "_small");
    }

    public Identifier largeInventorySprite() {
        return Morrigan.id("effect/" + this.getName().getString().toLowerCase() + "_large");
    }

    public Identifier hudSprite() {
        return Morrigan.id("effect/" + this.getName().getString().toLowerCase() + "_bg");
    }
}