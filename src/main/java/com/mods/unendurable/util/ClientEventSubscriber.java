package com.mods.unendurable.util;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.render.WandererRender;
import com.mods.unendurable.items.ModArmorMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = UnEndurable.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(RegistryHandler.WANDERER.get(), WandererRender::new);
    }

    @SubscribeEvent
    public void armorInteraction(ItemStack itemStack, PlayerEntity player) {
        World world = player.world;
        boolean chestOn = false;

        Iterable<ItemStack> armor = player.getArmorInventoryList();
        /*for (ItemStack slot:armor
             ) {
            if (slot.getItem().equals(ModArmorMaterial.PHANTOM_CLOTH)) {
                player.setInvisible(true);
            }
        }*/
        if (player.inventory.armorItemInSlot(2).getItem().equals(ModArmorMaterial.PHANTOM_CLOTH))
        {
            player.setInvisible(true);
            player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 1, true, false, false));
        }

        if (player.inventory.armorItemInSlot(2).getItem().equals(RegistryHandler.PHANTOM_CAPE))
        {
            player.setInvisible(true);
            player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 1, true, false, false));
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null && !player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(2).getItem().equals(RegistryHandler.PHANTOM_CAPE)) {
                player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 1, true, false, false));
                player.setInvisible(true);
            }
        } else if (e.phase == TickEvent.Phase.END) {
            PlayerEntity player = Minecraft.getInstance().player;
            if (player != null) {
                EffectInstance eff = player.getActivePotionEffect(Effects.SLOW_FALLING);
                if (eff != null && eff.getAmplifier() == 1) {
                    player.removePotionEffect(Effects.SLOW_FALLING);
                    player.setInvisible(false);
                }
            }
        }
    }
}
