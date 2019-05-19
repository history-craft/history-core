package com.historycraft;


import com.historycraft.config.OreDictUnificationConfigHandler;
import com.historycraft.config.RecipeCopyConfigHandler;
import com.historycraft.config.RecipeRemoveConfigHandler;
import com.historycraft.recipe.RecipeHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

@Mod(modid = HistoryCore.MODID, name = HistoryCore.NAME, version = HistoryCore.VERSION,
        dependencies = "required-after:gregtech;after:immersiveengineering")
public class HistoryCore {

    public static final String MODID = "historycore";
    public static final String NAME = "History Core";
    public static final String VERSION = "1.1";

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        RecipeCopyConfigHandler recipeCopyConfigHandler = new RecipeCopyConfigHandler();
        recipeCopyConfigHandler.init();

        OreDictUnificationConfigHandler jeiCleanupConfigHandler = new OreDictUnificationConfigHandler();
        jeiCleanupConfigHandler.init();

        RecipeRemoveConfigHandler recipeRemoveConfigHandler = new RecipeRemoveConfigHandler();
        recipeRemoveConfigHandler.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        RecipeHandler.recipes = ForgeRegistries.RECIPES.getEntries();
        RecipeHandler.changeRecipes();
    }
}
