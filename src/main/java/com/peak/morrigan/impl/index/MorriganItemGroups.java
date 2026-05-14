package com.peak.morrigan.impl.index;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.index.custom.MorriganOaths;
import net.acoyt.acornlib.api.registrants.ItemGroupRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public interface MorriganItemGroups {
    ItemGroupRegistrant GROUPS = new ItemGroupRegistrant(Morrigan.MOD_ID);

    RegistryKey<ItemGroup> MAIN_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Morrigan.id(Morrigan.MOD_ID));
    ItemGroup ITEM_GROUP = GROUPS.register(MAIN_KEY.getValue().getPath(), FabricItemGroup.builder()
            .icon(() -> new ItemStack(MorriganItems.SACRIFICIAL_CLEAVER))
            .displayName(Text.translatable("itemGroup." + Morrigan.MOD_ID).withColor(0xFFac42ff))
            .build());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(MAIN_KEY).register(MorriganItemGroups::buildItemGroup);
    }

    private static void buildItemGroup(FabricItemGroupEntries itemGroup) {
        itemGroup.add(MorriganItems.SACRIFICIAL_CLEAVER);
        for (Oath oath : MorriganOaths.OATHS) {
            ItemStack cleaverVariant = new ItemStack(MorriganItems.SACRIFICIAL_CLEAVER);
            cleaverVariant.set(MorriganDataComponents.STORED_OATH, new StoredOathComponent(oath));

            itemGroup.add(cleaverVariant);
        }

        itemGroup.add(MorriganItems.CELESTIAL_POCKETWATCH);
        itemGroup.add(MorriganItems.DREAMCATCHER);
        itemGroup.add(MorriganItems.ACHERON);
    }
}