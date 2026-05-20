package com.peak.morrigan.data.provider.tag;

import com.peak.morrigan.impl.index.tag.MorriganEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class MorriganEntityTagGen extends FabricTagProvider.EntityTypeTagProvider {
    public MorriganEntityTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(MorriganEntityTypeTags.IGNORED_BY_CLEAR)
                .add(
                        EntityType.ARMOR_STAND,
                        EntityType.ITEM,
                        EntityType.ITEM_FRAME,
                        EntityType.GLOW_ITEM_FRAME,
                        EntityType.BLOCK_DISPLAY,
                        EntityType.ITEM_DISPLAY,
                        EntityType.PLAYER
                )
                .setReplace(false);
    }
}