package com.historycraft.config;

import gregtech.api.GTValues;
import scala.actors.threadpool.Arrays;

public class OreDictUnificationConfigHandler extends ConfigHandler<OreDictUnificationConfig> {

    public static OreDictUnificationConfig oreDictUnificationConfig;

    public OreDictUnificationConfigHandler() {
        super("oredict-unification", OreDictUnificationConfig.class);
    }

    @Override
    public void readConfig(OreDictUnificationConfig jeiCleanupConfig) {
        oreDictUnificationConfig = jeiCleanupConfig;
    }

    @Override
    public OreDictUnificationConfig createDefaultFile() {
        OreDictUnificationConfig jeiCleanupConfig = new OreDictUnificationConfig();
        jeiCleanupConfig.setModOrderId(Arrays.asList( new String[] {"minecraft", GTValues.MODID}));

        jeiCleanupConfig.addOreDict(new OreDictUnificationConfig.OreDict().setOreDict("plate"));
        jeiCleanupConfig.addOreDict(new OreDictUnificationConfig.OreDict().setOreDict("rod"));
        jeiCleanupConfig.addOreDict(new OreDictUnificationConfig.OreDict().setOreDict("ingot"));
        jeiCleanupConfig.addOreDict(new OreDictUnificationConfig.OreDict().setOreDict("circuit"));

        return jeiCleanupConfig;
    }
}
