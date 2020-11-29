package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.google.gson.annotations.Expose;

/**
 * Just a class used for deserialization
 */
public class BlockAssignment {

    @Expose
    private String name;

    @Expose
    private String character;


    public BlockAssignment(String name, String character){
        this.name = name;
        this.character = character;
    }

    public String getCharacter() {
        return this.character;
    }

    public String getName() {
        return this.name;
    }
}
