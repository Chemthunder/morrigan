package com.peak.morrigan.impl.block.entity;

import com.peak.morrigan.impl.cca.entity.InBoxComponent;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import com.peak.morrigan.impl.index.MorriganBlockEntities;
import com.peak.morrigan.impl.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class NevermorianCitadelBlockEntity extends BlockEntity {
    public NevermorianCitadelBlockEntity(BlockPos pos, BlockState state) {
        super(MorriganBlockEntities.CITADEL, pos, state);
    }

    public void tick(NevermorianCitadelBlockEntity be, World world, BlockPos pos, BlockState state) {
        List<LivingEntity> NEARBY_ENTITIES = world.getEntitiesByClass(LivingEntity.class, new Box(pos).expand(75, 150, 75), entity -> true);

        for (LivingEntity target : NEARBY_ENTITIES) {
            if (target instanceof PlayerEntity player) {
                CultistComponent cultistComponent = ModUtils.getCultistInstance(player);

                if (!cultistComponent.isCultist()) {
                    InBoxComponent component = InBoxComponent.KEY.get(target);
                    component.setInBox(true);
                }
            }
        }
    }
}