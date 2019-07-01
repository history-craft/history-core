package com.historycraft.config;

import com.historycraft.HistoryCore;
import net.minecraftforge.common.config.Config;

@Config(modid = HistoryCore.MODID)
public class HistoryCoreConfig {

    @Config.Comment("Perform a dynamic cleanup in duplicated items on JEI [Default : false]")
    public static boolean jeiCleanup = false;

    @Config.Comment("Update crusher recipes using modpack items [Default : true]")
    public static boolean changeCrusherRecipes = true;

    @Config.Comment("Update rolling machine recipes using modpack items [Default : true]")
    public static boolean changeRollingMachineRecipes = true;

    @Config.Comment("Update rolling lathe recipes using modpack items [Default : true]")
    public static boolean changeLatheRecipes = true;

    @Config.Comment("Update cutting machine recipes using modpack items [Default : true]")
    public static boolean changeSawRecipes = true;

    @Config.Comment("Remove metal recipes from crafing table (like ingot and nugget) [Default : true]")
    public static boolean removeMetalsFromCraftingTable = true;

}
