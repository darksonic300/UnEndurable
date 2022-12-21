package com.mods.unendurable;


import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FreezeEffect extends MobEffect {

    public FreezeEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        if (livingEntity.getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, RegistryHandler.ARCTIC_CRYSTAL.get().getDefaultInstance()),
                    livingEntity.getX(), livingEntity.getY() + livingEntity.getBbHeight(), livingEntity.getZ(),
                    1, 0.1, 0.0, 0.1, 0.1);
        }
        livingEntity.clearFire();
        super.applyEffectTick(livingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
