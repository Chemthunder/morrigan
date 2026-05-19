package com.peak.morrigan.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.world.CultDataComponent;
import com.peak.morrigan.impl.util.ModUtils;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class DreamcatcherItem extends Item implements ColorableItem, ModelVaryingItem {
    public DreamcatcherItem(Settings settings) {
        super(settings);
    }

    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return MiscUtils.isGui(renderMode) ? Morrigan.id("dreamcatcher") : Morrigan.id("dreamcatcher_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Morrigan.id("dreamcatcher"),
                Morrigan.id("dreamcatcher_handheld")
        );
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            if (target instanceof PlayerEntity) {
                World world = player.getWorld();

                CultDataComponent cult = CultDataComponent.KEY.get(world);

                if (ModUtils.getCultistInstance(target).isCultist()) {
                    cult.createHeretic(target.getNameForScoreboard(), target.getUuid());
                    ModUtils.getCultistInstance(target).setHeretic(true);
                }
            }
        }

        return super.postHit(stack, target, attacker);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(Morrigan.applyFormatting(super.getName(stack))).withColor(0xFF9b5815);
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