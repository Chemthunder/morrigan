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
public class NevermorianCitadelBlockItem extends BlockItem implements ColorableItem {
    public NevermorianCitadelBlockItem(Settings settings) {
        super(MorriganBlocks.NEVERMORIAN_CITADEL, settings);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(Morrigan.applyFormatting(super.getName(stack))).withColor(0xac42ff);
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