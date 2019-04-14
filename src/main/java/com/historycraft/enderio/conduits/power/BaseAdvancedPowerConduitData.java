package com.historycraft.enderio.conduits.power;

import com.enderio.core.client.render.IconUtil;
import crazypants.enderio.base.conduit.IConduitTexture;
import crazypants.enderio.base.conduit.geom.CollidableComponent;
import crazypants.enderio.conduits.conduit.power.IPowerConduitData;
import crazypants.enderio.conduits.conduit.power.PowerConduit;
import crazypants.enderio.conduits.render.ConduitTextureWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static com.historycraft.enderio.conduits.ConduitEnum.item_advanced_power_conduit;

public class BaseAdvancedPowerConduitData implements IPowerConduitData {

    private final int id;
    private final int rf;

    public BaseAdvancedPowerConduitData(int id, int rf) {
        this.id = id;
        this.rf = rf;
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getMaxEnergyIO() {
        return rf;
    }

    @Nonnull
    @Override
    public ItemStack createItemStackForSubtype() {
        return new ItemStack(item_advanced_power_conduit.getItemNN(), 1, getID());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public @Nonnull IConduitTexture getTextureForState(@Nonnull CollidableComponent component) {
        if (component.isCore()) {
            return AdvancedPowerConduit.ICONS.get(AdvancedPowerConduit.ICON_CORE_KEY + AdvancedPowerConduit.POSTFIX[getID() -3 ]);
        }
        if (PowerConduit.COLOR_CONTROLLER_ID.equals(component.data)) {
            return new ConduitTextureWrapper(IconUtil.instance.whiteTexture);
        }
        return AdvancedPowerConduit.ICONS.get(AdvancedPowerConduit.ICON_KEY + AdvancedPowerConduit.POSTFIX[getID() -3 ]);
    }


}
