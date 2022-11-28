package com.mods.unendurable.items;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.Objects;

public class ModArmorItem extends ArmorItem implements Vanishable {

    private static final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT1_MAP =
            new ImmutableMap.Builder<ArmorMaterial, MobEffect>()
                    .put(ModArmorMaterial.PHANTOM_CLOTH, MobEffects.SLOW_FALLING)
                    .build();

    private static final Map<ArmorMaterial, MobEffect> MATERIAL_TO_EFFECT2_MAP =
            new ImmutableMap.Builder<ArmorMaterial, MobEffect>()
                    .put(ModArmorMaterial.PHANTOM_CLOTH, MobEffects.MOVEMENT_SPEED)
                    .build();

    public ModArmorItem(ModArmorMaterial p_i48534_1_, EquipmentSlot p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if(!player.getInventory().armor.get(2).isEmpty()) {
            evaluateArmorEffects(player);
        }

        super.onArmorTick(stack, world, player);
    }

    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffect> entry : MATERIAL_TO_EFFECT1_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
        for (Map.Entry<ArmorMaterial, MobEffect> entry : MATERIAL_TO_EFFECT2_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial, MobEffect mapStatusEffect) {
        boolean hasPlayerEffect = !Objects.equals(player.getEffect(mapStatusEffect), null);

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect, 50, 0, true, false, false));
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        ArmorItem chestplate = ((ArmorItem) player.getInventory().armor.get(2).getItem());

        return chestplate.getMaterial() == material;
    }
}
