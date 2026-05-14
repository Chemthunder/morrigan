package com.peak.morrigan.impl.index;

import com.peak.morrigan.impl.Morrigan;
import com.peak.morrigan.impl.block.NevermorianCitadelBlock;
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
    );

    static void init() {}
}
