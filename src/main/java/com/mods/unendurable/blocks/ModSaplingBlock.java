package com.mods.unendurable.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;
import java.util.function.Supplier;

public class ModSaplingBlock extends SaplingBlock implements IGrowable {

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(
            2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    private final Supplier<Tree> tree;

    public ModSaplingBlock(Supplier<Tree> treeIn, Properties p_i48337_2_) {
        super((Tree) treeIn, p_i48337_2_);
        this.tree = treeIn;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public void tick(BlockState state, ServerWorld serverWorld, BlockPos pos, Random random) {
        super.tick(state, serverWorld, pos, random);
        if (!serverWorld.isAreaLoaded(pos, 1))
        {
            return;
        }
        if (serverWorld.getLight(pos.up()) >= 9 && random.nextInt(7) == 0)
        {
            this.grow(serverWorld, random, pos, state);
        }
    }

    public void grow(ServerWorld serverWorld, BlockPos pos, BlockState state, Random random) {
        if (state.get(STAGE) == 0)
        {
            serverWorld.setBlockState(pos, state.cycleValue(STAGE), 4);
        }else{
            if (!ForgeEventFactory.saplingGrowTree(serverWorld, random, pos)) return;
            this.tree.get().attemptGrowTree(serverWorld, serverWorld.getChunkProvider().getChunkGenerator(), pos, state, random);
        }
    }

    @Override
    public void grow(ServerWorld serverWorld, Random random, BlockPos pos, BlockState state) {
        this.grow(serverWorld, pos, state, random);
    }

    @Override
    public boolean canGrow(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World serverWorld, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return (double)serverWorld.rand.nextFloat() < 0.45D;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }
}
