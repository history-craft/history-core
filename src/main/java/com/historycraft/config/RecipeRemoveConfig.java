package com.historycraft.config;

import java.util.ArrayList;
import java.util.List;

public class RecipeRemoveConfig {

    public static class Mod {

        private String modId;
        private boolean hide = false;
        private List<String> whiteList = new ArrayList<>();

        public String getModId() {
            return modId;
        }

        public Mod setModId(String modId) {
            this.modId = modId;
            return this;
        }


        public Mod addToWhiteList(String item) {
            whiteList.add(item);
            return this;
        }

        public List<String> getWhiteList() {
            if (whiteList == null) {
                whiteList = new ArrayList<>();
            }
            return whiteList;
        }

        public Mod setHide(boolean hide) {
            this.hide = hide;
            return this;
        }

        public boolean isHide() {
            return hide;
        }

        public static Mod create(){
            return new Mod();
        }
    }

    private List<Mod> mods = new ArrayList<>();

    public List<Mod> getMods() {
        return mods;
    }

    public RecipeRemoveConfig addMod(Mod mod) {
        mods.add(mod);
        return this;
    }

    public static RecipeRemoveConfig create(){
        return new RecipeRemoveConfig();
    }
}
