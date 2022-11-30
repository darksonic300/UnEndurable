package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import com.mods.unendurable.entities.Wanderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

import java.util.function.Function;

public class WandererGlowLayer<T extends LivingEntity> extends LayerGlowingAreasGeo<Wanderer> {
    public WandererGlowLayer(GeoEntityRenderer<Wanderer> renderer, Function<Wanderer, ResourceLocation> funcGetCurrentTexture, Function<Wanderer, ResourceLocation> funcGetCurrentModel, Function<ResourceLocation, RenderType> funcGetEmissiveRenderType) {
        super(renderer, funcGetCurrentTexture, funcGetCurrentModel, funcGetEmissiveRenderType);
    }
}