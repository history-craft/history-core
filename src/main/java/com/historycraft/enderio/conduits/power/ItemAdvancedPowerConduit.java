package com.historycraft.enderio.conduits.power;

import crazypants.enderio.api.IModObject;
import crazypants.enderio.base.conduit.IConduit;
import crazypants.enderio.base.conduit.IServerConduit;
import crazypants.enderio.conduits.conduit.AbstractItemConduit;
import crazypants.enderio.conduits.conduit.ItemConduitSubtype;
import crazypants.enderio.conduits.conduit.power.IPowerConduit;
import crazypants.enderio.conduits.conduit.power.IPowerConduitData;
import crazypants.enderio.conduits.conduit.power.PowerConduit;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemAdvancedPowerConduit extends AbstractItemConduit {


    public static ItemAdvancedPowerConduit create(@Nonnull IModObject modObject, @Nullable Block block) {
        return null;
    }


    protected ItemAdvancedPowerConduit(@Nonnull IModObject modObj, ItemConduitSubtype... subtypes) {
        super(modObj, subtypes);
    }

    @Nonnull
    @Override
    public Class<? extends IConduit> getBaseConduitType() {
        return IPowerConduit.class;
    }

    @Override
    public IServerConduit createConduit(@Nonnull ItemStack item, @Nonnull EntityPlayer player) {
        return new PowerConduit(IPowerConduitData.Registry.fromID(item.getItemDamage()));
    }

    @Override
    public boolean shouldHideFacades(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
        return true;
    }
}
