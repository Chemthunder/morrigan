package com.peak.morrigan.impl.index.data;

import com.peak.morrigan.impl.Morrigan;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface MorriganDamageTypes {
    List<DamageSourceData> DATA = new ArrayList<>();

    RegistryKey<DamageType> EXTOL = register("extol", 0.0f);

    private static RegistryKey<DamageType> register(String name, float exhaustion) {
        RegistryKey<DamageType> key = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Morrigan.id(name));
        DamageSourceData data = new DamageSourceData(key, name, exhaustion);

        DATA.add(data);
        return key;
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        DATA.forEach(damageSourceData -> registerable.register(damageSourceData.key, new DamageType(damageSourceData.name, damageSourceData.exhaustion)));
    }

    record DamageSourceData(RegistryKey<DamageType> key, String name, float exhaustion) {}
}
