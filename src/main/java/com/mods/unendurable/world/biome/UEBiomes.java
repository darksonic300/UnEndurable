package com.mods.unendurable.world.biome;

import com.mods.unendurable.UnEndurable;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UEBiomes
{
    public static DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create(Registry.BIOME_REGISTRY, UnEndurable.MODID);

    public static void registerBiomes()
    {
        register(ICE_AGE, UETestBiomes::iceAge);
        register(FROZEN_CAVES, UETestBiomes::frozenCaverns);
    }

    public static final ResourceKey<Biome> ICE_AGE = registerBiome("ice_age");
    public static final ResourceKey<Biome> FROZEN_CAVES = registerBiome("frozen_caves");
    private static ResourceKey<Biome> registerBiome(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(UnEndurable.MODID, name));
    }

    public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier)
    {
        return BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
    }
}
