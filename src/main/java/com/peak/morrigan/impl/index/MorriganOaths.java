package com.peak.morrigan.impl.index;

import com.peak.morrigan.api.Oath;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface MorriganOaths {
    List<Oath> OATHS = new ArrayList<>();
    List<Oath> HAS_ABILITY = new ArrayList<>();

    Oath PRIESTESS_GAZE = create("priestess_gaze", 0xFFac42ff);
    Oath RETURNING_ROOTS = create("returning_roots", 0xFF67edcd);
    Oath PERSEVERING_WILL = create("persevering_will", 0xFFfdf698);
    Oath BEATING_CYST = create("beating_cyst", 0xFFc95858);

    static Oath create(String id, int color) {
        Oath gen = new Oath(id, color, Text.translatable("oath.morrigan." + id + ".title").withColor(color), Text.translatable("oath.morrigan." + id + ".description"));
        OATHS.add(gen);
        return gen;
    }

    static void init() {
        HAS_ABILITY.add(RETURNING_ROOTS);
        HAS_ABILITY.add(BEATING_CYST);
    }
}