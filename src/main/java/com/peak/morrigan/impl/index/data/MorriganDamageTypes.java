package com.peak.morrigan.impl.index.data;

import com.peak.morrigan.impl.Morrigan;
import com.peak.omnia.api.registration.core.DamageTypeRegistry;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;

/**
 * @author Chemthunder
 */
public interface MorriganDamageTypes {
    DamageTypeRegistry DATA = new DamageTypeRegistry(Morrigan.MOD_ID);

    RegistryKey<DamageType> EXTOL = DATA.register("extol", 0.0f);
    RegistryKey<DamageType> BESEECH = DATA.register("beseech", 0.0f);
}