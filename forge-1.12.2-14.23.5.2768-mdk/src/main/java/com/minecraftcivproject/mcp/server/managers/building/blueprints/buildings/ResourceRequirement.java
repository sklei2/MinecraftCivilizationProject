package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.google.gson.annotations.Expose;

public class ResourceRequirement {

    @Expose
    private String resource;

    @Expose
    private int required;

    public String getResource() {
        return resource;
    }

    public int getRequired() {
        return required;
    }
}
