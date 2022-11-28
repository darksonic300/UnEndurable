package com.mods.unendurable.blocks;

import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class WarmheartLog extends RotatedPillarBlock {

    private final int encouragement;
    private final int flammability;

    public WarmheartLog(int encouragement, int flammability) {
        super(Properties.from(Blocks.STRIPPED_SPRUCE_LOG));
        this.encouragement = encouragement;
        this.flammability = flammability;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return encouragement;
    }

    @Override
    public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
        return flammability;
    }
}
