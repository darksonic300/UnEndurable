package com.mods.unendurable.world.feature;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.world.configuration.IceStalagmiteConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class IceStalagmitePlacer extends Feature<IceStalagmiteConfiguration> {
    public IceStalagmitePlacer(Codec<IceStalagmiteConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<IceStalagmiteConfiguration> featurePlaceContext) {
        BlockPos blockpos = featurePlaceContext.origin();
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        RandomSource randomsource = featurePlaceContext.random();
        int maxLengt = 5;

        if(worldgenlevel.isEmptyBlock(blockpos) && !worldgenlevel.isEmptyBlock(blockpos.above(1))) {
            maxLengt = maxLengt-randomsource.nextInt(0, 3);
            for(int i = 0; i <= maxLengt; i++) {
                this.setBlock(worldgenlevel, blockpos.below(i), Blocks.ICE.defaultBlockState());
            }

            int maxLengthSide = maxLengt - randomsource.nextInt(1, maxLengt-1);
            for(int i = 0; i <= maxLengthSide; i++) {
                this.setBlock(worldgenlevel, blockpos.below(i).west(1), Blocks.ICE.defaultBlockState());
            }

            maxLengthSide = maxLengt - randomsource.nextInt(1, maxLengt-1);
            for(int i = 0; i <= maxLengthSide; i++) {
                this.setBlock(worldgenlevel, blockpos.below(i).east(1), Blocks.ICE.defaultBlockState());
            }

            maxLengthSide = maxLengt - randomsource.nextInt(1, maxLengt-1);
            for(int i = 0; i <= maxLengthSide; i++) {
                this.setBlock(worldgenlevel, blockpos.below(i).north(1), Blocks.ICE.defaultBlockState());
            }

            maxLengthSide = maxLengt - randomsource.nextInt(1, maxLengt-1);
            for(int i = 0; i <= maxLengthSide; i++) {
                this.setBlock(worldgenlevel, blockpos.below(i).south(1), Blocks.ICE.defaultBlockState());
            }
/*
            for(int i = 0; i > 10; i++)
                if(!worldgenlevel.isEmptyBlock(blockpos.below(maxLengt + i+1))) {
                    blockpos = blockpos.below(maxLengt + i);
                    if(randomsource.nextInt(0, 2) > 0) this.setBlock(worldgenlevel, blockpos, RegistryHandler.ICE_CARPET.get().defaultBlockState());
                    if(randomsource.nextInt(0, 2) > 0) this.setBlock(worldgenlevel, blockpos.below(1), Blocks.ICE.defaultBlockState());
                    if(randomsource.nextInt(0, 2) > 0 && !worldgenlevel.isEmptyBlock(blockpos.below(1).east(1))) this.setBlock(worldgenlevel, blockpos.east(1), RegistryHandler.ICE_CARPET.get().defaultBlockState());
                    if(randomsource.nextInt(0, 2) > 0 && !worldgenlevel.isEmptyBlock(blockpos.below(1).west(1))) this.setBlock(worldgenlevel, blockpos.west(1), RegistryHandler.ICE_CARPET.get().defaultBlockState());
                    if(randomsource.nextInt(0, 2) > 0 && !worldgenlevel.isEmptyBlock(blockpos.below(1).north(1))) this.setBlock(worldgenlevel, blockpos.north(1), RegistryHandler.ICE_CARPET.get().defaultBlockState());
                    if(randomsource.nextInt(0, 2) > 0 && !worldgenlevel.isEmptyBlock(blockpos.below(1).south(1))) this.setBlock(worldgenlevel, blockpos.south(1), RegistryHandler.ICE_CARPET.get().defaultBlockState());
                    break;
                }*/
            return true;
        }

        else return false;


    }
}