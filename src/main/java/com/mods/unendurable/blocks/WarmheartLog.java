package com.mods.unendurable.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;

public class WarmheartLog extends RotatedPillarBlock {

    public WarmheartLog(int encouragement, int flammability) {
        super(Properties.copy(Blocks.STRIPPED_SPRUCE_LOG));
    }
}
