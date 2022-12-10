package com.mods.unendurable.world.gen;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UnEndurable;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
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
}
