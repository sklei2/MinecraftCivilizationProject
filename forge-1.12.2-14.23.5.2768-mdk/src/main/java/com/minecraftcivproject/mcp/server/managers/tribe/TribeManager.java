package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;
import net.minecraft.util.math.BlockPos;

public class TribeManager {

    private final GoalManager goalManager;
    private final String tribeName;
    private final TownBlueprint townBlueprint;
    private final BlockPos corner;

    public TribeManager(String tribeName, TownBlueprint townBlueprint, BlockPos corner){
        this(new GoalManager(townBlueprint, corner), tribeName, townBlueprint, corner);
    }

    public TribeManager(GoalManager goalManager, String tribeName, TownBlueprint townBlueprint, BlockPos corner){
        this.goalManager = goalManager;
        this.tribeName = tribeName;
        this.townBlueprint = townBlueprint;
        this.corner = corner;
    }

    public String getTribeName() {
        return tribeName;
    }


}
