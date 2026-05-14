package com.peak.morrigan.impl.item;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(
                TextEffectManager.withEffect(
                        super.getName(stack).getStyle(),
                        HibiscusPresetEffects.LERP_WAVE_EFFECT,
                        TextEffectManager.getEffect(HibiscusPresetEffects.LERP_WAVE_EFFECT)
                ).withColor(0xFF9b5815)
        );
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