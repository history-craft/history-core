package com.historycraft;

import com.historycraft.block.WorldProtectBlock;
import com.historycraft.data.WorldProtectData;
import com.historycraft.recipe.CleanRecipeHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HistoryCore.MODID)
public class CommonProxy {

    public static WorldProtectBlock worldProtectBlock = new WorldProtectBlock();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        CleanRecipeHandler.doCleanUp(event);
    }


    @SubscribeEvent
    public static void blockRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
        if (event.getWorld().isRemote && !event.getEntityPlayer().isCreative()) {
            ChunkPos chunkPos = event.getWorld().getChunkFromBlockCoords(event.getPos()).getPos();
            if (WorldProtectData.isProtected(chunkPos,event.getEntityPlayer().getUniqueID())) {
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent
    public static void blockLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (event.getWorld().isRemote && !event.getEntityPlayer().isCreative()) {
            ChunkPos chunkPos = event.getWorld().getChunkFromBlockCoords(event.getPos()).getPos();
            if (WorldProtectData.isProtected(chunkPos,event.getEntityPlayer().getUniqueID())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void blockBreackEvent(BlockEvent.BreakEvent event) {
        if (event.getWorld().isRemote && !event.getPlayer().isCreative()) {
            ChunkPos chunkPos = event.getWorld().getChunkFromBlockCoords(event.getPos()).getPos();
            if (WorldProtectData.isProtected(chunkPos,event.getPlayer().getUniqueID())) {
                event.setCanceled(true);
            }
        }
    }


    @SubscribeEvent
    public static void explosionStartEvent(ExplosionEvent.Start event) {
       // event.setCanceled(true);
    }


    @SubscribeEvent
    public static void onChunkSave(ChunkDataEvent.Save event) {
        if (event.getWorld().isRemote)
            return;

        WorldProtectData.saveData(event.getChunk(),event.getWorld(),event.getData());
    }

    @SubscribeEvent
    public static void onChunkLoad(ChunkDataEvent.Load event) {
        if (event.getWorld().isRemote)
            return;
        WorldProtectData.loadData(event.getChunk(),event.getWorld(),event.getData());
    }

    @SubscribeEvent
    public static void onChunkUnLoad(ChunkDataEvent.Unload event) {
        if (event.getWorld().isRemote)
            return;
        WorldProtectData.freeData(event.getChunk(),event.getWorld());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(worldProtectBlock);
    }


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(worldProtectBlock.createItemBlock());
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

    }

    @SubscribeEvent
    public static void loadWorld(WorldEvent.Load event) {
        if (event.getWorld().isRemote) {
            WorldProtectData.clearData();
        }
    }

}
