package com.historycraft.jei;

import com.historycraft.unification.OreDictUnificationHandler;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

public class JEICleanup {


    public static void applyCleanup() {
        OreDictUnificationHandler.getInstance().cleanDuplicatedItems();
        cleanByConfig();
    }


    public static void cleanByConfig() {
        for(ItemStack itemStack : JEIAddonPlugin.itemRegistry.getAllIngredients(VanillaTypes.ITEM)){

        }
    }
}
