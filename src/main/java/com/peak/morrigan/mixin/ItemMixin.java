package com.peak.morrigan.mixin;

import com.peak.morrigan.impl.index.MorriganItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = Item.class)
public abstract class ItemMixin {

    @Inject(method = "useOnBlock", at = @At(value = "HEAD"))
    private void morrigan$paperBecomesScryingPaper(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        if (player == null) return;

        ItemStack stack = player.getStackInHand(context.getHand());

        if (stack.isOf(Items.PAPER)) {
            if (player.isSneaking()) {
                stack.split(1);
                player.giveItemStack(new ItemStack(MorriganItems.SCRYING_PAPER));

                if (player.getWorld().isClient()) {
                    player.swingHand(context.getHand());
                    player.playSoundToPlayer(
                            SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE,
                            SoundCategory.BLOCKS,
                            1,
                            1
                    );
                }
            }
        }
    }
}
