package com.minecraftcivproject.mcp.server.managers.building;

import com.minecraftcivproject.mcp.server.managers.TickableManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.BlueprintManager;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.towns.TownBlueprint;
import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionManager;
import com.minecraftcivproject.mcp.server.managers.queue.QueueManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildingManager implements TickableManager {

    private final BlueprintManager blueprintManager;
    private final ConstructionManager constructionManager;
    private final OperableBuildingManager operableBuildingManager;
    private final QueueManager queueManager;

    public BuildingManager(QueueManager queueManager, TownBlueprint townBlueprint, World world, BlockPos blockPos){
        this(new BlueprintManager(townBlueprint), new ConstructionManager(queueManager, world, blockPos), new OperableBuildingManager(queueManager), queueManager);
    }

    public BuildingManager(BlueprintManager blueprintManager, ConstructionManager constructionManager, OperableBuildingManager operableBuildingManager, QueueManager queueManager){
        this.blueprintManager = blueprintManager;
        this.constructionManager = constructionManager;
        this.operableBuildingManager = operableBuildingManager;
        this.queueManager = queueManager;

        this.constructionManager.queueAll(this.blueprintManager.getBuildingsToConstruct());
    }

    @Override
    public void onTick() {
        constructionManager.onTick();
        operableBuildingManager.onTick();
    }
}
