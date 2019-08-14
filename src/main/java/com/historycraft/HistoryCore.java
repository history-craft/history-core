package com.historycraft;


import com.historycraft.api.utils.HistoryCraftUtils;
import com.historycraft.config.OreDictUnificationConfigHandler;
import com.historycraft.config.RecipeCopyConfigHandler;
import com.historycraft.config.RecipeRemoveConfigHandler;
import com.historycraft.recipe.RecipeHandler;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.CrafttweakerImplementationAPI;
import crafttweaker.runtime.CrTTweaker;
import crafttweaker.runtime.IScriptProvider;
import crafttweaker.runtime.ITweaker;
import crafttweaker.runtime.providers.ScriptProviderCascade;
import crafttweaker.runtime.providers.ScriptProviderDirectory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = HistoryCore.MODID, name = HistoryCore.NAME, version = HistoryCore.VERSION,
        dependencies = "required-after:gregtech;after:immersiveengineering;after:techguns;after:advancedrocketry")
public class HistoryCore {

    public static final String MODID = "historycore";
    public static final String NAME = "History Core";
    public static final String VERSION = "1.2.2";

    public static Logger logger;

    @SidedProxy(modId = HistoryCore.MODID, clientSide = "com.historycraft.ClientProxy", serverSide = "com.historycraft.CommonProxy")
    public static CommonProxy proxy;


    public static final ITweaker tweaker = new CrTTweaker();

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


        File fileFromURL = HistoryCraftUtils.getInstance().getFileFromURL("scripts");
        tweaker.setScriptProvider(new ScriptProviderDirectory(fileFromURL));
        tweaker.getOrCreateLoader(new String[]{"history-core"}).setMainName("history-core");
        tweaker.loadScript(false, "history-core");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        RecipeHandler.recipes = ForgeRegistries.RECIPES.getEntries();
        RecipeHandler.changeRecipes();


        //gamb
    }
}
