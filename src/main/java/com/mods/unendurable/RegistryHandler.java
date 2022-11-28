package com.mods.unendurable;

import com.mods.unendurable.blocks.WarmheartLeaves;
import com.mods.unendurable.blocks.WarmheartLog;
import com.mods.unendurable.blocks.WarmheartPlanks;
import com.mods.unendurable.entities.Wanderer;
import com.mods.unendurable.items.ModArmorItem;
import com.mods.unendurable.items.ModArmorMaterial;
import com.mods.unendurable.items.ModSpawnEggItem;
import com.mods.unendurable.world.biome.ModConfiguredSurfaceBuilders;
import com.mods.unendurable.world.feature.WarmheartTree;
import com.mods.unendurable.world.gen.ModConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UnEndurable.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UnEndurable.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, UnEndurable.MODID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, UnEndurable.MODID);
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACES = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, UnEndurable.MODID);


    //Entity registration
    public static final RegistryObject<EntityType<Wanderer>> WANDERER = ENTITIES.register("icy_wanderer", () -> EntityType.Builder.create(Wanderer::new, EntityClassification.MONSTER)
            .size(1f, 2f)
            .build(new ResourceLocation(UnEndurable.MODID, "icy_wanderer").toString()));


    //Block registration and properties
    public static final RegistryObject<Block> WARMHEART_LOG = BLOCKS.register("warmheart_log", () -> new WarmheartLog(3,4));
            //() -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIP_WARMHEART_LOG = BLOCKS.register("stripped_warmheart_log", () -> new WarmheartLog(3,4));

    public static final RegistryObject<Block> WARMHEART_PLANKS = BLOCKS.register("warmheart_planks", () -> new WarmheartPlanks(3,4));

    public static final RegistryObject<Block> WARMHEART_LEAVES = BLOCKS.register("warmheart_leaves",() -> new WarmheartLeaves(5,6));

    public static final RegistryObject<Block> WARMHEART_SAPLING = BLOCKS.register("warmheart_sapling",
            () -> new SaplingBlock(new WarmheartTree(), AbstractBlock.Properties.from(Blocks.SPRUCE_SAPLING)));

    public static final RegistryObject<Block> SNOWMAN_HEAD = BLOCKS.register("snowman_head",
            () -> new FlowerBlock(Effects.LUCK, 0, AbstractBlock.Properties.from(Blocks.GRASS)));


    //Item registration
    public static final RegistryObject<BlockItem> WARMHEART_LOG_ITEM = ITEMS.register("warmheart_log", () -> new BlockItem(WARMHEART_LOG.get(), new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<BlockItem> STRIP_WARMHEART_LOG_ITEM = ITEMS.register("stripped_warmheart_log", () -> new BlockItem(STRIP_WARMHEART_LOG.get(), new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<BlockItem> WARMHEART_PLANKS_ITEM = ITEMS.register("warmheart_planks", () -> new BlockItem(WARMHEART_PLANKS.get(), new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<BlockItem> WARMHEART_LEAVES_ITEM = ITEMS.register("warmheart_leaves", () -> new BlockItem(WARMHEART_LEAVES.get(), new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<BlockItem> WARMHEART_SAPLING_ITEM = ITEMS.register("warmheart_sapling", () -> new BlockItem(WARMHEART_SAPLING.get(), new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<ModSpawnEggItem> WANDERER_EGG = ITEMS.register("wanderer_egg", () -> new ModSpawnEggItem(WANDERER, 0xFFFFFF, 0xA8FFFA, new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<Item> PHANTOM_CLOTH = ITEMS.register("phantom_cloth", () -> new Item(new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<Item> PHANTOM_PATCH = ITEMS.register("phantom_patch", () -> new Item(new Item.Properties().group(UEItemGroup.ALL)));

    public static final RegistryObject<Item> PHANTOM_CAPE = ITEMS.register("phantom_cape", () -> new ModArmorItem(ModArmorMaterial.PHANTOM_CLOTH, EquipmentSlotType.CHEST, new Item.Properties().group(UEItemGroup.ALL)));


    //Biome registration
    public static final RegistryObject<Biome> ICE_AGE = BIOMES.register("ice_age",
            () -> makeIceAgeBiome(() -> ModConfiguredSurfaceBuilders.ICE_SURFACE, 0.1f, 0.1f));

    private static Biome makeIceAgeBiome(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
            MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
            mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                    new MobSpawnInfo.Spawners(RegistryHandler.WANDERER.get(), 50, 4, 10));
            mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
                    new MobSpawnInfo.Spawners(EntityType.VEX,  100, 4, 10));
            BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                    (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);

            biomegenerationsettings$builder.withStructure(StructureFeatures.MINESHAFT);
            biomegenerationsettings$builder.withStructure(StructureFeatures.BURIED_TREASURE);
        biomegenerationsettings$builder.withStructure(StructureFeatures.IGLOO);
        biomegenerationsettings$builder.withStructure(StructureFeatures.PILLAGER_OUTPOST);

            DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationsettings$builder);

            DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationsettings$builder);
            DefaultBiomeFeatures.withMonsterRoom(biomegenerationsettings$builder);
            DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationsettings$builder);
            DefaultBiomeFeatures.withOverworldOres(biomegenerationsettings$builder);
            DefaultBiomeFeatures.withClayDisks(biomegenerationsettings$builder);

            biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.LAKES, Features.LAKE_WATER);

            biomegenerationsettings$builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, ModConfiguredFeatures.WARMHEART
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(20, 0.5f, 1))));

        DefaultBiomeFeatures.withFrozenTopLayer(biomegenerationsettings$builder);

            return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).category(Biome.Category.FOREST).depth(depth).scale(scale)
                    .temperature(0F).downfall(1.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(16777215)
                            .setFogColor(16777215).withSkyColor(7633547).withFoliageColor(16777215).withGrassColor(14483455)
                            .setParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.04f)).withSkyColor(7633547)
                            .build())
                    .withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
        }

}