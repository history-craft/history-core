package com.historycraft.config;

import com.google.gson.JsonObject;
import com.historycraft.jei.JEICleanup;

public class JEIConfigHandler extends ConfigHandler {

    public JEIConfigHandler() {
        super("jei");
    }

    @Override
    protected void doConfig() {
        JEICleanup.orderModId = readArrayProperty("orderModId");
        JEICleanup.removeDuplicates = readArrayProperty("removeDuplicates");
    }

    @Override
    public JsonObject createDefaultFile() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("orderModId",copyFromArray(JEICleanup.orderModId));
        jsonObject.add("removeDuplicates",copyFromArray(JEICleanup.removeDuplicates));
        return jsonObject;
    }
}
