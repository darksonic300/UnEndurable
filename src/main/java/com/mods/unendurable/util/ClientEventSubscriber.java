package com.mods.unendurable.util;

import com.mods.unendurable.RegistryHandler;
import com.mods.unendurable.UnEndurable;
import com.mods.unendurable.client.entity.render.WandererRender;
import com.mods.unendurable.items.ModArmorMaterial;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = UnEndurable.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(RegistryHandler.WANDERER.get(), WandererRender::new);
    }

    @SubscribeEvent
    public void armorInteraction(ItemStack itemStack, Player player) {
        Level world = player.level;
        boolean chestOn = false;

        Iterable<ItemStack> armor = player.getArmorSlots();
        for (ItemStack slot:armor) {
            if (slot.getItem().equals(ModArmorMaterial.PHANTOM_CLOTH)) {
                player.setInvisible(true);
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 1, true, false, false));
            }
            if (slot.getItem().equals(RegistryHandler.PHANTOM_CAPE))
            {
                player.setInvisible(true);
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 1, true, false, false));
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent e) {
        if (e.phase == TickEvent.Phase.START) {
            Player player = Minecraft.getInstance().player;
            Iterable<ItemStack> armor = player.getArmorSlots();
            for (ItemStack slot:armor) {
                if (player != null && !slot.isEmpty() && slot.getItem().equals(RegistryHandler.PHANTOM_CAPE)) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 1, true, false, false));
                    player.setInvisible(true);
                }
            }
        } else if (e.phase == TickEvent.Phase.END) {
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                MobEffectInstance eff = (MobEffectInstance) player.getActiveEffects();
                if (eff != null && eff.getAmplifier() == 1) {
                    player.removeEffectNoUpdate(MobEffects.SLOW_FALLING);
                    player.setInvisible(false);
                }
            }
        }
    }
}
