package com.peak.morrigan.data.provider.tag;

import com.peak.morrigan.impl.index.tag.MorriganItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class MorriganItemTagGen extends FabricTagProvider.ItemTagProvider {
    public MorriganItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(MorriganItemTags.CEREMONIAL_SCROLLS)
                .add(Items.PAPER)
                .setReplace(false);
    }
}