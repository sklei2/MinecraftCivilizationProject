package com.minecraftcivproject.mcp.servers.managers.blueprints;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.BlueprintLayer;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.BlueprintReader;
import org.junit.Test;

import java.util.Collection;

public class BlueprintReaderTest {

    @Test
    public void testReadBlueprint(){
        BlueprintReader blueprintReader = new BlueprintReader();

        Blueprint blueprint = blueprintReader.readBlueprint("test");

        Collection<BlueprintLayer> layers = blueprint.getLayers();

        System.out.println(blueprint);
    }

}
