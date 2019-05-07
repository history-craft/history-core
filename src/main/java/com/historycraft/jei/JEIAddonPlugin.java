package com.historycraft.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;

@JEIPlugin
public class JEIAddonPlugin implements IModPlugin {

    public static IIngredientRegistry itemRegistry;
    public static IJeiHelpers jeiHelpers;

    @Override
    public void register(IModRegistry registry) {
        jeiHelpers = registry.getJeiHelpers();
        itemRegistry = registry.getIngredientRegistry();
        JEICleanup.init();
    }

}
