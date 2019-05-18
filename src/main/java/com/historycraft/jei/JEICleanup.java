package com.historycraft.jei;

import gregtech.api.GTValues;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class JEICleanup {

    public static String [] orderModId = new String[] {"minecraft", GTValues.MODID};
    public static String [] removeDuplicates = new String[] {"plate","rod", "ingot", "circuit"};
    private static List<ItemStack> itemsToRemove = new ArrayList<>();

    public static void applyCleanup() {
        cleanDuplicatedItems();
        JEIAddonPlugin.itemRegistry.removeIngredientsAtRuntime(VanillaTypes.ITEM, itemsToRemove);
    }

    public static void cleanDuplicatedItems(){
        for(String oreName:  OreDictionary.getOreNames()) {
            for(ItemStack itemStack:  getItemStackByName(oreName)){
                itemsToRemove.add(itemStack);
            }
        }
    }

    public static List<ItemStack> getItemStackByName(String name) {
        boolean found = false;
        for (String removeDuplicated : removeDuplicates) {
            if (name.startsWith(removeDuplicated)) {
                found = true;
            }
        }
        if (!found) return new ArrayList<>();


        for (String modId :orderModId) {
            boolean foundFromMod = false;
            for(ItemStack stack: OreDictionary.getOres(name)){
                if (modId.equals(ForgeHooks.getDefaultCreatorModId(stack))){
                    foundFromMod = true;
                    break;
                }
            }
            //if found some from the mod, do the test
            if(foundFromMod) {
                List<ItemStack> toReturn = new ArrayList<>();
                for(ItemStack stack: OreDictionary.getOres(name)){
                    //if found, add all that is not from the mod
                    if (!modId.equals(ForgeHooks.getDefaultCreatorModId(stack))){
                        toReturn.add(stack);
                    }
                }
                return toReturn;
            }
            //if not, try next mod.
        }
        return new ArrayList<>();
    }
}
