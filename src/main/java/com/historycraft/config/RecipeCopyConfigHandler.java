package com.historycraft.config;

import com.historycraft.recipe.RecipeHandler;

public class RecipeCopyConfigHandler extends ConfigHandler<RecipeCopyConfig> {

    public RecipeCopyConfigHandler() {
        super("recipe-copy", RecipeCopyConfig.class);
    }

    @Override
    public void readConfig(RecipeCopyConfig file) {
        String[] strings = file.getCrusher().getRemovedByProduct().toArray(new String[0]);
        RecipeHandler.removedByProduct = strings;
    }

    @Override
    public RecipeCopyConfig createDefaultFile() {
        RecipeCopyConfig recipeCopyConfig = new RecipeCopyConfig();
        recipeCopyConfig.setCrusher(new RecipeCopyConfig.Crusher().add("oredict1").add("oredict2").add("oredict3"));
        return recipeCopyConfig;
    }
}
