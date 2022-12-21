package com.mods.unendurable.world.gen;

import com.google.common.base.Suppliers;
import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.world.configuration.IcePillarPlacerConfiguration;
import com.mods.unendurable.world.configuration.IceStalagmiteConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {


    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, UnEndurable.MODID);

    public static IcePillarPlacerConfiguration icePillarConfiguration(BlockStateProvider blockstate) {
        return new IcePillarPlacerConfiguration(blockstate);
    }

    public static IceStalagmiteConfiguration iceStalagmiteConfiguration(BlockStateProvider blockstate) {
        return new IceStalagmiteConfiguration(blockstate);
    }

    public static final RegistryObject<ConfiguredFeature<?, ?>> WARMHEART = CONFIGURED_FEATURES.register("warmheart_tree",
            () -> new ConfiguredFeature<>(Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(RegistryHandler.WARMHEART_LOG.get()),
                    new StraightTrunkPlacer(7, 2, 3),
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(RegistryHandler.WARMHEART_LEAVES.get().defaultBlockState(), 2)),
                    new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, ConstantInt.of(3)),
                    new TwoLayersFeatureSize(3, 2, 1)).ignoreVines().build()));


    public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> WARMHEART_SPAWN =
            CONFIGURED_FEATURES.register("warmheart_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ModPlacedFeatures.WARMHEART_CHECKED.getHolder().get(), 0.5F)),
                            ModPlacedFeatures.WARMHEART_CHECKED.getHolder().get())));


    public static final Supplier<List<OreConfiguration.TargetBlockState>> PACKED_ICE_PATCH_FROZEN_CAVES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(RegistryHandler.FROZEN_STONE.get()), Blocks.PACKED_ICE.defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> BLUE_ICE_PATCH_FROZEN_CAVES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(RegistryHandler.FROZEN_STONE.get()), Blocks.BLUE_ICE.defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PACKED_ICE_PATCH = CONFIGURED_FEATURES.register("packed_ice_patch",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(PACKED_ICE_PATCH_FROZEN_CAVES.get(),11)
            ));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BLUE_ICE_PATCH = CONFIGURED_FEATURES.register("blue_ice_patch",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(BLUE_ICE_PATCH_FROZEN_CAVES.get(),5)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_SHEET = CONFIGURED_FEATURES.register("ice_sheet",
            () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(75, 7, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,

                            new SimpleBlockConfiguration(BlockStateProvider.simple(RegistryHandler.ICE_CARPET.get()))))));


    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_PILLAR = CONFIGURED_FEATURES.register("ice_pillar",
            () -> new ConfiguredFeature<>(ModFeatures.ICE_PILLAR_PLACER.get(),
                    ModConfiguredFeatures.icePillarConfiguration(BlockStateProvider.simple(Blocks.ICE))));

    public static final RegistryObject<ConfiguredFeature<?, ?>> ICE_STALAGMITE = CONFIGURED_FEATURES.register("ice_stalagmite",
            () -> new ConfiguredFeature<>(ModFeatures.ICE_STALAGMITE_PLACER.get(),
                    ModConfiguredFeatures.iceStalagmiteConfiguration(BlockStateProvider.simple(Blocks.ICE))));

}
