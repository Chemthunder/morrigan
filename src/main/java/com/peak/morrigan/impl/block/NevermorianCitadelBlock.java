package com.peak.morrigan.impl.block;

import com.mojang.serialization.MapCodec;
import com.peak.morrigan.impl.block.entity.NevermorianCitadelBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class NevermorianCitadelBlock extends BlockWithEntity {
    public static final MapCodec<NevermorianCitadelBlock> CODEC = createCodec(NevermorianCitadelBlock::new);

    public NevermorianCitadelBlock(Settings settings) {
        super(settings);
    }

    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NevermorianCitadelBlockEntity(pos, state);
    }

    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
