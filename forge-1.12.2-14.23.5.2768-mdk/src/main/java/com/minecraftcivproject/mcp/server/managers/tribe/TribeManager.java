package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ui.tribe.queuedisplay.QueueListener;

public class TribeManager {

    private final GoalManager goalManager;
    private final QueueManager queueManager;
    private final TownBlueprint townBlueprint;
    private final BlockPos corner;

    public TribeManager(TownBlueprint townBlueprint, World world, BlockPos corner, QueueListener queueListener){
        this.queueManager = new QueueManager();
        this.queueManager.addObserver(queueListener);
        this.goalManager = new GoalManager(this.queueManager, townBlueprint, world, corner);
        this.townBlueprint = townBlueprint;
        this.corner = corner;
    }
}
