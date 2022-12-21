package com.mods.unendurable.world.biome;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.world.gen.ModConfiguredFeatures;
import com.mods.unendurable.world.gen.ModPlacedFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

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
                    4159204, 16777215,
                    spawnBuilder, biomeBuilder, music);
        }

        private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music)
        {
            return (new Biome.BiomeBuilder()).precipitation(precipitation)
                    .temperature(temperature).downfall(downfall)
                    .specialEffects((new BiomeSpecialEffects.Builder())
                            .ambientParticle(new AmbientParticleSettings(ParticleTypes.WHITE_ASH, 0.05f))
                            .waterColor(waterColor).waterFogColor(waterFogColor)
                            .fogColor(16777215)
                            .skyColor(7633547)
                            .foliageColorOverride(16777215)
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

        //TODO: Adjust Biome and Tree parameters.
        public static Biome iceAge()
        {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
            spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(RegistryHandler.WANDERER.get(), 5, 1, 3));

            BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
            globalOverworldGeneration(biomeBuilder);
            BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
            BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
            BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
            BiomeDefaultFeatures.addInfestedStone(biomeBuilder);

            addWarmheartTree(biomeBuilder);
            return biome(Biome.Precipitation.SNOW, -0.7F, 0.9F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }

        public static void addWarmheartTree(BiomeGenerationSettings.Builder builder) {
            builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WARMHEART_PLACED.getHolder().get());
        }


        public static Biome frozenCaverns () {
            MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
            BiomeDefaultFeatures.commonSpawns(spawnBuilder);
            spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.STRAY, 9, 4, 8));
            spawnBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(RegistryHandler.WANDERER.get(), 10, 1, 8));

            // Features
            BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
            globalOverworldGeneration(biomeBuilder);
            BiomeDefaultFeatures.addFrozenSprings(biomeBuilder);
            BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
            BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);

            biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ICE_SHEET_PLACED.getHolder().get());
            //genBuilder.addFeature(GenerationStep.Decoration.LAKES, ModPlacedFeatures.ICE_LAKE_PLACED.getHolder().get());
            biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ICE_PILLAR_PLACED.getHolder().get());
            biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.PACKED_ICE_PATCH_PLACED.getHolder().get());
            biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.BLUE_ICE_PATCH_PLACED.getHolder().get());
            biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ICE_STALAGMITE_PLACED.getHolder().get());
            return biome(Biome.Precipitation.SNOW, -0.7F, 0.9F, spawnBuilder, biomeBuilder, NORMAL_MUSIC);
        }
    }
