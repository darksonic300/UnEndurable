package com.mods.unendurable.entities;

import com.mods.unendurable.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;


public class Wanderer extends StrayEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    public Wanderer(EntityType<? extends StrayEntity> p_i50191_1_, World p_i50191_2_) {
        super(p_i50191_1_, p_i50191_2_);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 1.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 10.0D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true ) );
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player)
    {
        return 4 + this.world.rand.nextInt(5);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_STRAY_AMBIENT;
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_STRAY_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_STRAY_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_STRAY_STEP, 0.20F, 0.5F);
    }


    @SubscribeEvent
    public void dropItem(LivingDeathEvent event){
        if (!(event.getEntity() instanceof Wanderer)) {
            return;
        }

        Random random = new Random();

        if (!event.getEntity().world.isRemote) {
            entityDropItem(RegistryHandler.PHANTOM_CLOTH.get(), random.nextInt(3));
        }
    }

   /* @Nullable
    @Override
    public ItemEntity entityDropItem(ItemStack p_70099_1_, float p_70099_2_) {
        if (p_70099_1_.isEmpty()) {
            return null;
        } else if (this.world.isRemote) {
            return null;
        } else {
            ItemEntity itementity = new ItemEntity(this.world, this.getPosX(), this.getPosY() + (double)p_70099_2_, this.getPosZ(), p_70099_1_);
            itementity.setDefaultPickupDelay();
            this.world.addEntity(itementity);
            return itementity;
        }
    }*/

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance p_180481_1_) {
        super.setEquipmentBasedOnDifficulty(p_180481_1_);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animations.icy_wanderer.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100,1));
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100));
            }
            return true;
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<Wanderer>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected boolean isDespawnPeaceful() {
        return true;
    }
}