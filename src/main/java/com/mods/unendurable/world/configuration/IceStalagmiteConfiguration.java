package com.mods.unendurable.world.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record IceStalagmiteConfiguration(BlockStateProvider blockstate) implements FeatureConfiguration {

    public static final Codec<IceStalagmiteConfiguration> CODEC = RecordCodecBuilder.create((p_190962_) -> p_190962_.group(BlockStateProvider.CODEC.fieldOf("blockstate").forGetter(IceStalagmiteConfiguration::blockstate)).apply(p_190962_, IceStalagmiteConfiguration::new));
}