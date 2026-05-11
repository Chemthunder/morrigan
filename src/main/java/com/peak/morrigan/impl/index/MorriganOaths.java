package com.peak.morrigan.impl.index;

import com.peak.morrigan.api.Oath;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface MorriganOaths {
    List<Oath> OATHS = new ArrayList<>();

    Oath PRIESTESS_GAZE = create("priestess_gaze", 0xFFac42ff,
            Text.translatable("oath.morrigan.priestess_gaze.title").withColor(0xFFac42ff),
            Text.translatable("oath.morrigan.priestess_gaze.description").formatted(Formatting.DARK_GRAY)
    );

    Oath RETURNING_ROOTS = create("returning_roots", 0xFF67edcd,
            Text.translatable("oath.morrigan.returning_roots.title").withColor(0xFF67edcd),
            Text.translatable("oath.morrigan.returning_roots.description").formatted(Formatting.DARK_GRAY)
    );

    static Oath create(String id, int color, Text title, Text desc) {
        Oath gen = new Oath(id, color, title, desc);
        OATHS.add(gen);
        return gen;
    }

    static void init() {}
}
