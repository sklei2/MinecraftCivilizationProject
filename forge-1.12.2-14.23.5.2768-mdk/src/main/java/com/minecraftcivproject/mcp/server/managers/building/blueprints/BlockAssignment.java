package com.minecraftcivproject.mcp.server.managers.building.blueprints;

import com.google.gson.annotations.Expose;

/**
 * Just a class used for deserialization
 */
public class BlockAssignment {

    @Expose
    private String name;

    @Expose
    private String character;


    public BlockAssignment(){

    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }
}
