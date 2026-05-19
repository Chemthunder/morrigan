package com.peak.morrigan.impl;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.compat.MorriganConfig;
import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.EnchancementDataComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.*;
import com.peak.morrigan.impl.util.ModUtils;
import com.peak.morrigan.impl.util.MorriganKeybindings;
import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Morrigan implements ModInitializer {
    public static final String MOD_ID = "morrigan";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ALib.ModMenuData MENUDATA = new ALib.ModMenuData(
            Text.literal("Morrigan").withColor(0xFFac42ff),
            Text.literal("Nevermorian Decadence."),
            Text.literal("\"Man has no need to fear that which lays in the dark--that which should never be awakened.\"")
    );

    public void onInitialize() {
        ALib.registerModData(MOD_ID, MENUDATA);

        MorriganItems.init();
        MorriganSounds.init();
        MorriganEntities.init();
        MorriganOaths.init();
        MorriganDataComponents.init();
        MorriganItemGroups.init();
        MorriganAshProfiles.init();
        MorriganBlocks.init();
        MorriganBlockEntities.init();
        MorriganStatusEffects.init();
        MorriganParticles.init();

        MorriganNetworking.registerTypes();
        MorriganNetworking.registerC2SPackets();

        MorriganKeybindings.register();

        LOGGER.info("Morrigan has initialized internally!");

        MidnightConfig.init(MOD_ID, MorriganConfig.class);

        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandRegistryAccess, registrationEnvironment) -> {
            commandDispatcher.register(CommandManager.literal("morrigan")
                    .then(CommandManager.literal("clear").executes(context -> {
                        PlayerEntity player = context.getSource().getPlayerOrThrow();
                        CultistComponent cultistComponent = CultistComponent.KEY.get(player);

                        cultistComponent.setLeader("");
                        cultistComponent.setCultist(false);
                        cultistComponent.swearOath(Oath.EMPTY);

                        return Command.SINGLE_SUCCESS;
                    }))

                    .then(CommandManager.literal("enchancement").executes(context -> {
                        PlayerEntity player = context.getSource().getPlayerOrThrow();
                        EnchancementDataComponent data = EnchancementDataComponent.KEY.get(player);

                        data.setMovementRemovedTicks(50);

                        return Command.SINGLE_SUCCESS;
                    }))

                    .then(CommandManager.literal("getProfile").executes(context -> {
                        PlayerEntity player = context.getSource().getPlayerOrThrow();
                        AshProfileComponent data = AshProfileComponent.KEY.get(player);

                        context.getSource().sendFeedback(() -> Text.literal("current profile: " + data.getCurrentProfile().id()), false);

                        return Command.SINGLE_SUCCESS;
                    }))

                    .then(CommandManager.literal("hereticStatus")
                            .then(CommandManager.argument("state", BoolArgumentType.bool()).executes(context -> {
                                boolean toApply = BoolArgumentType.getBool(context, "state");

                                ModUtils.getCultistInstance(context.getSource().getPlayerOrThrow()).setHeretic(toApply);
                                return Command.SINGLE_SUCCESS;
                            }))
                            .executes(context -> {
                                context.getSource().sendFeedback(() -> Text.literal("Heretic State: " + ModUtils.getCultistInstance(context.getSource().getPlayer()).isHeretic()), true);
                                return Command.SINGLE_SUCCESS;
                            })
                    )
            );
        }); // TODO: move morrigan debug to its own class
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static Style applyFormatting(Text text) {
        return MorriganConfig.wavyText ? TextEffectManager.withEffect(text.getStyle(), HibiscusPresetEffects.LERP_WAVE_EFFECT, TextEffectManager.getEffect(HibiscusPresetEffects.LERP_WAVE_EFFECT)) : text.getStyle();
    }

    /**
     * Checks if an entity is Chemthunder.
     * @param entity The entity to check.
     */
    public static boolean isChem(Entity entity) {
        return entity != null && (entity.getUuidAsString().equals("a26e29f1-532e-4116-9112-ca18ea30d27f"));
    }
}

/**
 * DREAMCATCHER
 * Gives an effect that forcefully deafens people and removes their ability to see others.
 */