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

    Oath PRIESTESS_GAZE = create("priestess_gaze", 0xFFac42ff,
            Text.translatable("oath.morrigan.priestess_gaze.title").withColor(0xFFac42ff),
            Text.translatable("oath.morrigan.priestess_gaze.description")
    );

    Oath RETURNING_ROOTS = create("returning_roots", 0xFF67edcd,
            Text.translatable("oath.morrigan.returning_roots.title").withColor(0xFF67edcd),
            Text.translatable("oath.morrigan.returning_roots.description")
    );

    Oath PERSEVERING_WILL = create("persevering_will", 0xFFfdf698,
            Text.translatable("oath.morrigan.persevering_will.title").withColor(0xFFfdf698),
            Text.translatable("oath.morrigan.persevering_will.description")
    );

    static Oath create(String id, int color, Text title, Text desc) {
        Oath gen = new Oath(id, color, title, desc);
        OATHS.add(gen);
        return gen;
    }

    static void init() {
        HAS_ABILITY.add(RETURNING_ROOTS);
    }
}
