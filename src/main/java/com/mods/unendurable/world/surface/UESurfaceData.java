package com.mods.unendurable.world.surface;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.world.biome.UEBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class UESurfaceData {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static final SurfaceRules.RuleSource STONE_BLOCK = makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource ICY_GRASS = makeStateRule(Blocks.GRASS_BLOCK);

    private static final SurfaceRules.RuleSource FROZEN_STONE = makeStateRule(RegistryHandler.FROZEN_STONE.get());



    public static SurfaceRules.RuleSource makeRules()
    {

        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);


        return SurfaceRules.sequence(

            SurfaceRules.ifTrue(SurfaceRules.isBiome(UEBiomes.ICE_AGE), ICY_GRASS),
            // Default to a grass and dirt surface
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }
    public static SurfaceRules.RuleSource makeRules2() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(UEBiomes.FROZEN_CAVES), FROZEN_STONE)
        );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }

}
