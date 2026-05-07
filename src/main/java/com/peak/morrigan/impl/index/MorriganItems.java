package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.item.CelestialPocketwatchItem;
import com.peak.morrigan.impl.item.DreamcatcherItem;
import com.peak.morrigan.impl.item.SacrificesEffigyItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface MorriganItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Morrigan.MOD_ID);

    Item SACRIFICES_EFFIGY = ITEMS.register("sacrifices_effigy", SacrificesEffigyItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SacrificesEffigyItem.createAttributeModifiers())
    );

    Item DREAMCATCHER = ITEMS.register("dreamcatcher", DreamcatcherItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item CELESTIAL_POCKETWATCH = ITEMS.register("celestial_pocketwatch", CelestialPocketwatchItem::new, new Item.Settings()
            .maxCount(1)
    );

    static void init() {}
}
