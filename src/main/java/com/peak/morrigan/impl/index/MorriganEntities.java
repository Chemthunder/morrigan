package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.client.render.entity.CycleEntityRenderer;
import com.peak.morrigan.impl.entity.CycleEntity;
import com.peak.morrigan.impl.entity.SharpShotEntity;
import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

/**
 * @author Chemthunder
 */
public interface MorriganEntities {
    EntityTypeRegistrant ENTITIES = new EntityTypeRegistrant(Morrigan.MOD_ID);

    EntityType<CycleEntity> CYCLE = ENTITIES.register("cycle",
            EntityType.Builder.<CycleEntity>create(
                    CycleEntity::new,
                    SpawnGroup.MISC)
                    .dimensions(0.4f, 0.4f)
    );

    EntityType<SharpShotEntity> SHARP_SHOT = ENTITIES.register("sharp_shot",
            EntityType.Builder.<SharpShotEntity>create(
                            SharpShotEntity::new,
                            SpawnGroup.MISC)
                    .dimensions(0.4f, 0.9f)
    );

    static void init() {}

    @Environment(EnvType.CLIENT)
    static void clientInit() {
        EntityRendererRegistry.register(CYCLE, CycleEntityRenderer::new);
    }
}