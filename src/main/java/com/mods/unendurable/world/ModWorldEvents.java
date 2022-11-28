package com.mods.unendurable.world;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.mods.unendurable.UnEndurable.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ModWorldEvents {
        @SubscribeEvent
        public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
            ModTreeGeneration.generateTrees(event);
        }
    }
