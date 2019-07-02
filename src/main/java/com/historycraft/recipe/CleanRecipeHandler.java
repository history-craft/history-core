package com.historycraft.recipe;

import com.historycraft.HistoryCore;
import com.historycraft.api.utils.HistoryCraftUtils;
import com.historycraft.config.HistoryCoreConfig;
import com.historycraft.config.RecipeRemoveConfig;
import com.historycraft.config.RecipeRemoveConfigHandler;
import com.historycraft.jei.JEIAddonPlugin;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class CleanRecipeHandler {

    private static List<ResourceLocation> removingRecipes = new ArrayList<>();
    private static RecipeRemoveConfig recipeRemoveConfig = RecipeRemoveConfigHandler.getConfig();
    private static HistoryCraftUtils utils = HistoryCraftUtils.getInstance();

    public static void doCleanUp(RegistryEvent.Register<IRecipe> event) {
        HistoryCore.logger.info("--------------------------------------doCleanUp------------------------------------");
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) event.getRegistry();
        for (Map.Entry<ResourceLocation, IRecipe> map : event.getRegistry().getEntries()) {
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

            if (recipeOutput != null && !recipeOutput.isEmpty()){
                if (utils.isOreDictByItemStack(recipeOutput,"ingot", "nugget") && !utils.isOreDictByItemStack(recipeOutput,"ingotDouble")) {
                    removingRecipes.add(map.getKey());
                    HistoryCore.logger.info("removed recipe by oredict from: {} ", recipeOutput);
                }
            } else {
                HistoryCore.logger.info("Invalid outputstack {} ", recipeOutput);
            }

        }
        removingRecipes.forEach(recipe -> {
            HistoryCore.logger.info("removed sharped {} ", recipe);
            modRegistry.remove(recipe);
        });
    }

}
