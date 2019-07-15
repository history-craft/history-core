package com.historycraft.api.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isOreDictByItemStack(ItemStack itemStack, String... oreDicts) {
        for (String stackOreDict: getOreDictByItemStack(itemStack)){
            for(String compOreDict: oreDicts) {
                if (stackOreDict.contains(compOreDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getOreDictByItemStack(ItemStack itemStack) {
        List<String> result = new ArrayList<>();
        int[] oreIDs = OreDictionary.getOreIDs(itemStack);
        for (int id : oreIDs) {
            result.add(OreDictionary.getOreName(id));
        }
        return result;
    }

    public static HistoryCraftUtils getInstance() {
        if (historyCraftUtils == null) {
            historyCraftUtils = new HistoryCraftUtils();
        }
        return historyCraftUtils;
    }
}
