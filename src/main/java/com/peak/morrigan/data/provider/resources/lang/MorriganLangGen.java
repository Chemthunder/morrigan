package com.peak.morrigan.data.provider.resources.lang;

import com.peak.morrigan.api.Oath;
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

        translationBuilder.add("itemGroup.morrigan", "Morrigan");

        // KEYBINDINGS
        translationBuilder.add("category.morrigan", "Morrigan");
        translationBuilder.add("key.morrigan.open_cultist_display_screen", "Open Cultist Screen");

        translationBuilder.add("morrigan.cult_screen.title", "Church of the Nevermore");

        // OATHS
        registerOath(translationBuilder, MorriganOaths.PRIESTESS_GAZE,
                "Bind of the Priesstess's Gaze",
                "Lay down one's soul to Fesscinenne's will."
        );

        registerOath(translationBuilder, MorriganOaths.RETURNING_ROOTS,
                "Oath of Returning Roots",
                "Take back from whence you came."
        );

        registerOath(translationBuilder, MorriganOaths.PERSEVERING_WILL,
                "Oath of Persevering Will",
                "Through suffering we are born stronger."
        );

        registerOath(translationBuilder, MorriganOaths.BEATING_CYST,
                "Oath of the Beating Cyst",
                "One among many; to live is to be more."
        );
    }

    public static void registerOath(TranslationBuilder translationBuilder, Oath oath, String title, String description) {
        translationBuilder.add("oath.morrigan." + oath.id() + ".title", title);
        translationBuilder.add("oath.morrigan." + oath.id() + ".description", description);
    }
}


