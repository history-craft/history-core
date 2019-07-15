package com.historycraft.block;

import com.historycraft.data.WorldProtectData;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldProtectBlock extends Block {

    public WorldProtectBlock() {
        super(Material.IRON);
        setHardness(5f);
        setResistance(5f);
        setUnlocalizedName("world-protect-block");
        setRegistryName("world-protect-block");
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        WorldProtectData.protectChunk(worldIn.getChunkFromBlockCoords(pos).getPos(), placer.getUniqueID());

    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return !WorldProtectData.isProtected(player.getEntityWorld().getChunkFromBlockCoords(pos).getPos(), player.getUniqueID());
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.isRemote) {
            WorldProtectData.unprotectChunk(worldIn.getChunkFromBlockCoords(pos).getPos());
            super.onBlockDestroyedByPlayer(worldIn, pos, state);
        }
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
        super.onBlockExploded(world, pos, explosion);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }


}
