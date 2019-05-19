package com.historycraft.api.utils;

import net.minecraft.item.ItemStack;

public class HistoryCraftUtils {

    private static HistoryCraftUtils historyCraftUtils;

    private HistoryCraftUtils() {

    }

    public boolean isItemName(String name, ItemStack itemStack){
        String[] split = name.split(":");
        if (split.length > 0) {
            if (!split[0].equals(itemStack.getItem().getRegistryName().getResourceDomain())){
                return false;
            }
        }
        if (split.length > 1) {
            if (!split[1].equals(itemStack.getItem().getRegistryName().getResourcePath())){
                return false;
            }
        }
        if (itemStack.getItemDamage() > 0) {
            if (split.length > 2) {
                if (!split[2].equals(String.valueOf(itemStack.getItemDamage()))){
                    return false;
                }
            }else  {
                return false;
            }
        } else if (split.length > 2) {
            return false;
        }
        return true;
    }

    public boolean isOreDictName(String configName, ItemStack itemStack) {
        return false;
    }

    public boolean isOreDictName(String configName, String oreDictName) {
        return false;
    }


    public static HistoryCraftUtils getInstance() {
        if (historyCraftUtils == null) {
            historyCraftUtils = new HistoryCraftUtils();
        }
        return historyCraftUtils;
    }
}
