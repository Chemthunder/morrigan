package com.peak.morrigan.mixin.compat.enchancement;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.peak.morrigan.impl.cca.entity.roots.RootsVictimComponent;
import moriyashiine.enchancement.common.component.entity.AirJumpComponent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Chemthunder
 */
@Mixin(value = AirJumpComponent.class)
public abstract class AirJumpComponentMixin {
    @Shadow @Final private PlayerEntity obj;

    @WrapMethod(method = "use")
    private void morrigan$denyGale(Operation<Void> original) {
        PlayerEntity player = this.obj;

        if (RootsVictimComponent.KEY.get(player).getMovementRemovedTicks() <= 0) {
            original.call();
        }
    }
}