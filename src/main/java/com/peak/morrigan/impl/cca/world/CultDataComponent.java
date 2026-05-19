package com.peak.morrigan.impl.cca.world;

import com.peak.morrigan.api.Heretic;
import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Chemthunder
 */
public class CultDataComponent implements AutoSyncedComponent {
    public static final ComponentKey<CultDataComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("cult_data"), CultDataComponent.class);
    private final World world;

    private final List<Heretic> heretics = new ArrayList<>();

    public CultDataComponent(World world) {
        this.world = world;
    }

    public void sync() {
        KEY.sync(this.world);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.heretics.clear();
        NbtList hereticList = nbt.getList("Heretics", NbtElement.COMPOUND_TYPE);
        for (NbtElement element : hereticList) {
            Heretic profile = Heretic.CODEC.decode(registryLookup.getOps(NbtOps.INSTANCE), element).resultOrPartial().orElseThrow().getFirst();
            this.heretics.add(profile);
        }
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList hereticList = new NbtList();
        for (Heretic heretic : this.heretics) {
            NbtElement element = Heretic.CODEC.encodeStart(registryLookup.getOps(NbtOps.INSTANCE), heretic).getOrThrow();
            hereticList.add(element);
        }

        nbt.put("Heretics", hereticList);
    }

    public List<Heretic> getHeretics() {
        return this.heretics;
    }

    public void createHeretic(String name, UUID uuid) {
        this.heretics.add(new Heretic(name, String.valueOf(uuid)));
        this.sync();
    }
}
