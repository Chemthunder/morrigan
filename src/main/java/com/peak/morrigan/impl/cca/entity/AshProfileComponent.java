package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.api.AshProfile;
import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.cca.entity.core.CultistComponent;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class AshProfileComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<AshProfileComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("ash_profile"), AshProfileComponent.class);
    private final PlayerEntity player;

    private AshProfile currentProfile = AshProfile.EMPTY;

    public AshProfileComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void tick() {
        CultistComponent cultistComponent = CultistComponent.KEY.get(player);

        if (!cultistComponent.isCultist()) {
            if (this.getCurrentProfile() != AshProfile.EMPTY) {
                this.setCurrentProfile(AshProfile.EMPTY);
            }
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (nbt.contains("CurrentProfile", NbtElement.COMPOUND_TYPE)) {
            this.currentProfile = AshProfile.CODEC.parse(wrapperLookup.getOps(NbtOps.INSTANCE), nbt.getCompound("CurrentProfile")).resultOrPartial(Morrigan.LOGGER::error).orElseThrow();
        } else {
            this.currentProfile = AshProfile.EMPTY;
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        if (this.currentProfile != AshProfile.EMPTY) {
            nbt.put("CurrentProfile", AshProfile.CODEC.encodeStart(wrapperLookup.getOps(NbtOps.INSTANCE), this.currentProfile).getOrThrow());
        }
    }

    public AshProfile getCurrentProfile() {
        return this.currentProfile;
    }

    public void setCurrentProfile(AshProfile s) {
        this.currentProfile = s;
        this.sync();
    }
}