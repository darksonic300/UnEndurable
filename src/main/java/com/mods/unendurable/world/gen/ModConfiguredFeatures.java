package com.mods.unendurable.world.gen;

import com.mods.unendurable.RegistryHandler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> WARMHEART =
            register("warmheart", Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(RegistryHandler.WARMHEART_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(RegistryHandler.WARMHEART_LEAVES.get().getDefaultState()),
                            new SpruceFoliagePlacer(
                                    FeatureSpread.create(2),
                                    FeatureSpread.create(0),
                                    FeatureSpread.create(3)),
                            new StraightTrunkPlacer(7, 2, 3),
                            new TwoLayerFeature(3, 2, 1))).setIgnoreVines().build()));


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
