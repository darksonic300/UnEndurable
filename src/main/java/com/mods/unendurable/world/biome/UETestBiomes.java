package com.mods.unendurable.world.biome;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.world.gen.ModConfiguredFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;

import javax.annotation.Nullable;

public class UETestBiomes {
        @Nullable
        private static final Music NORMAL_MUSIC = null;

        protected static int calculateSkyColor(float color)
        {
            float $$1 = color / 3.0F;
            $$1 = Mth.clamp($$1, -1.0F, 1.0F);
            return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
        }

        private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
        {
            return biome(precipitation, temperature, downfall,
                    4159204, 329011,
                    spawnBuilder, biomeBuilder, music);
        }

        private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
        {
            return (new Biome.BiomeBuilder()).precipitation(precipitation)
                    .temperature(temperature).downfall(downfall)
                    .specialEffects((new BiomeSpecialEffects.Builder())
                            .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05f))
                            .waterColor(waterColor).waterFogColor(waterFogColor)
                            .fogColor(12638463)
                            .skyColor(calculateSkyColor(temperature))
                            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                            .backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build())
                    .generationSettings(biomeBuilder.build()).build();
        }

        private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder)
        {
            BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
            BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
            BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
            BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
            BiomeDefaultFeatures.addDefaultSprings(builder);
            BiomeDefaultFeatures.addSurfaceFreezing(builder);
        }

        //TODO: Add Warmheart Tree generation.
        //TODO: Adjust old Biome parameters.
        public static Biome iceAge()
        {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
            spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(RegistryHandler.WANDERER.get(), 5, 1, 3));
            BiomeDefaultFeatures.commonSpawns(spawnBuilder);
            BiomeDefaultFeatures.snowySpawns(spawnBuilder);

            BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
            globalOverworldGeneration(biomeBuilder);
            BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
            BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
            BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
            BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
            return biome(Biome.Precipitation.SNOW, -0.7F, 0.9F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }
