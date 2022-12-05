package com.mods.unendurable.client.entity.model;

import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.entities.Wanderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class WandererModel extends AnimatedGeoModel<Wanderer> {

        @Override
        public ResourceLocation getModelResource(Wanderer object) {
                return new ResourceLocation(UnEndurable.MODID, "geo/icy_wanderer.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(Wanderer object) {
                return new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer.png");
        }

        public static ResourceLocation getModelResourceGLOW(Wanderer object) {
                return new ResourceLocation(UnEndurable.MODID, "geo/icy_wanderer.geo.json");
        }

        public static ResourceLocation getTextureResourceGLOW(Wanderer object) {
                return new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer.png");
        }

        public static ResourceLocation getGlowTextureResource(Wanderer object) {
                return new ResourceLocation(UnEndurable.MODID, "textures/entity/icy_wanderer_glowing.png");
        }

        @Override
        public ResourceLocation getAnimationResource(Wanderer animatable) {
                return new ResourceLocation(UnEndurable.MODID, "animations/icy_wanderer.idle.json");
        }

        @Override
        public void setLivingAnimations(Wanderer entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
                super.setLivingAnimations(entity, uniqueID, customPredicate);

                IBone head = getAnimationProcessor().getBone("head");

                EntityModelData entityData = (EntityModelData)customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
                head.setRotationX(entityData.headPitch * Mth.DEG_TO_RAD);
                head.setRotationY(entityData.netHeadYaw * Mth.DEG_TO_RAD);
        }
}