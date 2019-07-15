package com.historycraft.config;

public class RecipeRemoveConfigHandler extends ConfigHandler<RecipeRemoveConfig> {

    private static RecipeRemoveConfig recipeRemoveConfig;

    public RecipeRemoveConfigHandler() {
        super("recipe-remove", RecipeRemoveConfig.class);
    }

    @Override
    public void readConfig(RecipeRemoveConfig file) {
        recipeRemoveConfig = file;
    }

    @Override
    public RecipeRemoveConfig createDefaultFile() {
        RecipeRemoveConfig defaultFile = RecipeRemoveConfig.create();
        defaultFile.addMod(RecipeRemoveConfig.Mod.create()
                .setModId("mod2whiteList")
                .setHide(true)
                .addToWhiteList("item1")
                .addToWhiteList("item2"));
        defaultFile.addMachineOre("Aluminium");
        return defaultFile;
    }

    public static RecipeRemoveConfig getConfig() {
        return recipeRemoveConfig;
    }
}
