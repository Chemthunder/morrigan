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
    Oath SYSTEMATIC_MARTYRDOM = create("systematic_martyrdom", 0xFFc95858);
    Oath SHARPENED_STAR = create("sharpened_star", 0xFFbaa15d);
    Oath LIGHT_DARK = create("light_dark", 0xFF2d3034);

    static Oath create(String id, int color) {
        Oath gen = new Oath(
                id, color,
                Text.translatable("oath.morrigan." + id + ".title").withColor(color),
                Text.translatable("oath.morrigan." + id + ".description")
        );

        OATHS.add(gen);
        return gen;
    }

    static void init() {
        HAS_ABILITY.add(RETURNING_ROOTS);
        HAS_ABILITY.add(SYSTEMATIC_MARTYRDOM);
        HAS_ABILITY.add(SHARPENED_STAR);
        HAS_ABILITY.add(LIGHT_DARK);
    }
}