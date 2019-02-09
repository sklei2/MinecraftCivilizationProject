package com.minecraftcivproject.mcp.server.managers.tribe;

import com.minecraftcivproject.mcp.server.managers.goal.GoalManager;

public class TribeManager {

    private final GoalManager goalManager;

    public TribeManager(){
        this(new GoalManager());
    }

    public TribeManager(GoalManager goalManager){
        this.goalManager = goalManager;
    }
}
