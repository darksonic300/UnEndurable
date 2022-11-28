package com.mods.unendurable;

import com.mods.unendurable.entities.Wanderer;
import com.mods.unendurable.items.ModArmorMaterial;
import com.mods.unendurable.items.ModSpawnEggItem;
import com.mods.unendurable.loot.WandererDrop;
import com.mods.unendurable.world.gen.ModBiomeGeneration;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nonnull;
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


        RegistryHandler.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegistryHandler.SURFACES.register(FMLJavaModLoadingContext.get().getModEventBus());

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(RegistryHandler.WANDERER.get(), Wanderer.setCustomAttributes().create());
            ModBiomeGeneration.generateBiomes();
        });

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        RenderTypeLookup.setRenderLayer(RegistryHandler.WARMHEART_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(RegistryHandler.WARMHEART_LEAVES.get(), RenderType.getCutout());

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
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event)
    {
        ModSpawnEggItem.initSpawnEggs();
    }

    /*@SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new WandererDrop.Serializer().setRegistryName
                        (new ResourceLocation(UnEndurable.MODID,"phantom_cloth_from_wanderer"))
        );
    }*/

    public static ResourceLocation location(String name)
    {
        return new ResourceLocation(MODID, name);
    }
}
