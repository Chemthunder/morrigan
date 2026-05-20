package com.peak.morrigan.impl.entity;

import com.peak.morrigan.impl.index.MorriganEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class SharpShotEntity extends ThrownEntity {
    public SharpShotEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public SharpShotEntity(World world) {
        super(MorriganEntities.SHARP_SHOT, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {

    }

    public void tick() {



        super.tick();
    }
}
