package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.google.gson.annotations.Expose;

public class ResourceRequirement {

    @Expose
    private String resource;

    @Expose
    private int numRequired;

    public ResourceRequirement(String resource, int numRequired) {
        this.resource = resource;
        this.numRequired = numRequired;
    }

    public String getResource() {
        return this.resource;
    }

    public int getNumRequired() {
        return this.numRequired;
    }
}
