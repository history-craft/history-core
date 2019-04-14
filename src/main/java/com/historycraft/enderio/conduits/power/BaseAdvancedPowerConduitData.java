package com.historycraft.enderio.conduits.power;

import crazypants.enderio.base.conduit.geom.CollidableComponent;
import crazypants.enderio.conduits.conduit.power.IPowerConduitData;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BaseAdvancedPowerConduitData implements IPowerConduitData {

    private final int id;

    public BaseAdvancedPowerConduitData(int id) {
        this.id = id;
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getMaxEnergyIO() {
        return 0;
    }

    @Nonnull
    @Override
    public ItemStack createItemStackForSubtype() {
        //TODO i have no ideia
        return null;
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getTextureForState(@Nonnull CollidableComponent component) {
        //TODO textures
        return null;
    }
}
