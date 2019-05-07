package com.historycraft.recipe;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import com.historycraft.HistoryCore;
import gnu.trove.map.TObjectIntMap;
import gregtech.api.GTValues;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import zmaster587.advancedRocketry.tile.multiblock.machine.TileRollingMachine;
import zmaster587.libVulpes.interfaces.IRecipe;
import zmaster587.libVulpes.recipe.RecipesMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeHandler {

    public static String [] removedByProduct = new String[]{};
    public static Map<FluidStack, Integer> lubricants = new HashMap<>();

    public static void changeRecipes() {
        changeCrusherRecipes();
        changeRollingMachine();
    }

    private static void addRollingMachineRecipe(String materialName, String input, String output, int multiplier) {
        String inputName = input + materialName;
        String outputName= output + materialName;

        if (OreDictionary.doesOreNameExist(outputName)){

            ItemStack outputStack = null;
            if (OreDictionary.getOres(outputName).isEmpty()) {
                HistoryCore.logger.info("can't find stack for {} ", outputName);
                return;
            }

            //find greg itemstack first
            NonNullList<ItemStack> plateStacks = OreDictionary.getOres(outputName);
            for (ItemStack itemStack: plateStacks) {
                if (GTValues.MODID.equals(ForgeHooks.getDefaultCreatorModId(itemStack))){
                    outputStack = itemStack;
                }
            }
            if (outputStack == null) {
                outputStack = OreDictionary.getOres(outputName).get(0);
            }
            int power = 300;
            int time = 200;

            Material ingot = Material.MATERIAL_REGISTRY.getObject(materialName.toLowerCase());

            if (ingot != null) {
                time = (int) ingot.getAverageMass();
                power = (int) ingot.getAverageMass() * 2;
            }

            outputStack.setCount(multiplier);

            for (FluidStack fluidStack: lubricants.keySet()) {
                int divisor = lubricants.get(fluidStack);
                RecipesMachine.getInstance().addRecipe(TileRollingMachine.class, outputStack, time / divisor, power, inputName, fluidStack);
            }

        }
    }

    private static void removeOldRecipes(Class clazz, String name) {
        List<IRecipe> toRemove = new ArrayList<>();
        List<IRecipe> iRecipes = RecipesMachine.getInstance().recipeList.get(clazz);
        for (IRecipe iRecipe : iRecipes) {
            boolean added = false;
            for (ItemStack stack: iRecipe.getOutput()){
                List<String> oreDicts = getOreDictByItemStack(stack);
                for(String oreDict: oreDicts) {
                    if (oreDict.startsWith(name)) {
                        toRemove.add(iRecipe);
                        added=true;
                        break;
                    }
                }
                if(added); break;
            }
        }
        if (!toRemove.isEmpty()) {
            iRecipes.removeAll(toRemove);
        }
    }

    private static void changeRollingMachine() {

        removeOldRecipes(TileRollingMachine.class, "plate");

        lubricants.put(new FluidStack(FluidRegistry.WATER, 1000), 1);
        lubricants.put(Materials.Lubricant.getFluid(100), 4);

        for(String ingotName: OreDictionary.getOreNames()){
            if(ingotName.startsWith("ingot")){

                if (OreDictionary.getOres(ingotName).isEmpty()) {
                    HistoryCore.logger.info("can't find stack for ingot {} ", ingotName);
                    continue;
                }
                String materialName = ingotName.replace("ingot", "");
                addRollingMachineRecipe(materialName,"ingot", "plate", 1);
                addRollingMachineRecipe(materialName,"plate", "foil", 4);
            }
        }
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

    private static List<String> getOreDictByItemStack(ItemStack itemStack) {
        List<String> result = new ArrayList<>();
        int[] oreIDs = OreDictionary.getOreIDs(itemStack);
        for (int id: oreIDs) {
            result.add(OreDictionary.getOreName(id));
        }

        return result;
    }

    private static boolean isEnableByProduct(ItemStack itemStack) {
        for (String name: getOreDictByItemStack(itemStack)) {
            for (String product : removedByProduct){
                if (name.equalsIgnoreCase(product)) {
                    return false;
                }
            }
        }
        return true;
    }
}
