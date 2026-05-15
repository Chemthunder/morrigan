package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.block.item.NevermorianCitadelBlockItem;
import com.peak.morrigan.impl.item.AcheronItem;
import com.peak.morrigan.impl.item.DreamcatcherItem;
import com.peak.morrigan.impl.item.SacrificialCleaverItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;

/**
 * @author Chemthunder
 */
public interface MorriganItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Morrigan.MOD_ID);

    Item SACRIFICIAL_CLEAVER = ITEMS.register("sacrificial_cleaver", SacrificialCleaverItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SacrificialCleaverItem.createAttributeModifiers())
    );

    Item DREAMCATCHER = ITEMS.register("dreamcatcher", DreamcatcherItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item ACHERON = ITEMS.register("acheron", AcheronItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(AcheronItem.createAttributeModifiers())
    );

    Item CITADEL_ITEM = ITEMS.register("nevermorian_citadel", NevermorianCitadelBlockItem::new, new Item.Settings()
            .maxCount(1)
    );

    static void init() {}
}