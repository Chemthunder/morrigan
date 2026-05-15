package com.peak.morrigan.impl.block;

import com.mojang.serialization.MapCodec;
import com.peak.morrigan.impl.block.entity.NevermorianCitadelBlockEntity;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class NevermorianCitadelBlock extends BlockWithEntity {
    public static final MapCodec<NevermorianCitadelBlock> CODEC = createCodec(NevermorianCitadelBlock::new);

    public static final VoxelShape HITBOX = NevermorianCitadelBlock.createCuboidShape(
            9.0F,
            0.0F,
            9.0F,
            32.0F,
            15.0F,
            32.0F
    );

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

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return HITBOX;
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return HITBOX;
    }
}
