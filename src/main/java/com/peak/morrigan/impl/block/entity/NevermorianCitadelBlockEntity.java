package com.peak.morrigan.impl.block.entity;

import com.peak.morrigan.impl.index.MorriganBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

/**
 * @author Chemthunder
 */
public class NevermorianCitadelBlockEntity extends BlockEntity {
    public NevermorianCitadelBlockEntity(BlockPos pos, BlockState state) {
        super(MorriganBlockEntities.CITADEL, pos, state);
    }
}
