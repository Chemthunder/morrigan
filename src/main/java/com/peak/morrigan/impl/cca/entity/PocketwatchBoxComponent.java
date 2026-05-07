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
public class PocketwatchBoxComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<PocketwatchBoxComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("pocketwatch_box"), PocketwatchBoxComponent.class);
    private final LivingEntity living;

    private boolean isInBox = false;

    public PocketwatchBoxComponent(LivingEntity living) {
        this.living = living;
    }

    public void tick() {

    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.isInBox = nbtCompound.getBoolean("IsInBox");
    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbtCompound.putBoolean("IsInBox", isInBox);
    }
}
