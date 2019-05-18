package com.historycraft.config;

import com.google.gson.JsonObject;
import com.historycraft.recipe.RecipeHandler;

public class RecipeConfigHandler extends ConfigHandler {

    public RecipeConfigHandler() {
        super("recipe");
    }

    @Override
    protected void doConfig() {
        RecipeHandler.removedByProduct = readArrayProperty("crusherRemovedByProduct");
    }

    @Override
    public JsonObject createDefaultFile() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("crusherRemovedByProduct", copyFromArray(RecipeHandler.removedByProduct));
        return jsonObject;
    }
}
