package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.google.gson.annotations.Expose;

/**
 * Just a class used for deserialization
 */
public class BuildingAssignment {

    @Expose
    private String name;

    @Expose
    private String character;


    public BuildingAssignment(){

    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }
}
