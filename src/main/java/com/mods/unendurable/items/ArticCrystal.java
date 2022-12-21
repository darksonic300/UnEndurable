package com.mods.unendurable.items;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UEEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ArticCrystal extends SimpleFoiledItem {
    public ArticCrystal(Item.Properties tab) {
        super(tab);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return super.shouldCauseReequipAnimation(oldStack, newStack, false);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if(!player.getCooldowns().isOnCooldown(RegistryHandler.ARCTIC_CRYSTAL.get())){
            if(hand == InteractionHand.MAIN_HAND || hand == InteractionHand.OFF_HAND) {
                livingEntity.addEffect(new MobEffectInstance(UEEffects.FREEZE.get(), 80, 2, false, false, false));
                player.getCooldowns().addCooldown(this, 50);
                player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE);
                player.awardStat(Stats.ITEM_USED.get(this));
                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }
            }
        }
        return super.interactLivingEntity(itemStack, player, livingEntity, hand);
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.literal("Right click on an Entity to apply the Freeze Effect.").withStyle(ChatFormatting.YELLOW));
        } else {
            components.add(Component.literal("Press SHIFT for more info.").withStyle(ChatFormatting.DARK_AQUA));
        }

        super.appendHoverText(stack, level, components, flag);
    }
}
