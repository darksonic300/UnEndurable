package com.mods.unendurable;

import com.mods.unendurable.entities.Wanderer;
import com.mods.unendurable.items.ModArmorItem;
import com.mods.unendurable.items.ModArmorMaterial;

import com.mods.unendurable.world.feature.WarmheartTreeGrower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UnEndurable.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UnEndurable.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UnEndurable.MODID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, UnEndurable.MODID);


    //Entity registration
    public static final RegistryObject<EntityType<Wanderer>> WANDERER = ENTITIES.register("icy_wanderer",
            () -> EntityType.Builder.<Wanderer>of(Wanderer::new, MobCategory.MONSTER)
            .sized(1f, 2f).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Wanderer::new)
            .build(new ResourceLocation(UnEndurable.MODID, "icy_wanderer").toString()));


    //Block registration and properties
    public static final RegistryObject<Block> WARMHEART_LOG = BLOCKS.register("warmheart_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIP_WARMHEART_LOG = BLOCKS.register("stripped_warmheart_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> WARMHEART_PLANKS = BLOCKS.register("warmheart_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS)));

    public static final RegistryObject<Block> WARMHEART_LEAVES = BLOCKS.register("warmheart_leaves",() -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES)));

    public static final RegistryObject<Block> FROZEN_STONE = BLOCKS.register("frozen_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS).strength(1.5F, 6.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ICE_CARPET = BLOCKS.register("ice_carpet", () -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.ICE)));

    public static final RegistryObject<Block> WARMHEART_SAPLING = BLOCKS.register("warmheart_sapling",
            () -> new SaplingBlock(new WarmheartTreeGrower(), Block.Properties.copy(Blocks.SPRUCE_SAPLING)));

    public static final RegistryObject<Block> SNOWMAN_HEAD = BLOCKS.register("snowman_head",
            () -> new FlowerBlock(MobEffects.LUCK, 0, Block.Properties.copy(Blocks.GRASS)));


    //Item registration
    public static final RegistryObject<BlockItem> FROZEN_STONE_ITEM = ITEMS.register("frozen_stone", () -> new BlockItem(FROZEN_STONE.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> ICE_CARPET_ITEM = ITEMS.register("ice_carpet", () -> new BlockItem(ICE_CARPET.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> WARMHEART_LOG_ITEM = ITEMS.register("warmheart_log", () -> new BlockItem(WARMHEART_LOG.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> STRIP_WARMHEART_LOG_ITEM = ITEMS.register("stripped_warmheart_log", () -> new BlockItem(STRIP_WARMHEART_LOG.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> WARMHEART_PLANKS_ITEM = ITEMS.register("warmheart_planks", () -> new BlockItem(WARMHEART_PLANKS.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> WARMHEART_LEAVES_ITEM = ITEMS.register("warmheart_leaves", () -> new BlockItem(WARMHEART_LEAVES.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<BlockItem> WARMHEART_SAPLING_ITEM = ITEMS.register("warmheart_sapling", () -> new BlockItem(WARMHEART_SAPLING.get(), new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<Item> WANDERER_EGG = ITEMS.register("wanderer_egg",
            () -> new ForgeSpawnEggItem(RegistryHandler.WANDERER, 33323, 42424, new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<Item> PHANTOM_CLOTH = ITEMS.register("phantom_cloth", () -> new Item(new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<Item> PHANTOM_PATCH = ITEMS.register("phantom_patch", () -> new Item(new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));

    public static final RegistryObject<Item> PHANTOM_CAPE = ITEMS.register("phantom_cape", () -> new ModArmorItem(ModArmorMaterial.PHANTOM_CLOTH, EquipmentSlot.CHEST, new Item.Properties().tab(UEItemGroup.TAB_UNENDURABLE_TAB)));


    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Wanderer.init();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(WANDERER.get(), Wanderer.setCustomAttributes().build());
    }

    //Biome registration
    /*public static final RegistryObject<Biome> ICE_AGE = BIOMES.register("ice_age",
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
        }*/

}