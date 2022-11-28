package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import com.mods.unendurable.entities.Wanderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class WandererRender extends MobRenderer<Wanderer, WandererModel<Wanderer>> {
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
}
