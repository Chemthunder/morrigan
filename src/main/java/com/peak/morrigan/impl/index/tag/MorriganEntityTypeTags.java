package com.peak.morrigan.impl.index.tag;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author Chemthunder
 */
public interface MorriganEntityTypeTags {
    TagBuilder<EntityType<?>> TAG = new TagBuilder<>(Morrigan.MOD_ID, RegistryKeys.ENTITY_TYPE);

    TagKey<EntityType<?>> IGNORED_BY_CLEAR = TAG.register("ignored_by_clear");
}