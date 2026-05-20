package com.peak.morrigan.impl.index;

import com.mojang.serialization.Codec;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.component.StoredOathComponent;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

/**
 * @author Chemthunder
 */
public interface MorriganDataComponents {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(Morrigan.MOD_ID);

    ComponentType<StoredOathComponent> STORED_OATH = COMPONENTS.register(
        "stored_oath",
        StoredOathComponent.CODEC,
        StoredOathComponent.PACKET_CODEC
    );

    ComponentType<Boolean> HERETIC = COMPONENTS.register(
        "heretic",
        Codec.BOOL,
        PacketCodecs.BOOL
    );

    static void init() {}
}