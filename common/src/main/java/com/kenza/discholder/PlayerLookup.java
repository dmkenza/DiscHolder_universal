package com.kenza.discholder;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.Collection;
import java.util.Objects;

//copied from Fabric

public class PlayerLookup {

    public static Collection<ServerPlayerEntity> tracking(BlockEntity blockEntity) {
        Objects.requireNonNull(blockEntity, "BlockEntity cannot be null");

        //noinspection ConstantConditions - IJ intrinsics don't know hasWorld == true will result in no null
        if (!blockEntity.hasWorld() || blockEntity.getWorld().isClient()) {
            throw new IllegalArgumentException("Only supported on server worlds!");
        }

        return tracking((ServerWorld) blockEntity.getWorld(), blockEntity.getPos());
    }

    public static Collection<ServerPlayerEntity> tracking(ServerWorld world, BlockPos pos) {
        Objects.requireNonNull(pos, "BlockPos cannot be null");

        return tracking(world, new ChunkPos(pos));
    }

    public static Collection<ServerPlayerEntity> tracking(ServerWorld world, ChunkPos pos) {
        Objects.requireNonNull(world, "The world cannot be null");
        Objects.requireNonNull(pos, "The chunk pos cannot be null");
        return world.getChunkManager().threadedAnvilChunkStorage.getPlayersWatchingChunk(pos, false);
    }



}
