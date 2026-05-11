package com.peak.morrigan.data.provider.resources.lang;

import com.peak.morrigan.impl.index.MorriganItems;
import com.peak.morrigan.impl.index.MorriganOaths;
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
        MorriganOaths.registerLang(translationBuilder);
    }
}


