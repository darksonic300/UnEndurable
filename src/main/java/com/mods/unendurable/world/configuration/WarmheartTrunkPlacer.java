package com.mods.unendurable.world.configuration;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
//Might finish this later, tried to create a curved trunk placer.
public class WarmheartTrunkPlacer extends TrunkPlacer {
    public static final Codec<WarmheartTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70261_) -> {
        return trunkPlacerParts(p_70261_).apply(p_70261_, WarmheartTrunkPlacer::new);
    });
    int curve;
    public WarmheartTrunkPlacer(int p_70248_, int p_70249_, int p_70250_) {
        super(p_70248_, p_70249_, p_70250_);
    }

    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> blockPosBlockStateBiConsumer, RandomSource randomSource, int maxHeight, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        setDirtAt(levelSimulatedReader, blockPosBlockStateBiConsumer, randomSource, blockPos.below(), treeConfiguration);

        curve = randomSource.nextInt(1, 3);
        int direction = randomSource.nextInt(0, 4);
        int b = randomSource.nextInt(2, 5);
        int c = maxHeight/b;

        for (int i = 0; i < maxHeight; ++i) {
            if (i == c) {
                c = c + maxHeight/b;
                if (direction == 0) blockPos = blockPos.north(1);
                if (direction == 1) blockPos = blockPos.west(1);
                if (direction == 2) blockPos = blockPos.south(1);
                if (direction == 3) blockPos = blockPos.east(1);
            }

            this.placeLog(levelSimulatedReader, blockPosBlockStateBiConsumer, randomSource, blockPos, treeConfiguration);
            blockPos = blockPos.above(1);
        }
        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos, 0, false));
    }
}