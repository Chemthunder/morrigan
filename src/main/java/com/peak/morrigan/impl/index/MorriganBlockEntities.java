package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.block.entity.NevermorianCitadelBlockEntity;
import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

/**
 * @author Chemthunder
 */
@SuppressWarnings("deprecation")
public interface MorriganBlockEntities {
    BlockEntityTypeRegistrant BLOCK_ENTITIES = new BlockEntityTypeRegistrant(Morrigan.MOD_ID);

    BlockEntityType<NevermorianCitadelBlockEntity> CITADEL = BLOCK_ENTITIES.register(
            "nevermorian_citadel",
            FabricBlockEntityTypeBuilder.create(NevermorianCitadelBlockEntity::new, MorriganBlocks.NEVERMORIAN_CITADEL)
    );

    static void init() {}
}
