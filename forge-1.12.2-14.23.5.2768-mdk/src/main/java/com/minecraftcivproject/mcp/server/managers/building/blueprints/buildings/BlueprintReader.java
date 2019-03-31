package com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings;

import com.google.gson.GsonBuilder;
import com.minecraftcivproject.mcp.utils.ResourceFileHelper;


public class BlueprintReader {

    private static final String BLUEPRINT_RESOURCE_ROOT = "blueprints";
    private final ResourceFileHelper resourceFileHelper;

    public BlueprintReader(){
        this.resourceFileHelper = new ResourceFileHelper();
    }

    public Blueprint readBlueprint(String relativePath){

        //uses googles json deserializer. Looks at Blueprint using reflection
        GsonBuilder gsonBuilder = new GsonBuilder();

        String fileContents = resourceFileHelper.readFileAsString(getBlueprintPath(relativePath));
        return gsonBuilder.excludeFieldsWithoutExposeAnnotation().create().fromJson(fileContents, Blueprint.class);
    }

    private String getBlueprintPath(String relativePath){
        return BLUEPRINT_RESOURCE_ROOT + "/" + relativePath + ".json";
    }
}
