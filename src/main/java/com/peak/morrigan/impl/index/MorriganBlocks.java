package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.block.NevermorianCitadelBlock;
import com.peak.morrigan.impl.block.RitualTableBlock;
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;

/**
 * @author Chemthunder
 */
public interface MorriganBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(Morrigan.MOD_ID);

    Block NEVERMORIAN_CITADEL = BLOCKS.register(
            "nevermorian_citadel",
            NevermorianCitadelBlock::new,
            AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    .dropsNothing()
                    .noBlockBreakParticles()
                    .sounds(BlockSoundGroup.LODESTONE)
                    .nonOpaque()
                    .luminance(value -> 9)
                    .emissiveLighting((state, world, pos) -> true)
    );

    Block RITUAL_TABLE = BLOCKS.register(
            "ritual_table",
            RitualTableBlock::new,
            AbstractBlock.Settings.copy(Blocks.SMITHING_TABLE)
                    .luminance(value -> 4)
                    .emissiveLighting((state, world, pos) -> true)
    );

    static void init() {}
}
