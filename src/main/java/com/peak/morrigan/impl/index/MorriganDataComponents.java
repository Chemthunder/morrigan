package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;

/**
 * @author Chemthunder
 */
public interface MorriganDataComponents {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Morrigan.MOD_ID);

    

    static void init() {}
}
