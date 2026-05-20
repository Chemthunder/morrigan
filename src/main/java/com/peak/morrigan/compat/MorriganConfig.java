package com.peak.morrigan.compat;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author Chemthunder
 */
public class MorriganConfig extends MidnightConfig {
    @Environment(EnvType.CLIENT)
    @Entry public static boolean wavyText = true;

    @Environment(EnvType.CLIENT)
    @Entry(isSlider = true, min = -90, max = 90)
    public static int indicatorY = 20;
}