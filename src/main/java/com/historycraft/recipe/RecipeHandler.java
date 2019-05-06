package com.historycraft.recipe;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import com.historycraft.HistoryCore;
import gnu.trove.map.TObjectIntMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class RecipeHandler {

    public static String [] removedByProduct = new String[]{};

    public static void changeRecipes() {
        changeCrusherRecipes();
        changeRollingMachine();
    }

    private static void changeRollingMachine() {
//        List<IRecipe> recipes = RecipesMachine.getInstance().getRecipes(TileRollingMachine.class);
//        RecipeMaps.BENDER_RECIPES;
    }

    private static void changeCrusherRecipes() {
        HistoryCore.logger.info("removing recipes");
        List<Recipe> recipesToChange = new ArrayList<>();
        RecipeMaps.MACERATOR_RECIPES.getRecipeList().forEach(recipe -> {
            NonNullList<ItemStack> outputs = recipe.getOutputs();
            TObjectIntMap<ItemStack> chancedOutputs = recipe.getChancedOutputs();

            recipe.getInputs().forEach(input ->{
                for (int x = 0; x <input.getIngredient().getMatchingStacks().length; x++) {
                    ItemStack itemStack = input.getIngredient().getMatchingStacks()[x];
                    CrusherRecipe.removeRecipesForInput(itemStack);
                    CrusherRecipe newRecipe = CrusherRecipe.addRecipe(outputs.get(0), itemStack, recipe.getEUt() * 4);
                    for (int y = 1; y < outputs.size(); y++){
                        ItemStack stack = outputs.get(y);
                        if (isEnableByProduct(stack)) {
                            newRecipe.addToSecondaryOutput(stack, 1.0F);
                        } else {
                            recipesToChange.add(recipe);
                        }
                    }
                    for (Object stack : chancedOutputs.keys()) {
                        if (isEnableByProduct((ItemStack) stack)) {
                            float chance = (float) chancedOutputs.get(stack) / 10000;
                            newRecipe.addToSecondaryOutput(stack, chance);
                        } else {
                            recipesToChange.add(recipe);
                        }
                    }
                }
            });
        });
        recipesToChange.forEach(recipe -> {
            RecipeMaps.MACERATOR_RECIPES.removeRecipe(recipe);

            SimpleRecipeBuilder recipeBuilder = RecipeMaps.MACERATOR_RECIPES.recipeBuilder()
                    .inputsIngredients(recipe.getInputs())
                    .outputs(recipe.getOutputs())
                    .duration(recipe.getDuration())
                    .EUt(recipe.getEUt());

            TObjectIntMap<ItemStack> chancedOutputs = recipe.getChancedOutputs();
            for (Object stack : chancedOutputs.keys()) {
                ItemStack itemStack = (ItemStack) stack;
                if (isEnableByProduct(itemStack)) {
                    recipeBuilder.chancedOutput(itemStack, chancedOutputs.get(itemStack));
                }
            }
            recipeBuilder.buildAndRegister();
        });
    }

    private static boolean isEnableByProduct(ItemStack itemStack) {
        int[] oreIDs = OreDictionary.getOreIDs(itemStack);
        for (int id: oreIDs) {
            String name = OreDictionary.getOreName(id);
            for (String product : removedByProduct){
                if (name.equalsIgnoreCase(product)) {
                    return false;
                }
            }
        }
        return true;
    }
}
