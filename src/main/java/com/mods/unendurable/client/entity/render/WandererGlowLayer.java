package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import com.mods.unendurable.entities.Wanderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class WandererGlowLayer<T extends LivingEntity> extends GeoLayerRenderer<Wanderer> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer_glow2.png"));

    public WandererGlowLayer(WandererRender entityRendererIn) {
        super(entityRendererIn);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Wanderer entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

    }
}