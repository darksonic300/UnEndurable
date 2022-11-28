package com.mods.unendurable;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class UEItemGroup {

    public static CreativeModeTab TAB_UNENDURABLE_TAB;

    public static void load() {
        TAB_UNENDURABLE_TAB = new CreativeModeTab("UnEndurable") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(RegistryHandler.PHANTOM_CLOTH.get());
            }

            @OnlyIn(Dist.CLIENT)
            public boolean hasSearchBar() {
                return false;
            }
        };
    }
}
