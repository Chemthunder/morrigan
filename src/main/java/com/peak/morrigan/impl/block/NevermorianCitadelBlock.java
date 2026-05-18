package com.peak.morrigan.impl.block;

import com.mojang.serialization.MapCodec;
import com.nitron.nitrogen.util.interfaces.ScreenShaker;
import com.peak.morrigan.impl.block.entity.NevermorianCitadelBlockEntity;
import com.peak.morrigan.impl.index.tag.MorriganEntityTypeTags;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Chemthunder
 */
public class NevermorianCitadelBlock extends BlockWithEntity {
    public static final MapCodec<NevermorianCitadelBlock> CODEC = createCodec(NevermorianCitadelBlock::new);

    public static final VoxelShape HITBOX = NevermorianCitadelBlock.createCuboidShape(
            0.0F,
            0.0F,
            0.0F,
            16.0F,
            32.0F,
            16.0F
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

    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof NevermorianCitadelBlockEntity citadel) {
                citadel.tick(citadel, world1, pos, state1);
            }
        };
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        List<Entity> NEARBY_ENTITIES = world.getEntitiesByClass(
                Entity.class,
                new Box(pos).expand(
                        75,
                        150,
                        75
                ),
                entity -> true
        );

        for (Entity target : NEARBY_ENTITIES) {
            if (isViable(target)) {
                target.kill();
            }

            if (target instanceof PlayerEntity playerEntity) {
                if (playerEntity instanceof ScreenShaker shaker) {
                    shaker.addScreenShake(
                            0.4f,
                            40
                    );
                }
            }
        }

        if (world.isClient()) {
            player.swingHand(player.getActiveHand());
        }

        return super.onUse(state, world, pos, player, hit);
    }

    private boolean isViable(Entity entity) {
        if (!entity.getType().isIn(MorriganEntityTypeTags.IGNORED_BY_CLEAR) || (entity.getType() instanceof Ownable ownable && ownable.getOwner() != null)) {
            return true;
        }
        return false;
    }
}
