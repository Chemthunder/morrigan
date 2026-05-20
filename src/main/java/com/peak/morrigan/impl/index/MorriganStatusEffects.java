package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.status_effect.StylizedEffect;
import net.acoyt.acornlib.api.registrants.StatusEffectRegistrant;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public interface MorriganStatusEffects {
    StatusEffectRegistrant STATUS_EFFECTS = new StatusEffectRegistrant(Morrigan.MOD_ID);

    RegistryEntry<StatusEffect> RAMPAGE = STATUS_EFFECTS.registerRef(
            "rampage",
            new StylizedEffect(
                    StatusEffectCategory.NEUTRAL,
                    0xFF882020
            )
                    .addAttributeModifier(
                            EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.ofVanilla("rampage.effect.speed"), 0.2f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            EntityAttributes.GENERIC_MAX_HEALTH, Identifier.ofVanilla("rampage.effect.max_health"), -8.0f, EntityAttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, Identifier.ofVanilla("rampage.effect.block_break_speed"), 0.3f, EntityAttributeModifier.Operation.ADD_VALUE
                    )
                    .addAttributeModifier(
                            EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.ofVanilla("rampage.effect.block_break_speed"), 0.3f, EntityAttributeModifier.Operation.ADD_VALUE
                    )
    );

    RegistryEntry<StatusEffect> FEARFUL = STATUS_EFFECTS.registerRef(
            "fearful",
            new StylizedEffect(
                    StatusEffectCategory.NEUTRAL,
                    0xFF002f5c
            )
                    .addAttributeModifier(
                            EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.ofVanilla("fearful.effect.speed"), 0.6f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
                    .addAttributeModifier(
                            EntityAttributes.GENERIC_STEP_HEIGHT, Identifier.ofVanilla("fearful.effect.step_height"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE
                    )
    );

    static void init() {}
}