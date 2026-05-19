package com.peak.morrigan.impl.index.tag;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author Chemthunder
 */
public interface MorriganItemTags {
    TagBuilder<Item> TAG = new TagBuilder<>(Morrigan.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> CEREMONIAL_SCROLLS = TAG.register("ceremonial_scrolls");
}
