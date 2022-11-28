package com.mods.unendurable.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class WarmheartLeaves extends Block {

    private final int encouragement;
    private final int flammability;

    public WarmheartLeaves(int encouragement, int flammability) {
        super(Properties.from(Blocks.SPRUCE_LEAVES));
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
