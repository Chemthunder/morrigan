package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.roots.RootsVictimComponent;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class InBoxComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<InBoxComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("in_box"), InBoxComponent.class);
    private final LivingEntity living;
    
    private int inBoxTicks = 0;
    private boolean inBox = false;

    public InBoxComponent(LivingEntity living) {
        this.living = living;
    }

    public void sync() {
        KEY.sync(this.living);
    }

    public void tick() {
        if (this.inBoxTicks > 0) {
            this.inBoxTicks--;

            if (this.living instanceof PlayerEntity player) {
                RootsVictimComponent.KEY.get(player).setMovementRemovedTicks(90);
            }

            if (!this.inBox) {
                this.inBox = true;
                this.sync();
            }

            if (this.inBoxTicks == 0) {
                this.inBox = false;
                this.sync();
            }
        }

        if (this.inBoxTicks == 0 && this.inBox) {
            this.inBox = false;
            this.sync();
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("InBoxTicks", this.inBoxTicks);
        nbt.putBoolean("InBox", this.inBox);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.inBoxTicks = nbt.getInt("InBoxTicks");
        this.inBox = nbt.getBoolean("InBox");
    }

    public boolean isInBox() {
        return this.inBox;
    }

    public void setInBox(boolean inBox) {
        this.inBox = inBox;
        this.inBoxTicks = inBox ? 24 : 0;
        this.sync();
    }

    public int getInBoxTicks() {
        return this.inBoxTicks;
    }
}