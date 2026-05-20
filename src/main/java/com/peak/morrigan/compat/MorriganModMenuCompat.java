package com.peak.morrigan.compat;

import com.peak.morrigan.impl.Morrigan;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;

/**
 * @author Chemthunder
 */
public class MorriganModMenuCompat implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, Morrigan.MOD_ID);
    }
}