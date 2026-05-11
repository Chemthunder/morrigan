package com.peak.morrigan.impl.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class ModUtils {

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
}
