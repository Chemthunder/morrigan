package com.peak.morrigan.impl;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import com.peak.morrigan.impl.index.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Morrigan implements ModInitializer {
	public static final String MOD_ID = "morrigan";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        MorriganItems.init();
        MorriganSounds.init();
        MorriganEntities.init();
        MorriganOaths.init();

        MorriganNetworking.registerTypes();

		LOGGER.info("Hello Fabric world!");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static Style applyFormatting(Text text) {
        return TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.LERP_WAVE_EFFECT));
    }
}

/*
 * NOTES
 *
 * A giant screen (accessed with =) that shows you your current Oath, who inflicted it on you (your owner), and the effects.
 * Add sacrificng, killing a player with the Sacrifices Effigy to sign them through.
 */