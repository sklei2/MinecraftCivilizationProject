package com.minecraftcivproject.mcp.World;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenCustomStructures implements IWorldGenerator {
    public static final WorldGenStructure TOWN = new WorldGenStructure("town");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            case 2:
                break;
            case 1:
                break;
            case 0:
                int chance = 400;  // What is this in reference to?? Smaller numbers = more likely chance to spawn
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomePlains.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, BiomeBeach.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, BiomeDesert.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeForest.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeForestMutated.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeHills.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeJungle.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, BiomeMesa.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeRiver.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeSavanna.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeSavannaMutated.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeSnow.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.STONE, BiomeStoneBeach.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeSwamp.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, BiomeTaiga.class);
                break;
            case -1:

        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes) {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock);
        BlockPos pos = new BlockPos(x,y,z);

        Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

        if (world.getWorldType() != WorldType.FLAT) {
            if (classesList.contains(biome)) {
                if (random.nextInt(chance) == 0) {
                    generator.generate(world, random, pos);
                }
            }
        }
    }

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {  // TODO: Make top block an array of block that it looks through
        int y = world.getHeight();
        boolean foundGround = false;

        while (!foundGround && --y >= 0) {
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = block == topBlock;
        }

        return y;
    }
}
