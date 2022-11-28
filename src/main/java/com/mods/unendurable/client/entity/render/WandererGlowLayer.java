package com.mods.unendurable.client.entity.render;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.model.WandererModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class WandererGlowLayer<T extends LivingEntity> extends AbstractEyesLayer<T, WandererModel<T>> {
    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer_glow2.png"));

    public WandererGlowLayer(IEntityRenderer<T, WandererModel<T>> p_i50939_1_) {
        super(p_i50939_1_);
    }

    public RenderType getRenderType() {
        return RENDER_TYPE;
    }
}