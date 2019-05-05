package com.historycraft;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import gnu.trove.map.TObjectIntMap;
import gregtech.api.recipes.RecipeMaps;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Recipes {

    public static void changeCrusherRecipes() {
        HistoryCore.logger.info("removing recipes");
        RecipeMaps.MACERATOR_RECIPES.getRecipeList().forEach(recipe -> {
            NonNullList<ItemStack> outputs = recipe.getOutputs();
            TObjectIntMap<ItemStack> chancedOutputs = recipe.getChancedOutputs();

            recipe.getInputs().forEach(input ->{
                for (int x = 0; x <input.getIngredient().getMatchingStacks().length; x++) {
                    ItemStack itemStack = input.getIngredient().getMatchingStacks()[x];
                    CrusherRecipe crusherRecipe = CrusherRecipe.findRecipe(itemStack);
                    if (crusherRecipe != null) {
                        CrusherRecipe.removeRecipesForInput(itemStack);
                        CrusherRecipe newRecipe = CrusherRecipe.addRecipe(outputs.get(0), itemStack, recipe.getEUt() * 4);
                        for (int y = 1; y < outputs.size(); y++){
                            newRecipe.addToSecondaryOutput(outputs.get(y), 1.0F);
                        }
                        for (Object stack : chancedOutputs.keys()) {
                            float chance = (float) chancedOutputs.get(stack) / 10000;
                            newRecipe.addToSecondaryOutput(stack, chance);
                        }
                        HistoryCore.logger.info("replaced {}", itemStack);
                    }
                }
            });
        });
    }
}
