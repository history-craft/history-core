package com.historycraft.jei;

import com.historycraft.unification.OreDictUnificationHandler;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIAddonPlugin implements IModPlugin {

    public static IIngredientRegistry itemRegistry;
    public static IJeiHelpers jeiHelpers;
    public static IRecipeRegistry recipeRegistry;
    public static IIngredientHelper<ItemStack> ingredientHelper;
    public static IIngredientBlacklist iIngredientBlacklist;
    public static IModRegistry modRegistry;
    public static IJeiRuntime jeiRuntime;

    public static List<ItemStack> itemsToRemove = new ArrayList<>();

    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();
        itemRegistry = registry.getIngredientRegistry();
        iIngredientBlacklist = jeiHelpers.getIngredientBlacklist();
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime iJeiRuntime) {
        recipeRegistry = iJeiRuntime.getRecipeRegistry();
        jeiRuntime = iJeiRuntime;
        OreDictUnificationHandler.cleanDuplicatedItems();
        if (!itemsToRemove.isEmpty()) {
            JEIAddonPlugin.itemRegistry.removeIngredientsAtRuntime(VanillaTypes.ITEM, itemsToRemove);
        }
    }

    public static void removeItem(ItemStack itemStack) {
        itemsToRemove.add(itemStack);
    }

}
