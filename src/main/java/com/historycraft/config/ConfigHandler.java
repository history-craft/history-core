package com.historycraft.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.historycraft.HistoryCore;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class ConfigHandler {

    public final Gson gson = new Gson();
    public static Path modPath = Loader.instance().getConfigDir().toPath().resolve(HistoryCore.MODID);
    public File configFile;

    public ConfigHandler(String fileName) {
        configFile = new File(modPath.toFile(), fileName + ".json");
    }

    public String[] readArrayProperty(String property) {
        List<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(configFile);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(fileReader).getAsJsonObject();
            JsonArray array = jsonObject.getAsJsonArray(property);
            array.forEach(jsonElement -> list.add(jsonElement.getAsString()));
        } catch (Exception ex) {
            HistoryCore.logger.error(ex);
        }
        return list.toArray(new String[0]);
    }

    public void init() {
        if (!configFile.exists()){
            if (!modPath.toFile().exists()) {
                modPath.toFile().mkdir();
            }
            try {
                FileWriter fileWriter = new FileWriter(configFile);
                gson.toJson(createDefaultFile(), fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception ex) {
                HistoryCore.logger.error(ex);
            }
        }
        doConfig();
    }

    protected abstract void doConfig();

    public abstract JsonObject createDefaultFile();

    public JsonArray copyFromArray(String [] array){
        JsonArray jsonArray = new JsonArray();
        for(String value : array) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

}
