package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class LockMovementComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<LockMovementComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("lock_movement"), LockMovementComponent.class);
    private final LivingEntity living;

    private int ticks = 0;

    public LockMovementComponent(LivingEntity living) {
        this.living = living;
    }

    public void sync() {
        KEY.sync(this.living);
    }

    public void tick() {
        if (this.ticks > 0) {
            this.ticks--;
            if (this.ticks == 0) {
                this.sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.ticks = nbt.getInt("Ticks");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("Ticks", ticks);
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.sync();
    }
}
