package com.historycraft.data;

import com.historycraft.HistoryCore;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//

public class WorldProtectData {


    private static final String KEY = "chunkProtection";

    private static Map<ChunkPos, UUID> protectedChunk = new ConcurrentHashMap<>();

    public static boolean isProtected(ChunkPos chunkPos, UUID playerId) {
        if (protectedChunk.containsKey(chunkPos)) {
            //return !playerId.equals(protectedChunk.get(chunkPos)) ;
            return true;
        }
        return false;
    }

    public static void unprotectChunk(ChunkPos chunkPos) {
        protectedChunk.remove(chunkPos);
    }

    public static void protectChunk(ChunkPos chunkPos, UUID playerId) {
        protectedChunk.put(chunkPos, playerId);
    }

    public static void loadData(Chunk chunk, World world, NBTTagCompound data) {
        if (data.hasUniqueId(KEY)) {
            protectedChunk.put(chunk.getPos(), data.getUniqueId(KEY));
        }
    }

    public static void saveData(Chunk chunk, World world, NBTTagCompound data) {
        if (protectedChunk.containsKey(chunk.getPos())){
            data.setUniqueId(KEY, protectedChunk.get(chunk.getPos()));
        } else if (data.hasKey(KEY)) {
            data.removeTag(KEY);
        }
    }

    public static void freeData(Chunk chunk, World world) {
        if (protectedChunk.containsKey(chunk.getPos())) {
            protectedChunk.remove(chunk.getPos());
        }
    }

    public static void clearData() {
        protectedChunk.clear();
    }
}
