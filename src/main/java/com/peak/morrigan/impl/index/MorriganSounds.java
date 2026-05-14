package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.minecraft.sound.SoundEvent;

/**
 * @author Chemthunder
 */
public interface MorriganSounds {
    SoundEventRegistrant SOUNDS = new SoundEventRegistrant(Morrigan.MOD_ID);

    SoundEvent POCKETWATCH_TICK = SOUNDS.register("pocketwatch_tick");

    static void init() {}
}