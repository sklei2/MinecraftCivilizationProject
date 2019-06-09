package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;

public class TribeManager {

    private final GoalManager goalManager;
    private final String tribeName;
    private final TownBlueprint townBlueprint;

    public TribeManager(String tribeName, TownBlueprint townBlueprint){
        this(new GoalManager(townBlueprint), tribeName, townBlueprint);
    }

    public TribeManager(GoalManager goalManager, String tribeName, TownBlueprint townBlueprint){
        this.goalManager = goalManager;
        this.tribeName = tribeName;
        this.townBlueprint = townBlueprint;
    }

    public String getTribeName() {
        return tribeName;
    }


}
