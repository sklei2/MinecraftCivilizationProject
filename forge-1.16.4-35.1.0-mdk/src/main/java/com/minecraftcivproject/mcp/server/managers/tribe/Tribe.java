package com.minecraftcivproject.mcp.server.managers.tribe;

import net.minecraft.world.World;
import ui.tribe.general.TribeUi;

public class Tribe {

    private final String tribeName;
    private final TribeManager tribeManager;
    private final TribeUi tribeUi;
    private final World world;

    public Tribe(String tribeName, TribeManager tribeManager, TribeUi tribeUi, World world){
        this.tribeName = tribeName;
        this.tribeManager = tribeManager;
        this.tribeUi = tribeUi;
        this.world = world;
    }

    public String getTribeName() {
        return tribeName;
    }

    public TribeManager getTribeManager() {
        return tribeManager;
    }

    public TribeUi getTribeUi() {
        return tribeUi;
    }

    public World getWorld() {
        return world;
    }
}
