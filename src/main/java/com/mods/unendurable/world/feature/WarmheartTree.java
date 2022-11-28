package com.mods.unendurable.world.feature;

import com.mods.unendurable.world.gen.ModConfiguredFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.Random;


public class WarmheartTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random random, boolean largeHive)
    {
        return ModConfiguredFeatures.WARMHEART;
    }
}
