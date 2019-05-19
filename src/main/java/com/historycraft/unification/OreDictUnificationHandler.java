package com.historycraft.unification;

import com.historycraft.HistoryCore;
import com.historycraft.config.OreDictUnificationConfig;
import com.historycraft.config.OreDictUnificationConfigHandler;
import com.historycraft.jei.JEIAddonPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class OreDictUnificationHandler {

    private static OreDictUnificationHandler oreDictUnificationHandler;

    public static OreDictUnificationHandler getInstance() {
        if (oreDictUnificationHandler == null) {
            oreDictUnificationHandler = new OreDictUnificationHandler();
        }
        return oreDictUnificationHandler;
    }

    private OreDictUnificationHandler() {
    }

    public void cleanDuplicatedItems(){
        for(String oreName:  OreDictionary.getOreNames()) {
            for(ItemStack itemStack:  getItemStackByName(oreName)){
                JEIAddonPlugin.removeItem(itemStack);
            }
        }
    }

    public List<ItemStack> getItemStackByName(String name) {
        boolean found = false;
        for (OreDictUnificationConfig.OreDict oreDict : OreDictUnificationConfigHandler.oreDictUnificationConfig.getOreDicts()) {
            if (name.startsWith(oreDict.getOreDict())) {
                found = true;
            }
        }
        if (!found) return new ArrayList<>();


        for (String modId : OreDictUnificationConfigHandler.oreDictUnificationConfig.getModOrderId()) {
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
                        HistoryCore.logger.info("removed: {} oredict: {}", stack,name);
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
