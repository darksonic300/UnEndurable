package com.mods.unendurable.items;

import com.google.common.collect.ImmutableMap;
import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.items.ModArmorMaterial;
import net.minecraft.enchantment.IArmorVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Objects;

public class ModArmorItem extends ArmorItem implements IArmorVanishable {

    private static final Map<IArmorMaterial, Effect> MATERIAL_TO_EFFECT1_MAP =
            new ImmutableMap.Builder<IArmorMaterial, Effect>()
                    .put(ModArmorMaterial.PHANTOM_CLOTH, Effects.SLOW_FALLING)
                    .build();

    private static final Map<IArmorMaterial, Effect> MATERIAL_TO_EFFECT2_MAP =
            new ImmutableMap.Builder<IArmorMaterial, Effect>()
                    .put(ModArmorMaterial.PHANTOM_CLOTH, Effects.SPEED)
                    .build();

    public ModArmorItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if(!world.isRemote()) {
            if(hasArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }

        super.onArmorTick(stack, world, player);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<IArmorMaterial, Effect> entry : MATERIAL_TO_EFFECT1_MAP.entrySet()) {
            IArmorMaterial mapArmorMaterial = entry.getKey();
            Effect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
        for (Map.Entry<IArmorMaterial, Effect> entry : MATERIAL_TO_EFFECT2_MAP.entrySet()) {
            IArmorMaterial mapArmorMaterial = entry.getKey();
            Effect mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, IArmorMaterial mapArmorMaterial, Effect mapStatusEffect) {
        boolean hasPlayerEffect = !Objects.equals(player.getActivePotionEffect(mapStatusEffect), null);

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addPotionEffect(new EffectInstance(mapStatusEffect, 50, 0, true, false, false));

            // if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            // Uncomment if you wanna damage armor
            // player.inventory.func_234563_a_(DamageSource.MAGIC, 1f);
            // }
        }
    }

    private boolean hasArmorOn(PlayerEntity player) {
        ItemStack chestplate = player.inventory.armorItemInSlot(2);

        return !chestplate.isEmpty();
    }

    private boolean hasCorrectArmorOn(IArmorMaterial material, PlayerEntity player) {
        ArmorItem chestplate = ((ArmorItem)player.inventory.armorItemInSlot(2).getItem());

        return chestplate.getArmorMaterial() == material;
    }
}
