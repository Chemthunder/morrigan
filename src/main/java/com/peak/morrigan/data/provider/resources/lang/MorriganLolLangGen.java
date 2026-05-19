package com.peak.morrigan.data.provider.resources.lang;

import com.peak.morrigan.api.AshProfile;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.index.tag.MorriganEntityTypeTags;
import com.peak.morrigan.impl.index.tag.MorriganItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class MorriganLolLangGen extends FabricLanguageProvider {
    public MorriganLolLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "lol_us", registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        /* Items */
        translationBuilder.add("item.morrigan.sacrificial_cleaver", "befoul ahh");
        translationBuilder.add("item.morrigan.scrying_paper", "magik papa");
        translationBuilder.add("item.morrigan.dreamcatcher", "a miwwion dweams i hav had");
        translationBuilder.add("item.morrigan.acheron", "scawy stik");

        /* Blocks */
        translationBuilder.add("block.morrigan.ritual_table", "majik table");
        translationBuilder.add("block.morrigan.nevermorian_citadel", "jewel of da nevamore");

        /* Effects */
        translationBuilder.add("effect.morrigan.rampage", "angy >:0");
        translationBuilder.add("effect.morrigan.fearful", "scawed >n<");

        /* Tags */
        MorriganItemTags.TAG.registerLang(wrapperLookup, translationBuilder);
        MorriganEntityTypeTags.TAG.registerLang(wrapperLookup, translationBuilder);

        translationBuilder.add("itemGroup.morrigan", "crow");

        // KEYBINDINGS
        translationBuilder.add("category.morrigan", "crow");
        translationBuilder.add("key.morrigan.open_cultist_display_screen", "see ur cult");
        translationBuilder.add("key.morrigan.trigger_ability", "do tings");

        translationBuilder.add("morrigan.cult_screen.title", "<o>");
        translationBuilder.add("morrigan.cult_screen.bound", "owned by %s");
        translationBuilder.add("morrigan.cult_screen.profile", "current ver: %s");
        translationBuilder.add("morrigan.cult_screen.heretic", "~ kill da baddos! ~");

        // OATHS
        registerOath(translationBuilder, MorriganOaths.PRIESTESS_GAZE,
                "eternity of misery",
                "give it up for one last chance"
        );

        registerOath(translationBuilder, MorriganOaths.RETURNING_ROOTS,
                "oat of no enchancement",
                "when u can't adapt, fight back!"
        );

        registerOath(translationBuilder, MorriganOaths.PERSEVERING_WILL,
                "oat of mira",
                "hi mira!!!!"
        );

        registerOath(translationBuilder, MorriganOaths.SYSTEMATIC_MARTYRDOM,
                "oat of plurality",
                "das a lot of people"
        );

        registerOath(translationBuilder, MorriganOaths.SHARPENED_STAR,
                "oat of gun",
                "pew pew!!!"
        );

        registerOath(translationBuilder, MorriganOaths.LIGHT_DARK,
                "oat of eclapse",
                "startgazing"
        );

        // ASHPROFILE
        registerAshProfile(translationBuilder, MorriganAshProfiles.CRYOCIDE,
                "im cold >:<"
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.FLORACIDE,
                "motha natue"
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.PYROCIDE,
                "is very hawt in her"
        );

        registerAshProfile(translationBuilder, MorriganAshProfiles.AEROCIDE,
                "very fast"
        );

        // CONFIG
        translationBuilder.add("morrigan.midnightconfig.title", "crow");
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
