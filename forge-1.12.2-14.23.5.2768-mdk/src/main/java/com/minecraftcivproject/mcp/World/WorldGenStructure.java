package com.minecraftcivproject.mcp.World;

import com.minecraftcivproject.mcp.MinecraftCivProject;
import com.minecraftcivproject.mcp.utils.IStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class WorldGenStructure extends WorldGenerator implements IStructure {
    public static String structureName;

    public WorldGenStructure(String name) {
        this.structureName = name;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        this.generateStructure(world, pos);
        return true;
    }

    public static void generateStructure(World world, BlockPos pos) {
        MinecraftServer mcServer = world.getMinecraftServer();
        TemplateManager manager = worldServer.getStructureTemplateManager();
        ResourceLocation location = new ResourceLocation(MinecraftCivProject.MODID, structureName);
        Template template = manager.get(mcServer, location);

        if (template != null) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            template.addBlocksToWorldChunk(world, pos, settings);
        }
    }
}
