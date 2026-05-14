package com.peak.morrigan.impl.item;

import com.everest.hibiscus.api.modules.rendering.text.HibiscusPresetEffects;
import com.everest.hibiscus.api.modules.rendering.text.registry.TextEffectManager;
import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.util.ModUtils;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class SacrificialCleaverItem extends Item implements ModelVaryingItem, ColorableItem, CustomHitParticleItem {
    public SacrificialCleaverItem(Settings settings) {
        super(settings
                .component(
                        MorriganDataComponents.STORED_OATH,
                        new StoredOathComponent(Oath.EMPTY)
                ));
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 7.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.6f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(Identifier.ofVanilla("base_entity_interaction_range"), 0.5f, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();

        if (!oath.isEmpty()) {
            tooltip.add(oath.title());
            tooltip.add(oath.description().copy().formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    public void getKillEffect(PlayerEntity player, PlayerEntity target, World world, ItemStack stack) {
        Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();

        if (oath.isEmpty()) return;

        if (!ModUtils.isNaked(target)) return;

        CultistComponent component = CultistComponent.KEY.get(target);
        component.setCultist(true);
        component.setLeader(player.getNameForScoreboard());

        component.swearOath(oath);

        target.sendMessage(Text.literal("You have been sworn under " + component.getLeader() + " in the " + component.getOath().title().getString()).formatted(Formatting.ITALIC, Formatting.YELLOW), true);
        player.sendMessage(Text.literal("You have been sworn under " + component.getLeader() + " in the " + component.getOath().title().getString()).formatted(Formatting.ITALIC, Formatting.YELLOW), true);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        CultistComponent cultistComponent = CultistComponent.KEY.get(user);
        Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();

        if (user.isSneaking()) {
            if (!oath.isEmpty()) {
                if (!cultistComponent.isCultist()) {
                    cultistComponent.setCultist(true);
                    cultistComponent.setLeader(user.getNameForScoreboard());
                    cultistComponent.swearOath(oath);
                    
                    ParticleUtils.spawnSweepParticles(new SweepParticleEffect(0xFF1c1c2a, oath.color()), user);

                    if (world.isClient()) {
                        user.swingHand(hand);
                    }
                }
            }
        }

        return super.use(world, user, hand);
    }

    public Identifier getModel(ModelTransformationMode modelTransformationMode, ItemStack itemStack, @Nullable LivingEntity livingEntity) {
        return MiscUtils.isGui(modelTransformationMode) ? Morrigan.id("sacrificial_cleaver") : Morrigan.id("sacrificial_cleaver_handheld");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Morrigan.id("sacrificial_cleaver"),
                Morrigan.id("sacrificial_cleaver_handheld")
        );
    }

    public void spawnHitParticles(PlayerEntity player, Entity target) {
        ItemStack stack = player.getMainHandStack();
        Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();
        ParticleUtils.spawnSweepParticles(new SweepParticleEffect(0xFF1c1c2a, oath.color()), player);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(
                TextEffectManager.withEffect(
                        super.getName(stack).getStyle(),
                        HibiscusPresetEffects.LERP_WAVE_EFFECT,
                        TextEffectManager.getEffect(HibiscusPresetEffects.LERP_WAVE_EFFECT)
                ).withColor(stack.get(MorriganDataComponents.STORED_OATH).oath().color())
        );
    }

    public int startColor(ItemStack itemStack) {
        return itemStack.get(MorriganDataComponents.STORED_OATH).oath().isEmpty() ? 0xFF23232a : itemStack.get(MorriganDataComponents.STORED_OATH).oath().color();
    }

    public int endColor(ItemStack itemStack) {
        return 0xFF29293a;
    }

    public int backgroundColor(ItemStack itemStack) {
        return 0xFF101013;
    }
}