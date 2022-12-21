package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import com.mods.unendurable.entities.Wanderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class WandererRender extends GeoEntityRenderer<Wanderer> {

public WandererRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WandererModel());
        this.addLayer(new WandererGlowLayer<>(this, WandererModel::getTextureResourceGLOW, WandererModel::getModelResourceGLOW, RenderType::eyes));
        this.shadowRadius = 0.3f;
        }

@Override
public ResourceLocation getTextureLocation(Wanderer instance) {
        return new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer.png");
        }

@Override
public RenderType getRenderType(Wanderer animatable, float partialTicks, PoseStack stack,
                                @Nullable MultiBufferSource renderTypeBuffer,
                                @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                ResourceLocation textureLocation) {
        stack.scale(1.0f, 1.0f, 1.0f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
        }
}
