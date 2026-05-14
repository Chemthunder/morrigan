package com.peak.morrigan.impl.cca.entity;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class AshProfileComponent implements AutoSyncedComponent, CommonTickingComponent {
 //   public static final ComponentKey<AshProfileComponent> KEY = MiscUtils.getOrCreateKey(Morrigan.id("ash_profile"), AshProfileComponent.class);

    public void tick() {

    }

    public void readFromNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }

    public void writeToNbt(NbtCompound nbtCompound, RegistryWrapper.WrapperLookup wrapperLookup) {

    }
}

/*
 * SYSTEMATIC MARTYR PROFILES:
----    CRYOCIDE ----
"Getting cold feet?"
All blocks are now slippery as ice.

---- FLORACIDE ----
"You hear the forest whisper sweet nothings."
Crouching grows all nearby crops.
Can ensnare nearby players with trigger ability, locking movement.

---- PYROCIDE ----
"LET'S GET MOVING!"
Less health but double damage (5-6 hearts),
Can become enraged with trigger ability, doubling speed and regeneration but halving armor protection.


---- AEROCIDE ----
"GOTTA GO FAST!!"
Speed boost.
Can leap into the air and glide.
 */