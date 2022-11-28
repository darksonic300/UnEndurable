package com.mods.unendurable.world.biome;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.world.surface.IceAgeSurface;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.*;

import java.util.Random;

public class ModConfiguredSurfaceBuilders {

        public static ConfiguredSurfaceBuilder<?> ICE_SURFACE = register("ice_surface", SurfaceBuilder.DEFAULT.func_242929_a( new SurfaceBuilderConfig(
                Blocks.GRASS_BLOCK.getDefaultState(),
                Blocks.SNOW_BLOCK.getDefaultState(),
                Blocks.PACKED_ICE.getDefaultState()
        )));

        private ConfiguredSurfaceBuilder<SurfaceBuilderConfig> randomGenIce()
        {
            Random random = new Random();
            if (random.nextInt(3) != 0)
            {
                return IceAgeSurface.DEFAULT.func_242929_a(IceAgeSurface.SNOW_CONFIG);
            }else{
                return IceAgeSurface.DEFAULT.func_242929_a(IceAgeSurface.ICE_CONFIG);
            }
        }
        
        private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> csb) {
            return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
                    new ResourceLocation(UnEndurable.MODID, name), csb);
        }
    }
