package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class LightDarkComponent implements AutoSyncedComponent {
    public static final ComponentKey<LightDarkComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("light_dark"), LightDarkComponent.class);
    private final PlayerEntity player;

    private boolean invis = false;

    public LightDarkComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.invis = nbt.getBoolean("Invis");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putBoolean("Invis", invis);
    }

    public boolean isInvis() {
        return this.invis;
    }

    public void setInvis(boolean invis) {
        this.invis = invis;
        this.sync();
    }
}
