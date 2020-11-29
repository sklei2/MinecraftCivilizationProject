package com.minecraftcivproject.mcp.server.managers.building.blueprints.towns;

import com.google.gson.GsonBuilder;
import com.minecraftcivproject.mcp.utils.ResourceFileHelper;


public class TownBlueprintReader {

    private static final String BLUEPRINT_RESOURCE_ROOT = "blueprints/towns";
    private final ResourceFileHelper resourceFileHelper;

    public TownBlueprintReader(){
        this.resourceFileHelper = new ResourceFileHelper();
    }

    public TownBlueprint readBlueprint(String relativePath){

        //uses googles json deserializer. Looks at TownBlueprint using reflection
        GsonBuilder gsonBuilder = new GsonBuilder();

        String fileContents = resourceFileHelper.readFileAsString(getBlueprintPath(relativePath));
        return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().fromJson(fileContents, TownBlueprint.class);
    }

    private String getBlueprintPath(String relativePath){
        return BLUEPRINT_RESOURCE_ROOT + "/" + relativePath + ".json";
    }
}
