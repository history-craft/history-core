package com.historycraft.jei;

import com.historycraft.config.HistoryCoreConfig;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JEICleanup {

    public static void init () {
        if (HistoryCoreConfig.jeiCleanup) {
            Collection<ItemStack> allIngredients = JEIAddonPlugin.itemRegistry.getAllIngredients(VanillaTypes.ITEM);
            final List<ItemStack> itemToRemove = new ArrayList<>();
            allIngredients.forEach(itemStack -> {
                if (!"gregtech".equals(ForgeHooks.getDefaultCreatorModId(itemStack))) {
                    itemToRemove.add(itemStack);
                }
            });
            JEIAddonPlugin.itemRegistry.removeIngredientsAtRuntime(VanillaTypes.ITEM, itemToRemove);
        }
    }
}
