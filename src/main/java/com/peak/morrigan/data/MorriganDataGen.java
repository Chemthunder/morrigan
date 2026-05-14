package com.peak.morrigan.data;

import com.peak.morrigan.data.provider.resources.MorriganModelGen;
import com.peak.morrigan.data.provider.resources.lang.MorriganLangGen;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.data.MorriganDamageTypes;
import com.peak.omnia.api.registration.DataInitializer;
import com.peak.omnia.impl.Omnia;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class MorriganDataGen implements DataGeneratorEntrypoint {
    public static final DataInitializer PRIMARY = new DataInitializer(Morrigan.MOD_ID, List.of(
            MorriganDamageTypes.DATA
    ));

	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(DynamicRegistries::new);

        pack.addProvider(MorriganModelGen::new);
        pack.addProvider(MorriganLangGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        PRIMARY.buildRegistries(registryBuilder);
    }

    public static class DynamicRegistries extends FabricDynamicRegistryProvider {
        public DynamicRegistries(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
            PRIMARY.loadConfigurations(wrapperLookup, entries);
        }

        public String getName() {
            return "Morrigan Dynamic Registries";
        }
    }
}