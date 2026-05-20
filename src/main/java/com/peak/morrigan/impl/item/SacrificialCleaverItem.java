package com.peak.morrigan.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.client.particle.ShockwaveParticleEffect;
import com.peak.morrigan.impl.component.StoredOathComponent;
import com.peak.morrigan.impl.index.MorriganBlocks;
import com.peak.morrigan.impl.index.MorriganDataComponents;
import com.peak.morrigan.impl.index.data.MorriganDamageTypes;
import com.peak.morrigan.impl.util.ModUtils;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
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
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Chemthunder
 */
public class SacrificialCleaverItem extends Item implements ModelVaryingItem, ColorableItem, CustomHitParticleItem, CustomKillSourceItem {
    public SacrificialCleaverItem(Settings settings) {
        super(settings
                .component(
                        MorriganDataComponents.STORED_OATH,
                        new StoredOathComponent(Oath.EMPTY)
                )
                .component(
                        MorriganDataComponents.HERETIC,
                        false
                )
        );
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
        if (Morrigan.isChem(player)) {
            Oath oath = stack.get(MorriganDataComponents.STORED_OATH).oath();

            MinecraftServer server = world.getServer();
            if (server == null) return;

            if (oath.isEmpty()) return;
            if (!ModUtils.isNaked(target)) return;

            CultistComponent component = CultistComponent.KEY.get(target);
            component.setCultist(true);
            component.setLeader(player.getNameForScoreboard());

            component.swearOath(oath);

            server.getPlayerManager().broadcast(
                    Text.translatable("death.cleaver", target.getNameForScoreboard(), player.getNameForScoreboard()),
                    false
            );

            stack.set(MorriganDataComponents.STORED_OATH, new StoredOathComponent(Oath.EMPTY));
        }
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
                    stack.set(MorriganDataComponents.STORED_OATH, new StoredOathComponent(Oath.EMPTY));

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

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        ItemStack stack = context.getStack();
        StoredOathComponent oath = stack.get(MorriganDataComponents.STORED_OATH);

        if (player != null) {
            if (player.isSneaking()) {
                if (oath != null) {
                    if (context.getWorld().getBlockState(context.getBlockPos()).isOf(MorriganBlocks.RITUAL_TABLE)) {
                        if (player.getOffHandStack().getItem() instanceof ScryingPaperItem) {
                            Oath toApply = player.getOffHandStack().get(MorriganDataComponents.STORED_OATH).oath();

                            stack.set(MorriganDataComponents.STORED_OATH, new StoredOathComponent(toApply));

                            if (world instanceof ServerWorld serverWorld) {
                                serverWorld.spawnParticles(
                                        new ShockwaveParticleEffect(
                                                toApply.color(),
                                                5,
                                                Direction.Axis.Y
                                        ),
                                        context.getBlockPos().getX() + 0.5f,
                                        context.getBlockPos().getY() + 1.5f,
                                        context.getBlockPos().getZ() + 0.5f,
                                        1,
                                        0,
                                        0,
                                        0,
                                        1
                                );
                            }

                            player.getOffHandStack().split(1);
                            return ActionResult.FAIL;
                        }
                    }
                }
            }
        }
        return super.useOnBlock(context);
    }

    public DamageSource getKillSource(LivingEntity living, @Nullable Entity attacker, float amount) {
        return living.getDamageSources().create(MorriganDamageTypes.EXTOL);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().setStyle(Morrigan.applyFormatting(super.getName(stack)).withColor(stack.get(MorriganDataComponents.STORED_OATH).oath().color()));
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