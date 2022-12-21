package com.mods.unendurable.world.feature;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.world.configuration.IcePillarPlacerConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class IcePillarPlacer extends Feature<IcePillarPlacerConfiguration> {
    public IcePillarPlacer(Codec<IcePillarPlacerConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<IcePillarPlacerConfiguration> featurePlaceContext) {
        BlockPos blockpos = featurePlaceContext.origin();
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        RandomSource randomsource = featurePlaceContext.random();
        int maxLengt = 10;
        int SecondaryDirectionChance;
        int MainDirection = randomsource.nextInt(0, 3);
        boolean SecondaryDirection = randomsource.nextBoolean();


        if (worldgenlevel.isEmptyBlock(blockpos))
            return false;
        boolean startup = false;
        for (int iii = 0; iii <= 4; iii++) {
            if (!worldgenlevel.isEmptyBlock(blockpos.below(iii))) {
                startup = true;
            }
        }
        if (!startup) {
            return false;
        }

        BlendInWithTerrain(blockpos, worldgenlevel, randomsource, 1);

        boolean canContinueBuilding = true;
        //IF SOUP
        for (int i = 0; i <= maxLengt; i++) {
            for (int ii = 0; ii <= 1; ii++) {
                if (canContinueBuilding) {
                    SecondaryDirectionChance = randomsource.nextInt(0, 4);
                    canContinueBuilding = placeCircle(blockpos, worldgenlevel, randomsource);
                    blockpos = blockpos.above(1);
                    if (MainDirection == 0) {
                        blockpos = blockpos.east(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection == true)
                                blockpos = blockpos.south(1);
                            else
                                blockpos = blockpos.north(1);
                        }
                    } else if (MainDirection == 1) {
                        blockpos = blockpos.west(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection == true)
                                blockpos = blockpos.south(1);
                            else
                                blockpos = blockpos.north(1);
                        }
                    } else if (MainDirection == 2) {
                        blockpos = blockpos.south(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection == true)
                                blockpos = blockpos.west(1);
                            else
                                blockpos = blockpos.east(1);
                        }
                    } else {
                        blockpos = blockpos.north(1);
                        if (SecondaryDirectionChance == 0) {
                            if (SecondaryDirection == true)
                                blockpos = blockpos.west(1);
                            else
                                blockpos = blockpos.east(1);
                        }
                    }
                } else BlendInWithTerrain(blockpos, worldgenlevel, randomsource, -1);
                canContinueBuilding = placeCircle(blockpos, worldgenlevel, randomsource);
                blockpos = blockpos.above(1);
            }
        }
        placeTopCircle(blockpos, worldgenlevel, randomsource);
        return true;
    }

    public boolean placeCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource) {
        int a;
        int stop = 0;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5) {
                a = 3;
                for (int i = 1; i <= a; ) {
                    i++;
                    int airChance = randomSource.nextInt(0, 5);
                    if (worldGenLevel.isEmptyBlock(blockPos.east(i + 1).south(ii))) {
                        if (airChance > 0) {
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii), Blocks.ICE.defaultBlockState());
                        }
                    } else stop += 1;
                }
            } else {
                a = 5;
                for (int i = 1; i <= a; ) {
                    i++;
                    int airChance = randomSource.nextInt(0, 5);
                    if (worldGenLevel.isEmptyBlock(blockPos.east(i).south(ii))) {
                        if (airChance > 0) {
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii), Blocks.ICE.defaultBlockState());
                        }
                    } else stop += 1;
                }
            }
        }
        if (stop >= 21)
            return false;
        else
            return true;
    }

    public void placeTopCircle(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource) {
        for (int ii = 1; ii <= 3; ii++) {
            for (int i = 1; i <= 3; ) {
                i++;
                int airChance = randomSource.nextInt(0, 5);
                if (worldGenLevel.isEmptyBlock(blockPos.east(i + 1).south(ii))) {
                    if (airChance > 0) {
                        this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii + 1), Blocks.ICE.defaultBlockState());
                    }
                }
            }
        }
    }

    public void BlendInWithTerrain(BlockPos blockPos, WorldGenLevel worldGenLevel, RandomSource randomSource, int ceilingOrFloor) {
        int a;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5) {
                a = 5;
                for (int i = 1; i <= a; ) {
                    i++;
                    Block block = worldGenLevel.getBlockState(blockPos.east(i-2).south(ii)).getBlock();
                    if (block == RegistryHandler.FROZEN_STONE.get() || block == Blocks.STONE || block == Blocks.DEEPSLATE) {
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii).below(ceilingOrFloor), Blocks.PACKED_ICE.defaultBlockState());
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i + 1).south(ii).below(ceilingOrFloor * 2), Blocks.PACKED_ICE.defaultBlockState());

                    }
                }
            } else {
                a = 7;
                for (int i = 1; i <= a; ) {
                    i++;
                    Block block = worldGenLevel.getBlockState(blockPos.east(i-1).south(ii)).getBlock();
                    if (block == RegistryHandler.FROZEN_STONE.get() || block == Blocks.STONE || block == Blocks.DEEPSLATE) {
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii).below(ceilingOrFloor), Blocks.PACKED_ICE.defaultBlockState());
                        if (randomSource.nextInt(0, 6) > 0)
                            this.setBlock(worldGenLevel, blockPos.east(i).south(ii).below(ceilingOrFloor * 2), Blocks.PACKED_ICE.defaultBlockState());
                    }
                }
            }
        }
    }
}
    /*public boolean canPlace(BlockPos blockPos, WorldGenLevel worldGenLevel) {
        int a;
        int fails = 0;
        for (int ii = 1; ii <= 5; ii++) {
            if (ii == 1 || ii == 5) {
                a = 3;
                for (int i = 1; i <= a; ) {
                    i++;
                    int canPlace = 0;
                    for (int iii = 0; iii <= 4; iii++)
                    if (worldGenLevel.isEmptyBlock(blockPos.east(i + 1).south(ii).below(iii)) && (canPlace <= 4)) {
                        canPlace++;
                    }
                    else if((canPlace != 0) && (canPlace > 4)) {
                        this.setBlock(blockPos.east(i + 1).south(ii).below(iii));
                    }
                    else {
                        fails++;
                    }
                }
            } else {
                a = 5;
                for (int i = 1; i <= a; ) {
                    i++;
                    this.setBlock(worldGenLevel, blockPos.east(i).south(ii), Blocks.ICE.defaultBlockState());
                }
            }
        }
}
     */


       /*int i;
        WorldGenLevel reader = context.level();
        int curvedBlocks = 0;
        int total = 0;
        int randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i + randomSource1 <= 2; i++) {
            total++;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        curvedBlocks += 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i + randomSource1 <= 2; i++) {
            total++;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        curvedBlocks += 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i - randomSource1 <= 2; i++) {
            total++;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        total += 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i - randomSource1 <= 2; i++) {
            curvedBlocks++;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        total += 1;
        for (i = 0; i <= 2; i++) {
            curvedBlocks++;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }

        total -= 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i - randomSource1 <= 2; i++) {
            curvedBlocks--;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        curvedBlocks -= 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i - randomSource1 <= 6; i++) {
            total--;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        curvedBlocks -= 1;
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i + randomSource1 <= 4; i++) {
            total--;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }
        randomSource1 = randomSource.nextInt(0, 2);
        for (i = 0; i + randomSource1 <= 2; i++) {
            total--;
            this.setBlock(reader, blockpos.above(total).east(curvedBlocks), blockstate);
        }*/



