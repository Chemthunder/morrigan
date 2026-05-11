package com.peak.morrigan.data.provider.resources.lang;

import com.peak.morrigan.impl.index.MorriganItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class MorriganLangGen extends FabricLanguageProvider {
    public MorriganLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        MorriganItems.ITEMS.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.morrigan", "Morrigan");

        // KEYBINDINGS
        translationBuilder.add("category.morrigan", "Morrigan");
        translationBuilder.add("key.morrigan.open_cultist_display_screen", "Open Cultist Screen");

        translationBuilder.add("morrigan.cult_screen.title", "Church of the Nevermore");

        // OATHS
        translationBuilder.add("oath.morrigan.priestess_gaze.title", "Bind of the Priestess' Gaze");
        translationBuilder.add("oath.morrigan.priestess_gaze.description", "Lay down one's soul to Fesscinenne's will.");

        translationBuilder.add("oath.morrigan.returning_roots.title", "Oath of Returning Roots");
        translationBuilder.add("oath.morrigan.returning_roots.description", "Take back from whence you came.");
    }
}


