package com.peak.morrigan.impl.entity;

import com.peak.morrigan.impl.index.MorriganEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
// might delete since this is a primarily scrapped feature
public class CycleEntity extends ThrownEntity {
    private float boxSize = 0;

    public CycleEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public CycleEntity(World world) {
        super(MorriganEntities.CYCLE, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {}

    public void tick() {
        if (this.boxSize < 40) {
            this.boxSize = this.boxSize + (0.1f + this.boxSize / 50);
        }
    }

    public float getBoxSize() {
        return this.boxSize;
    }

    public void setBoxSize(float i) {
        this.boxSize = i;
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.boxSize = nbt.getFloat("BoxSize");

        super.readCustomDataFromNbt(nbt);
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putFloat("BoxSize", boxSize);

        super.writeCustomDataToNbt(nbt);
    }
}