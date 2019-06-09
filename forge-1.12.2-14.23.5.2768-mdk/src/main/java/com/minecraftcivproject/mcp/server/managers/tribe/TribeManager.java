package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import net.minecraft.util.math.BlockPos;

public class TribeManager {

    private final GoalManager goalManager;
    private final QueueManager queueManager;
    private final String tribeName;
    private final TownBlueprint townBlueprint;
    private final BlockPos corner;

    public TribeManager(String tribeName, TownBlueprint townBlueprint, BlockPos corner){
        this.queueManager = new QueueManager();
        this.goalManager = new GoalManager(this.queueManager, townBlueprint, corner);
        this.tribeName = tribeName;
        this.townBlueprint = townBlueprint;
        this.corner = corner;
    }

    public String getTribeName() {
        return tribeName;
    }


}
