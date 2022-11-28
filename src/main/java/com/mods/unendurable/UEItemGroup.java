package com.mods.unendurable;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

public class UEItemGroup extends ItemGroup {

    private final Supplier<ItemStack> displayStack;

    public static final UEItemGroup ALL = new UEItemGroup("all", () -> new ItemStack(RegistryHandler.PHANTOM_CLOTH.get()));

    protected UEItemGroup(String label, Supplier<ItemStack> displayStack) {
        super(label);
        this.displayStack = displayStack;
    }

    @Override
    public ItemStack createIcon() { return displayStack.get(); }
}
