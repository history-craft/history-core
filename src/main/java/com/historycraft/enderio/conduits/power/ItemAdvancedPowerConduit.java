package com.historycraft.enderio.conduits.power;

import crazypants.enderio.api.IModObject;
import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.conduit.ConduitDisplayMode;
import crazypants.enderio.base.conduit.IConduit;
import crazypants.enderio.base.conduit.IServerConduit;
import crazypants.enderio.base.gui.IconEIO;
import crazypants.enderio.base.lang.LangPower;
import crazypants.enderio.conduits.conduit.AbstractItemConduit;
import crazypants.enderio.conduits.conduit.ItemConduitSubtype;
import crazypants.enderio.conduits.conduit.power.IPowerConduit;
import crazypants.enderio.conduits.conduit.power.IPowerConduitData;
import crazypants.enderio.conduits.conduit.power.PowerConduit;
import crazypants.enderio.conduits.conduit.power.PowerConduitRenderer;
import crazypants.enderio.conduits.render.ConduitBundleRenderManager;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemAdvancedPowerConduit extends AbstractItemConduit {

    static {
        IPowerConduitData.Registry.register(new BaseAdvancedPowerConduitData(3));
    }

    public static ItemAdvancedPowerConduit create(@Nonnull IModObject modObject, @Nullable Block block) {
        ItemAdvancedPowerConduit itemAdvancedPowerConduit = new ItemAdvancedPowerConduit(modObject);
        return itemAdvancedPowerConduit;
    }

    protected ItemAdvancedPowerConduit(@Nonnull IModObject modObject) {
        super(modObject,
                new ItemConduitSubtype(modObject.getUnlocalisedName() + "_tungstensteel", modObject.getRegistryName().toString() + "_tungstensteel")
        );
        ConduitDisplayMode.registerDisplayMode(new ConduitDisplayMode(getBaseConduitType(), IconEIO.WRENCH_OVERLAY_POWER, IconEIO.WRENCH_OVERLAY_POWER_OFF));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerRenderers(@Nonnull IModObject modObject) {
        super.registerRenderers(modObject);
        ConduitBundleRenderManager.instance.getConduitBundleRenderer().registerRenderer(new PowerConduitRenderer());
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
    @SideOnly(Side.CLIENT)
    public void addInformation(@Nonnull ItemStack itemStack, @Nullable World world, @Nonnull List<String> list, @Nonnull ITooltipFlag flag) {
        String prefix = EnderIO.lang.localize("power.max_output") + " ";
        super.addInformation(itemStack, world, list, flag);
        int cap = PowerConduit.getMaxEnergyIO(IPowerConduitData.Registry.fromID(itemStack.getItemDamage()));//TODO get from new list.
        list.add(prefix + LangPower.RFt(cap));
    }

    @Override
    public boolean shouldHideFacades(@Nonnull ItemStack stack, @Nonnull EntityPlayer player) {
        return true;
    }
}
