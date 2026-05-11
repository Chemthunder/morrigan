package com.peak.morrigan.impl.index;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface MorriganOaths {
    List<Oath> OATHS = new ArrayList<>();

    Oath TEST = create("test", Text.empty(), Text.empty());

    static Oath create(String id, Text title, Text desc) {
        Oath gen = new Oath(id, title, desc);
        OATHS.add(gen);
        return gen;
    }

    static void init() {}

    static void registerLang(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        OATHS.forEach(oath -> {
            translationBuilder.add("oath." + Morrigan.MOD_ID + "." + oath.id(), MiscUtils.formatString(oath.id()));
        });
    }
}
