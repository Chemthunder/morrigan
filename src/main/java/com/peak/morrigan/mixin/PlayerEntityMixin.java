package com.peak.morrigan.mixin;

import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
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

        if (CultistComponent.KEY.get(player).getOath().equals(MorriganOaths.SYSTEMATIC_MARTYRDOM)) {
            if (AshProfileComponent.KEY.get(player).getCurrentProfile() == MorriganAshProfiles.PYROCIDE) {
                if (target instanceof LivingEntity living) {
                    player.heal(living.getHealth() / 2);
                }
            }
        }
    }
}