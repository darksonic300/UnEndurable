package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import com.mods.unendurable.entities.Wanderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class WandererRender extends GeoEntityRenderer<Wanderer> {

public WandererRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WandererModel());
        this.addLayer(new WandererGlowLayer(this));
        this.shadowRadius = 0.3f;
        }

@Override
public ResourceLocation getTextureLocation(Wanderer instance) {
        return new ResourceLocation(UnEndurable.MODID, "textures/entities/quail.png");
        }

@Override
public RenderType getRenderType(Wanderer animatable, float partialTicks, PoseStack stack,
                                @Nullable MultiBufferSource renderTypeBuffer,
                                @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                ResourceLocation textureLocation) {
        stack.scale(1.2f, 1.2f, 1.2f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
        }
}

/*public class WandererRender extends MobRenderer<Wanderer, WandererModel<Wanderer>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer.png");

    public WandererRender(EntityRendererManager rendererManagerIn)
    {
        super(rendererManagerIn, new WandererModel<>(), 0f);
        this.addLayer(new WandererGlowLayer(this));
    }

    @Override
    protected int getSkyLight(Wanderer p_239381_1_, BlockPos p_239381_2_) {
        return super.getSkyLight(p_239381_1_, p_239381_2_);
    }

    @Override
    protected int getBlockLight(Wanderer p_225624_1_, BlockPos p_225624_2_) {
        return super.getBlockLight(p_225624_1_, p_225624_2_);
    }

    @Override
    public ResourceLocation getEntityTexture(Wanderer entity)
    {
        return TEXTURE;
    }
}*/
