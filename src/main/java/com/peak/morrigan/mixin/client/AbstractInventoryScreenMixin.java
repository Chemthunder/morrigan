package com.peak.morrigan.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.morrigan.impl.index.MorriganStatusEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = AbstractInventoryScreen.class)
public abstract class AbstractInventoryScreenMixin {

    @WrapOperation(
            method = "getStatusEffectDescription",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/effect/StatusEffect;getName()Lnet/minecraft/text/Text;"
            )
    )
    private Text morrigan$statusEffectNames(StatusEffect instance, Operation<Text> original) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        
        if (player != null) {
            if (instance.equals(MorriganStatusEffects.RAMPAGE)) {
                return Text.translatable("effect.morrigan.rampage").withColor(0xff0583);
            }
        }
        return original.call(instance);
    }
}
