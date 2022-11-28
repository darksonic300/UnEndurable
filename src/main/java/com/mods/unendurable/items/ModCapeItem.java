package com.mods.unendurable.items;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.world.item.Vanishable;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Stack;

public class ModCapeItem extends CapeLayer implements Vanishable {

    public ModCapeItem(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> p_116602_) {
        super(p_116602_);
    }

    @Override
    public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, AbstractClientPlayer p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        super.render(p_225628_1_, p_225628_2_, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_7_, p_225628_8_, p_225628_9_, p_225628_10_);
    }
}
