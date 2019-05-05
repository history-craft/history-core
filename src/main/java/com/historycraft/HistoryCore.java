package com.historycraft;


import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = HistoryCore.MODID, name = HistoryCore.NAME, version = HistoryCore.VERSION,
        dependencies = "required-after:gregtech;after:immersiveengineering")
public class HistoryCore {

    public static final String MODID = "historycore";
    public static final String NAME = "History Core";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("history-craft first message");
    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Recipes.changeCrusherRecipes();
    }
}
