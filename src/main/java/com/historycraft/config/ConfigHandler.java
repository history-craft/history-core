package com.historycraft.config;

import com.google.gson.*;
import com.historycraft.HistoryCore;
import com.historycraft.recipe.RecipeHandler;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    public static final Gson gson = new Gson();
    public static File configFile;

    public static void init() {
        Path modPath = Loader.instance().getConfigDir().toPath().resolve(HistoryCore.MODID);
        createDefaultFiles(modPath);
        RecipeHandler.removedByProduct = readProperty("crusher");
    }

    private static String[] readProperty(String property) {
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


    private static void createDefaultFiles(Path modPath) {
        if (!modPath.toFile().exists()) {
            modPath.toFile().mkdir();
        }
        configFile = new File(modPath.toFile(), "removed.json");
        if (!configFile.exists()) {
            JsonObject jsonObject = new JsonObject();
            JsonArray array = new JsonArray();
            array.add("ore1");
            array.add("ore2");
            jsonObject.add("crusher",array);
            System.out.println("Json: " + jsonObject.toString());
            try {
                FileWriter fileWriter = new FileWriter(configFile);
                gson.toJson(jsonObject, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception ex) {
                HistoryCore.logger.error(ex);
            }
        }
    }

}
