package com.peak.morrigan.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.InBoxComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganOaths;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void morrigan$hitAbilities(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (CultistComponent.KEY.get(player).getOath().equals(MorriganOaths.PERSEVERING_WILL)) {
            if (target instanceof LivingEntity living) {
                player.heal(living.getHealth() / 2);
            }
        }
    }

    @WrapMethod(method = "getBlockInteractionRange")
    private double morrigan$removeBlockBreakingWhenInCitadel(Operation<Double> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (InBoxComponent.KEY.get(player).isInBox()) {
            return 0;
        }
        return original.call();
    }

    @Inject(method = "attack", at = @At(value = "TAIL"))
    private void morrigan$naturalFireAspect(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (AshProfileComponent.KEY.get(player).getCurrentProfile().equals(MorriganAshProfiles.PYROCIDE)) {
            if (target instanceof LivingEntity living) {
                living.setOnFireFor(3.0f);
            }
        }
    }
}