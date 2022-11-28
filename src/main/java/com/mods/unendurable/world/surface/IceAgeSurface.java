package com.mods.unendurable.world.surface;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class IceAgeSurface extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final BlockState SNOW = Blocks.SNOW_BLOCK.getDefaultState();
    public static final BlockState GRASS = Blocks.GRASS_BLOCK.getDefaultState();
    public static final BlockState ICE = Blocks.PACKED_ICE.getDefaultState();

    public static final SurfaceBuilderConfig SNOW_CONFIG = new SurfaceBuilderConfig(SNOW, SNOW, GRASS);
    public static final SurfaceBuilderConfig DIRT_CONFIG = new SurfaceBuilderConfig(GRASS, SNOW, GRASS);
    public static final SurfaceBuilderConfig ICE_CONFIG = new SurfaceBuilderConfig(GRASS, SNOW, GRASS);
    
    public IceAgeSurface(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public ConfiguredSurfaceBuilder<SurfaceBuilderConfig> func_242929_a(SurfaceBuilderConfig p_242929_1_) {
        return new ConfiguredSurfaceBuilder(this, p_242929_1_);
    }

    @Override
    public void buildSurface(Random random, IChunk iChunk, Biome biome, int i, int i1, int i2, double v, BlockState blockState, BlockState blockState1, int i3, long l, SurfaceBuilderConfig surfaceBuilderConfig) {
        if (random.nextInt(3) != 0)
        {
            SurfaceBuilder.DEFAULT.buildSurface(random, iChunk, biome, i, i1, i2, v, blockState, blockState1, i3, l, SNOW_CONFIG);
        }else{
            SurfaceBuilder.DEFAULT.buildSurface(random, iChunk, biome, i, i1, i2, v, blockState, blockState1, i3, l, ICE_CONFIG);
        }
    }

}
