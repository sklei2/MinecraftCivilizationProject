package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;

public class TribeManager {

    private final GoalManager goalManager;
    private final String tribeName;

    public TribeManager(String tribeName, TownBlueprint townBlueprint){
        this(new GoalManager(townBlueprint), tribeName, townBlueprint);
    }

    public TribeManager(GoalManager goalManager, String tribeName){
        this.goalManager = goalManager;
        this.tribeName = tribeName;
    }

    public String getTribeName() {
        return tribeName;
    }


}
