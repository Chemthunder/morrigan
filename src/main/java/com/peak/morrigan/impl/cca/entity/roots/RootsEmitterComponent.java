package com.peak.morrigan.impl.cca.entity.roots;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class RootsEmitterComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<RootsEmitterComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("roots_emitter"), RootsEmitterComponent.class);
    private final PlayerEntity player;

    private boolean emitting = false;

    public RootsEmitterComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (this.emitting) {
            World world = player.getWorld();

            world.getEntitiesByClass(
                    PlayerEntity.class,
                    new Box(
                            player.getBlockPos()
                    ).expand(15),
                    entity -> true
            ).forEach(capture -> {
                RootsVictimComponent rootsVictimComponent = RootsVictimComponent.KEY.get(capture);
                rootsVictimComponent.setMovementRemovedTicks(80);
            });
        }
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.emitting = nbt.getBoolean("Emitting");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putBoolean("Emitting", emitting);
    }

    public boolean isEmitting() {
        return this.emitting;
    }

    public void setEmitting(boolean emitting) {
        this.emitting = emitting;
        this.sync();
    }
}
