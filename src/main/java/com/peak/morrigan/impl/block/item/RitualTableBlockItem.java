package com.peak.morrigan.impl.block.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.index.MorriganBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public class RitualTableBlockItem extends BlockItem implements ColorableItem {
    public RitualTableBlockItem(Settings settings) {
        super(MorriganBlocks.RITUAL_TABLE, settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(Morrigan.applyFormatting(super.getName(stack))).withColor(0x691818);
    }

    public int startColor(ItemStack itemStack) {
        return 0xFF23232a;
    }

    public int endColor(ItemStack itemStack) {
        return 0xFF29293a;
    }

    public int backgroundColor(ItemStack itemStack) {
        return 0xFF101013;
    }
}
