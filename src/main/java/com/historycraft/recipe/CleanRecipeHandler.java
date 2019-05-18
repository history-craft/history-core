package com.historycraft.recipe;

import appeng.core.AppEng;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CleanRecipeHandler {

    private static List<ResourceLocation> removingRecipes = new ArrayList<>();

    public static void doCleanUp() {
        for (Map.Entry<ResourceLocation, IRecipe> map : RecipeHandler.recipes) {
            IRecipe iRecipe  = map.getValue();
            ItemStack recipeOutput = iRecipe.getRecipeOutput();
            if (AppEng.MOD_ID.equals(ForgeHooks.getDefaultCreatorModId(recipeOutput))){
                if (!"appliedenergistics2:drive".equals(recipeOutput.getItem().getRegistryName().toString()))
                   if (!"appliedenergistics2:material:44".equals(recipeOutput.getItem().getRegistryName() + ":"+ recipeOutput.getItemDamage())){
                        removingRecipes.add(map.getKey());
                    }

            }
        }
        removingRecipes.forEach(recipe -> RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(recipe));
    }

}
