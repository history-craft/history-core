package com.historycraft.enderio.conduits.power;

import com.historycraft.HistoryCore;
import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.conduit.IConduitTexture;
import crazypants.enderio.base.render.registry.TextureRegistry;
import crazypants.enderio.conduits.render.ConduitTexture;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class AdvancedPowerConduit {


    public static final
    String ICON_KEY = "blocks/advanced_power_conduit";
    public static final
    String ICON_CORE_KEY = "blocks/advanced_power_conduit_core";

    static final Map<String, IConduitTexture> ICONS = new HashMap<>();

    static final String[] POSTFIX = new String[]{"_tungstensteel", "_hssg", "_naquadah", "_naquadah_alloy"};

    public static void registerTextures() {
        int i = 0;
        for (String pf : POSTFIX) {
            ICONS.put(ICON_KEY + pf, new ConduitTexture(TextureRegistry.registerTexture(EnderIO.DOMAIN+ ":" + ICON_KEY,false), ConduitTexture.arm(i)));
            ICONS.put(ICON_CORE_KEY + pf, new ConduitTexture(TextureRegistry.registerTexture(EnderIO.DOMAIN + ":" +"blocks/advanced_conduit_core_0",false), ConduitTexture.core(i++)));
        }
    }
}
