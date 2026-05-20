package com.peak.morrigan.impl.index;

import com.peak.morrigan.api.AshProfile;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface MorriganAshProfiles {
    List<AshProfile> PROFILES = new ArrayList<>();

    AshProfile CRYOCIDE = create("cryocide", 0xFF6dded4, false);
    AshProfile FLORACIDE = create("floracide", 0xFF80bd64, true);
    AshProfile PYROCIDE = create("pyrocide", 0xFFff5c5c, true);
    AshProfile AEROCIDE = create("aerocide", 0xFFe1e9fc, true);

    private static AshProfile create(String id, int color, boolean hasAbility) {
        AshProfile ashProfile = new AshProfile(id, color, hasAbility, Text.translatable("ash_profile.morrigan." + id));
        PROFILES.add(ashProfile);
        return ashProfile;
    }

    static void init() {}
}