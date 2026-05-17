package com.peak.morrigan.impl.util;

import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @author Chemthunder
 */
public class ModUtils {
    /**
     * Checks if an entity is naked.
     * @param living The entity to check.
     */
    public static boolean isNaked(LivingEntity living) {
        if (
                living.getEquippedStack(EquipmentSlot.HEAD).isEmpty() &&
                        living.getEquippedStack(EquipmentSlot.CHEST).isEmpty() &&
                        living.getEquippedStack(EquipmentSlot.LEGS).isEmpty() &&
                        living.getEquippedStack(EquipmentSlot.FEET).isEmpty()
        ) {
            return true;
        }
        return false;
    }

    public static CultistComponent getCultistInstance(Entity entity) {
        if (entity instanceof PlayerEntity player) {
            return CultistComponent.KEY.get(player);
        } else {
            return null;
        }
    }
}