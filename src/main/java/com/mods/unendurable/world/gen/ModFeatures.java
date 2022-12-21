package com.mods.unendurable.world.gen;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.world.configuration.IcePillarPlacerConfiguration;
import com.mods.unendurable.world.configuration.IceStalagmiteConfiguration;
import com.mods.unendurable.world.feature.IcePillarPlacer;
import com.mods.unendurable.world.feature.IceStalagmitePlacer;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, UnEndurable.MODID);
    public static RegistryObject<Feature<IcePillarPlacerConfiguration>> ICE_PILLAR_PLACER = FEATURES.register("ice_pillar_placer", () -> new IcePillarPlacer(IcePillarPlacerConfiguration.CODEC));
    public static RegistryObject<Feature<IceStalagmiteConfiguration>> ICE_STALAGMITE_PLACER = FEATURES.register("ice_stalagmite_placer", () -> new IceStalagmitePlacer(IceStalagmiteConfiguration.CODEC));

}

