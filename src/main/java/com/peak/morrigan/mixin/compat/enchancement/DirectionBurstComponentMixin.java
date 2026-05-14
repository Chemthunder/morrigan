package com.peak.morrigan.mixin.compat.enchancement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.morrigan.impl.cca.entity.EnchancementDataComponent;
import moriyashiine.enchancement.common.component.entity.DirectionBurstComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Chemthunder
 */
@Mixin(value = DirectionBurstComponent.class)
public abstract class DirectionBurstComponentMixin {
    @Shadow @Final private PlayerEntity obj;

    @WrapMethod(method = "use")
    private void morrigan$denyStrafe(double velocityX, double velocityZ, Operation<Void> original) {
        PlayerEntity player = this.obj;

        if (EnchancementDataComponent.KEY.get(player).getMovementRemovedTicks() <= 0) {
            original.call(velocityX, velocityZ);
        }
    }
}