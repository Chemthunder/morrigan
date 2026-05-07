package com.peak.morrigan.data;

import com.peak.morrigan.data.provider.MorriganDynamicRegistriesGen;
import com.peak.morrigan.data.provider.resources.MorriganModelGen;
import com.peak.morrigan.data.provider.resources.lang.MorriganLangGen;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.data.MorriganDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class MorriganDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MorriganDynamicRegistriesGen::new);

        pack.addProvider(MorriganModelGen::new);
        pack.addProvider(MorriganLangGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, MorriganDamageTypes::bootstrap);
    }
}
