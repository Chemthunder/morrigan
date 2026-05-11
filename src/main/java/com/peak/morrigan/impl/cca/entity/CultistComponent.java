package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.api.Oath;
import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class CultistComponent implements AutoSyncedComponent {
    public static final ComponentKey<CultistComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("cultist"), CultistComponent.class);
    private final PlayerEntity player;

    private boolean cultist = false;
    private Oath swornOath = Oath.EMPTY;
    private String leader = "";

    public CultistComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.cultist = nbt.getBoolean("Cultist");
        this.leader = nbt.getString("Leader");

        if (nbt.contains("SwornOath", NbtElement.COMPOUND_TYPE)) {
            this.swornOath = Oath.CODEC.parse(wrapperLookup.getOps(NbtOps.INSTANCE), nbt.getCompound("SwornOath")).resultOrPartial(Morrigan.LOGGER::error).orElseThrow();
        } else {
            this.swornOath = Oath.EMPTY;
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("Cultist", cultist);
        nbt.putString("Leader", leader);

        if (this.swornOath != Oath.EMPTY) {
            nbt.put("SwornOath", Oath.CODEC.encodeStart(wrapperLookup.getOps(NbtOps.INSTANCE), this.swornOath).getOrThrow());
        }
    }

    public Oath getOath() {
        return this.swornOath;
    }

    public boolean isCultist() {
        return this.cultist;
    }

    public String getLeader() {
        return this.leader;
    }

    public void swearOath(Oath oath) {
        this.swornOath = oath;
        this.sync();
    }

    public void setCultist(boolean bl) {
        this.cultist = bl;
        this.sync();
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}
