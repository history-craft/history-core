package com.historycraft.enderio.conduits.power;

import crazypants.enderio.base.conduit.IConduitTexture;
import crazypants.enderio.base.render.registry.TextureRegistry;
import crazypants.enderio.conduits.render.ConduitTexture;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class AdvancedPowerConduit {


    public static final @Nonnull
    String ICON_KEY = "blocks/advanced_power_conduit";
    public static final @Nonnull
    String ICON_CORE_KEY = "blocks/advanced_power_conduit_core";

    static final Map<String, IConduitTexture> ICONS = new HashMap<>();

    static final String[] POSTFIX = new String[]{"_tungstensteel"};

    static {
        int i = 0;
        for (String pf : POSTFIX) {
            ICONS.put(ICON_KEY + pf, new ConduitTexture(TextureRegistry.registerTexture(ICON_KEY), ConduitTexture.arm(i)));
            ICONS.put(ICON_CORE_KEY + pf, new ConduitTexture(TextureRegistry.registerTexture("blocks/conduit_core_0"), ConduitTexture.core(i++)));
        }
    }
}
