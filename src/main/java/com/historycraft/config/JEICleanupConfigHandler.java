package com.historycraft.config;

import com.historycraft.jei.JEICleanup;
import scala.actors.threadpool.Arrays;

public class JEICleanupConfigHandler extends ConfigHandler<JEICleanupConfig> {

    public JEICleanupConfigHandler() {
        super("jei-cleanup", JEICleanupConfig.class);
    }

    @Override
    public void readConfig(JEICleanupConfig jeiCleanupConfig) {
        JEICleanup.orderModId = jeiCleanupConfig.modOrderId.toArray(new String[0]);
        JEICleanup.removeDuplicates = jeiCleanupConfig.remove.toArray(new String[0]);
    }

    @Override
    public JEICleanupConfig createDefaultFile() {
        JEICleanupConfig jeiCleanupConfig = new JEICleanupConfig();
        jeiCleanupConfig.modOrderId = Arrays.asList(JEICleanup.orderModId);
        jeiCleanupConfig.remove = Arrays.asList(JEICleanup.removeDuplicates);
        return jeiCleanupConfig;
    }
}
