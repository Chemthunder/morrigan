package com.peak.morrigan.data.provider.resources;

import com.peak.morrigan.impl.index.MorriganItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

/**
 * @author Chemthunder
 */
public class MorriganModelGen extends FabricModelProvider {
    public MorriganModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(MorriganItems.CELESTIAL_POCKETWATCH, Models.GENERATED);
        itemModelGenerator.register(MorriganItems.SACRIFICES_EFFIGY, Models.GENERATED);
        itemModelGenerator.register(MorriganItems.DREAMCATCHER, Models.GENERATED);
    }
}
