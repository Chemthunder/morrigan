package com.peak.morrigan.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.peak.morrigan.impl.cca.entity.AshProfileComponent;
import com.peak.morrigan.impl.cca.entity.LockMovementComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganAshProfiles;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.index.MorriganOaths;
import com.peak.morrigan.impl.item.SacrificialCleaverItem;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    @Shadow @Nullable public abstract LivingEntity getAttacker();

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

    @WrapOperation(
            method = "dropLoot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/loot/LootTable;generateLoot(Lnet/minecraft/loot/context/LootContextParameterSet;JLjava/util/function/Consumer;)V"
            )
    )
    private void morrigan$naturalishLooting(LootTable instance, LootContextParameterSet parameters, long seed, Consumer<ItemStack> lootConsumer, Operation<Void> original) {
        LivingEntity livingEntity = this.getAttacker();

        if (livingEntity != null) {
            if (livingEntity instanceof PlayerEntity player) {
                if (AshProfileComponent.KEY.get(player).getCurrentProfile().equals(MorriganAshProfiles.AEROCIDE)) {
                    for (int i = 0; i < 2; i++) {
                        instance.generateLoot(parameters, seed, this::dropStack);
                    }
                } else {
                    original.call(instance, parameters, seed, lootConsumer);
                }
            }
        }
    }

    @WrapMethod(method = "applyMovementInput")
    private Vec3d morrigan$slipperiness(Vec3d movementInput, float slipperiness, Operation<Vec3d> original) {
        if ((Object) this instanceof PlayerEntity player) {
            if (AshProfileComponent.KEY.get(player).getCurrentProfile().equals(MorriganAshProfiles.CRYOCIDE)) {
                return original.call(movementInput, slipperiness / 2);
            }
        }
        return original.call(movementInput, slipperiness);
    }

    @WrapMethod(method = "applyMovementInput")
    private Vec3d morrigan$lockMovement(Vec3d movementInput, float slipperiness, Operation<Vec3d> original) {
        if (LockMovementComponent.KEY.get(this).getTicks() > 0) {
            return Vec3d.ZERO;
        }
        return original.call(movementInput, slipperiness);
    }

    @WrapMethod(method = "applyFluidMovingSpeed")
    private Vec3d morrigan$lockFluidSpeed(double gravity, boolean falling, Vec3d motion, Operation<Vec3d> original) {
        if (LockMovementComponent.KEY.get(this).getTicks() > 0) {
            return Vec3d.ZERO;
        }
        return original.call(gravity, falling, motion);
    }
}