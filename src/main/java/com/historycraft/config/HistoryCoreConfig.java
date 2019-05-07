package com.historycraft.config;

import com.historycraft.HistoryCore;
import net.minecraftforge.common.config.Config;

@Config(modid = HistoryCore.MODID)
public class HistoryCoreConfig {

    @Config.Comment("Perform a dynamic cleanup in duplicated items on JEI [Default : false]")
    public static boolean jeiCleanup = false;

}
