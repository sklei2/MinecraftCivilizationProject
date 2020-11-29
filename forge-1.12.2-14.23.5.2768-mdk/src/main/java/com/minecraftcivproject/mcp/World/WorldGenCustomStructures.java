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
                int yOffset = 8;
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomePlains.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, yOffset, BiomeBeach.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, yOffset, BiomeDesert.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeForest.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeForestMutated.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeHills.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeJungle.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.SAND, yOffset, BiomeMesa.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeRiver.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeSavanna.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeSavannaMutated.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeSnow.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.STONE, yOffset, BiomeStoneBeach.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeSwamp.class);
                generateStructure(TOWN, world, random, chunkX, chunkZ, chance, Blocks.GRASS, yOffset, BiomeTaiga.class);
                break;
            case -1:

        }
    }

    private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, int yOffset, Class<?>... classes) {
        ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

        int x = (chunkX * 16) + random.nextInt(15);
        int z = (chunkZ * 16) + random.nextInt(15);
        int y = calculateGenerationHeight(world, x, z, topBlock, yOffset);
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

    private static int calculateGenerationHeight(World world, int x, int z, Block topBlock, int yOffset) {  // TODO: Make top block an array of block that it looks through
        int y = world.getHeight();
        boolean foundGround = false;

        while (!foundGround && --y >= 0) {
            Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
            foundGround = block == topBlock;
        }

        return y;
    }
}
