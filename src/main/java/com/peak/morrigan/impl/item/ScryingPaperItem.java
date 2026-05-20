package com.peak.morrigan.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.index.MorriganBlocks;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.index.MorriganOaths;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class ScryingPaperItem extends Item implements ColorableItem {
    public ScryingPaperItem(Settings settings) {
        super(settings
                .component(
                        MorriganDataComponents.STORED_OATH,
                        new StoredOathComponent(Oath.EMPTY)
                )
        );
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();

        if (oath.isEmpty()) return;
        tooltip.add(oath.title());
        tooltip.add(
                oath.description().copy().formatted(
                    Formatting.DARK_GRAY,
                    Formatting.ITALIC
                )
        );
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        ItemStack stack = context.getStack();
        StoredOathComponent oath = stack.get(MorriganDataComponents.STORED_OATH);
        Oath storedOath = oath.oath();

        if (context.getWorld().getBlockState(context.getBlockPos()).isOf(MorriganBlocks.RITUAL_TABLE)) {
            var list = MorriganOaths.OATHS;
            var index = list.indexOf(storedOath);

            Oath toApply;
            toApply = (index < list.size() - 1) ? list.get(index + 1) : list.getFirst();

            stack.set(MorriganDataComponents.STORED_OATH, new StoredOathComponent(toApply));
            return ActionResult.FAIL;
        }
        return super.useOnBlock(context);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(Morrigan.applyFormatting(super.getName(stack))).withColor(stack.get(MorriganDataComponents.STORED_OATH).oath().color());
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