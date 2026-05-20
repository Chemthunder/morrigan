package com.peak.morrigan.data;

import com.peak.morrigan.data.provider.resources.MorriganModelGen;
import com.peak.morrigan.data.provider.resources.MorriganParticleGen;
import com.peak.morrigan.data.provider.resources.lang.MorriganLangGen;
import com.peak.morrigan.data.provider.resources.lang.MorriganLolLangGen;
import com.peak.morrigan.data.provider.tag.MorriganEntityTagGen;
import com.peak.morrigan.data.provider.tag.MorriganItemTagGen;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.data.MorriganDamageTypes;
import com.peak.omnia.api.registration.DataInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryWrapper;

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

        /* Resources */
        pack.addProvider(MorriganLangGen::new);
        pack.addProvider(MorriganLolLangGen::new);

        pack.addProvider(MorriganModelGen::new);
        pack.addProvider(MorriganParticleGen::new);

        /* Tags */
        pack.addProvider(MorriganItemTagGen::new);
        pack.addProvider(MorriganEntityTagGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        PRIMARY.buildRegistries(registryBuilder);
    }

    public static final class DynamicRegistries extends FabricDynamicRegistryProvider {
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