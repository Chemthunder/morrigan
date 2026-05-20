package com.peak.morrigan.data.provider.resources.lang;

import com.peak.morrigan.api.AshProfile;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganItems;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.index.MorriganStatusEffects;
import com.peak.morrigan.impl.index.tag.MorriganEntityTypeTags;
import com.peak.morrigan.impl.index.tag.MorriganItemTags;
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
        /* Registrants */
        MorriganItems.ITEMS.registerLang(wrapperLookup, translationBuilder);
        MorriganStatusEffects.STATUS_EFFECTS.registerLang(wrapperLookup, translationBuilder);

        /* Tags */
        MorriganItemTags.TAG.registerLang(wrapperLookup, translationBuilder);
        MorriganEntityTypeTags.TAG.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.morrigan", "Morrigan");

        // KEYBINDINGS
        translationBuilder.add("category.morrigan", "Morrigan");
        translationBuilder.add("key.morrigan.open_cultist_display_screen", "Open Cultist Screen");
        translationBuilder.add("key.morrigan.trigger_ability", "Use Oath Ability");

        translationBuilder.add("morrigan.cult_screen.title", "Church of the Nevermore");
        translationBuilder.add("morrigan.cult_screen.bound", "Bound under %s");
        translationBuilder.add("morrigan.cult_screen.profile", "Current Profile: %s");
        translationBuilder.add("morrigan.cult_screen.heretic", "~ SILENCE THE HERETICS ~");

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

        registerOath(translationBuilder, MorriganOaths.SYSTEMATIC_MARTYRDOM,
                "Oath of Systematic Martyrdom",
                "One among many; to live is to be more."
        );

        registerOath(translationBuilder, MorriganOaths.SHARPENED_STAR,
                "Oath of the Sharpened Star",
                "Fire when ready, soldier."
        );

        registerOath(translationBuilder, MorriganOaths.LIGHT_DARK,
                "Oath of Light and Dark",
                "Through light and dark, we shine forward."
        );

        translationBuilder.add("oath.morrigan.returning_roots.heads_up", "Emitting : %s");

        // ASHPROFILE
        registerAshProfile(translationBuilder, MorriganAshProfiles.CRYOCIDE,
                "Cold Feet?"
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.FLORACIDE,
                "You hear the grass whisper sweet nothings."
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.PYROCIDE,
                "LET'S GET MOVING!"
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.AEROCIDE,
                "Gotta go fast!"
        );

        // CONFIG
        translationBuilder.add("morrigan.midnightconfig.title", "Morrigan");
        translationBuilder.add("morrigan.midnightconfig.wavyText", "Wavy Text");
        translationBuilder.add("morrigan.midnightconfig.indicatorY", "Y-Position of Keybind Indicator");
    }

    public static void registerOath(TranslationBuilder translationBuilder, Oath oath, String title, String description) {
        translationBuilder.add("oath.morrigan." + oath.id() + ".title", title);
        translationBuilder.add("oath.morrigan." + oath.id() + ".description", description);
    }

    public static void registerAshProfile(TranslationBuilder translationBuilder, AshProfile profile, String description) {
        translationBuilder.add("ash_profile.morrigan." + profile.id(), description);
    }
}