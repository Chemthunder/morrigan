package com.peak.morrigan.impl.util;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.client.screen.CultDisplayScreen;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.networking.c2s.TriggerAbilityPayload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class MorriganKeybindings {
    public static KeyBinding openCultistScreen;
    public static KeyBinding triggerAbility;

    public static void register() {
        registerKeyBindings();
        setupPressDetection();
    }

    private static void registerKeyBindings() {
        String category = "category.morrigan";
        openCultistScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.morrigan.open_cultist_display_screen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                category
        ));

        triggerAbility = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.morrigan.trigger_ability",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                category
        ));
    }

    private static void setupPressDetection() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && openCultistScreen.isPressed()) {
                if (CultistComponent.KEY.get(client.player).isCultist()) {
                    handleOpenCultistScreen(client);
                }
            }

            if (client.player != null && triggerAbility.isPressed()) {
                if (CultistComponent.KEY.get(client.player).isCultist()) {
                    handleTriggerAbility(client);
                }
            }
        });
    }

    private static void handleOpenCultistScreen(MinecraftClient client) {
        if (client.player != null) {
            try {
                client.setScreen(new CultDisplayScreen());
            } catch (Exception e) {
                Morrigan.LOGGER.error("Failed to send openCultistScreen Payload");
            }
        }
    }

    private static void handleTriggerAbility(MinecraftClient client) {
        if (client.player != null) {
            try {
                TriggerAbilityPayload.send();
            } catch (Exception e) {
                Morrigan.LOGGER.error("Failed to send triggerAbilityPayload Payload");
            }
        }
    }
}
