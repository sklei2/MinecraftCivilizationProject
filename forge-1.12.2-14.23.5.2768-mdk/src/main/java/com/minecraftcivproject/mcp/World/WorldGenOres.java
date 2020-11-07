package com.minecraftcivproject.mcp.World;

import com.minecraftcivproject.mcp.common.initialization.register.BlockRegisterer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0){
            generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }

    // Get called above (second)
    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
        generateOre(BlockRegisterer.RegistrationHandler.CRYSTAL_ORE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 0, 12, random.nextInt(2+1)+1, 1);  // What would happen if the chance was less than 1? Every entry in the ChunkGenertorSettings class is an integer (this would also require changing the method input of chances to a float type)
        generateOre(BlockRegisterer.RegistrationHandler.MUDROCK_ORE.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 30, 60, random.nextInt(8-2+1)+2, 8);

        /**  Template
        *  generateOre(BlockRegisterer.RegistrationHandler.SAMPLE_ORE.getDefaultState(), world, random, 16x16 chunk X, 16x16 chunk Z,
        *  min height above bedrock ore spawns, max height above bedrock ore spawns, size is the amount of ore per cluster
        * (one vein, using the form random.nextInt(upper_limit-lower_limit+1)+lower_limit), chance of spawning within one chunk (unknown quantity as of now);
         */
    }

    /**
     * Gets called above (first)
    */
    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances){
        int deltaY = maxY - minY;

        for(int i = 0; i < chances; i++){
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate(world, random, pos);
        }
    }

}
