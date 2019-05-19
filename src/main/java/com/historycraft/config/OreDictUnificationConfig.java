package com.historycraft.config;

import java.util.ArrayList;
import java.util.List;

public class OreDictUnificationConfig {

    private List<String> modOrderId;
    private List<OreDict> oreDicts;



    public static class OreDict {
        private String  oreDict;
        private String  defaultItem;

        public String getOreDict() {
            return oreDict;
        }

        public OreDict setOreDict(String oreDict) {
            this.oreDict = oreDict;
            return this;
        }

        public String getDefaultItem() {
            return defaultItem;
        }

        public OreDict setDefaultItem(String defaultItem) {
            this.defaultItem = defaultItem;
            return this;
        }
    }

    public List<String> getModOrderId() {
        return modOrderId;
    }

    public OreDictUnificationConfig setModOrderId(List<String> modOrderId) {
        this.modOrderId = modOrderId;
        return this;
    }


    public OreDictUnificationConfig addOreDict(OreDict oreDict) {
        if (oreDicts == null) {
            oreDicts = new ArrayList<>();
        }
        oreDicts.add(oreDict);
        return this;
    }

    public List<OreDict> getOreDicts() {
        return oreDicts;
    }
}
