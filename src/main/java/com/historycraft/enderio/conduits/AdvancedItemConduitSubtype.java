package com.historycraft.enderio.conduits;

import crazypants.enderio.conduits.conduit.ItemConduitSubtype;

import javax.annotation.Nonnull;

public class AdvancedItemConduitSubtype extends ItemConduitSubtype {

    final private @Nonnull String unlocalisedName;

    final private @Nonnull String modelLocation;

    public AdvancedItemConduitSubtype(@Nonnull String baseName, @Nonnull String iconKey) {
        super(baseName, iconKey);
        this.unlocalisedName = "historycore." + baseName;
        this.modelLocation = iconKey;
    }

    public @Nonnull String getUnlocalisedName() {
        return unlocalisedName;
    }

    public @Nonnull String getModelLocation() {
        return modelLocation;
    }

}
