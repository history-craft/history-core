package com.historycraft.recipe;

import com.historycraft.HistoryCore;
import com.historycraft.api.utils.HistoryCraftUtils;
import com.historycraft.config.RecipeRemoveConfig;
import com.historycraft.config.RecipeRemoveConfigHandler;
import com.historycraft.jei.JEIAddonPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class CleanRecipeHandler {

    private static List<ResourceLocation> removingRecipes = new ArrayList<>();
    private static RecipeRemoveConfig recipeRemoveConfig = RecipeRemoveConfigHandler.getConfig();

    public static void doCleanUp() {
        for (Map.Entry<ResourceLocation, IRecipe> map : RecipeHandler.recipes) {
            IRecipe iRecipe  = map.getValue();
            ItemStack recipeOutput = iRecipe.getRecipeOutput();
            recipeRemoveConfig.getMods().forEach(mod -> {
                AtomicBoolean toRemove = new AtomicBoolean(false);
                if (mod.getModId().equals(ForgeHooks.getDefaultCreatorModId(recipeOutput))){
                    toRemove.set(true);
                    if (!mod.getWhiteList().isEmpty()) {
                        mod.getWhiteList().forEach(itemName -> {
                            if (HistoryCraftUtils.getInstance().isItemName(itemName, recipeOutput)) {
                                toRemove.set(false);
                            }
                        });
                    }
                }
                if (toRemove.get()) {
                    HistoryCore.logger.info("removed recipe from: {} ", recipeOutput);
                    removingRecipes.add(map.getKey());
                    if (mod.isHide()) {
                        JEIAddonPlugin.removeItem(recipeOutput);
                    }
                }
            });
        }
        removingRecipes.forEach(recipe -> RegistryManager.ACTIVE.getRegistry(GameData.RECIPES).remove(recipe));
    }

}
