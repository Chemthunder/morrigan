package com.peak.morrigan.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.item.SacrificialCleaverItem;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryUseTotem", at = @At(value = "HEAD"), cancellable = true)
    private void morrigan$cleaver(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (living instanceof PlayerEntity target) {

            Entity entity = source.getAttacker();
            if (entity == null) return;

            if (entity instanceof PlayerEntity player) {
                if (player.getMainHandStack().getItem() instanceof SacrificialCleaverItem cleaverItem) {
                    if (!player.getMainHandStack().get(MorriganDataComponents.STORED_OATH).oath().isEmpty()) {
                        cleaverItem.getKillEffect(player, target, player.getWorld(), player.getMainHandStack());
                        target.setHealth(target.getMaxHealth());
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }

    @WrapOperation(
            method = "baseTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
                    ordinal = 2
            )
    )
    private boolean morrigan$preventDrowning(LivingEntity instance, DamageSource source, float amount, Operation<Boolean> original) {
        if (instance instanceof PlayerEntity player) {
            if (CultistComponent.KEY.get(player).getOath().equals(MorriganOaths.PERSEVERING_WILL)) {
                return original.call(instance, source, 0.0f);
            }
        }

        return original.call(instance, source, amount);
    }
}