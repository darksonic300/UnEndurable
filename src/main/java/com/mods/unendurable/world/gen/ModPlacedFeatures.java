package com.mods.unendurable.world.gen;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UnEndurable;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, UnEndurable.MODID);

    //TREES
    public static final RegistryObject<PlacedFeature> WARMHEART_CHECKED = PLACED_FEATURES
            .register("warmheart_check", () -> new PlacedFeature(ModConfiguredFeatures.WARMHEART.getHolder().get(),
            List.of(PlacementUtils.filteredByBlockSurvival(RegistryHandler.WARMHEART_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> WARMHEART_PLACED = PLACED_FEATURES.register("warmheart_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>)(Holder<? extends ConfiguredFeature<?,?>>)
                    ModConfiguredFeatures.WARMHEART_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(10, 0.1F, 1))));
    public static final RegistryObject<PlacedFeature> ICE_PILLAR_PLACED = PLACED_FEATURES.register("ice_pillar_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ICE_PILLAR.getHolder().get(), List.of(
                    CountPlacement.of(6),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    InSquarePlacement.spread(),
                    BiomeFilter.biome()
            )));

    public static final RegistryObject<PlacedFeature> ICE_STALAGMITE_PLACED = PLACED_FEATURES.register("ice_stalagmite_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ICE_STALAGMITE.getHolder().get(), List.of(
                    CountPlacement.of(18),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    InSquarePlacement.spread(),
                    BiomeFilter.biome()
            )));

    public static final RegistryObject<PlacedFeature> ICE_SHEET_PLACED = PLACED_FEATURES.register("ice_sheet_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.ICE_SHEET.getHolder().get(), List.of(
                    CountPlacement.of(100),
                    PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                    InSquarePlacement.spread(),
                    BiomeFilter.biome()
            )));

    public static final RegistryObject<PlacedFeature> PACKED_ICE_PATCH_PLACED = PLACED_FEATURES.register("packed_ice_patch",
            () -> new PlacedFeature(ModConfiguredFeatures.PACKED_ICE_PATCH.getHolder().get(),
                    commonOrePlacement(20, // VeinsPerChunk
                            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT)));

    public static final RegistryObject<PlacedFeature> BLUE_ICE_PATCH_PLACED = PLACED_FEATURES.register("blue_ice_patch",
            () -> new PlacedFeature(ModConfiguredFeatures.BLUE_ICE_PATCH.getHolder().get(),
                    commonOrePlacement(10, // VeinsPerChunk
                            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT)));



    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }
    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }
    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
