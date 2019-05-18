package com.historycraft.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIAddonPlugin implements IModPlugin {

    public static IIngredientRegistry itemRegistry;
    public static IJeiHelpers jeiHelpers;
    public static IRecipeRegistry recipeRegistry;
    public static IIngredientHelper<ItemStack> ingredientHelper;
    public static IIngredientBlacklist iIngredientBlacklist;
    public static IModRegistry modRegistry;
    public static IJeiRuntime jeiRuntime;

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
        JEICleanup.applyCleanup();
    }

}
