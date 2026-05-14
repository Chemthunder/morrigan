package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.component.StoredOathComponent;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;

/**
 * @author Chemthunder
 */
public interface MorriganDataComponents {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Morrigan.MOD_ID);

    ComponentType<StoredOathComponent> STORED_OATH = COMPONENTS.register("stored_oath", StoredOathComponent.CODEC, StoredOathComponent.PACKET_CODEC);

    static void init() {}
}