package com.mods.unendurable;

import com.mods.unendurable.world.biome.UEBiomes;
import com.mods.unendurable.world.gen.ModFeatures;
import com.mods.unendurable.world.gen.ModConfiguredFeatures;
import com.mods.unendurable.world.gen.ModPlacedFeatures;
import com.mods.unendurable.world.gen.UERegion;
import com.mods.unendurable.world.surface.UESurfaceData;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("unendurable")

@Mod.EventBusSubscriber(modid = UnEndurable.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UnEndurable
{
    public static final String MODID = "unendurable";
    public static final String VERSION = "0.1";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public UnEndurable() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        GeckoLib.initialize();
        UEItemGroup.load();
        RegistryHandler.ITEMS.register(bus);
        RegistryHandler.BLOCKS.register(bus);
        RegistryHandler.ENTITIES.register(bus);
        UEEffects.MOB_EFFECTS.register(bus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        ModPlacedFeatures.PLACED_FEATURES.register(bus);
        UEBiomes.BIOME_REGISTER.register(bus);
        ModFeatures.FEATURES.register(bus);
        UEBiomes.registerBiomes();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Given we only add two biomes, we should keep our weight relatively low.
            Regions.register(new UERegion(new ResourceLocation(MODID, "overworld"), 2));

            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, UESurfaceData.makeRules());
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, UESurfaceData.makeRules2());
        });

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("unendurable", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }


    public static ResourceLocation location(String name)
    {
        return new ResourceLocation(MODID, name);
    }
}
