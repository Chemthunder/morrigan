package com.peak.morrigan.impl.cca.entity;

import com.peak.morrigan.impl.Morrigan;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 * Allows controlling of Enchancement enchantments.
 */
public class EnchancementDataComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<EnchancementDataComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("enchancement_data"), EnchancementDataComponent.class);
    private final PlayerEntity player;

    private int movementRemovedTicks = 0;

    public EnchancementDataComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void tick() {
        if (this.movementRemovedTicks > 0) {
            this.movementRemovedTicks--;
            if (this.movementRemovedTicks == 0) {
                this.sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.movementRemovedTicks = nbt.getInt("MovementRemovedTicks");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putInt("MovementRemovedTicks", movementRemovedTicks);
    }

    public int getMovementRemovedTicks() {
        return this.movementRemovedTicks;
    }

    public void setMovementRemovedTicks(int i) {
        this.movementRemovedTicks = i;
        this.sync();
    }
}
