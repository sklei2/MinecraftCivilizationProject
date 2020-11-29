package com.minecraftcivproject.mcp.servers.managers.blueprints;

import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.BlockAssignment;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.Blueprint;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.BlueprintReader;
import com.minecraftcivproject.mcp.server.managers.building.blueprints.buildings.ResourceRequirement;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class BlueprintReaderTest {

    /*
    Unit test for blueprint reader method
     */

    @Test
    public void testReadBlueprint(){
        BlueprintReader blueprintReader = new BlueprintReader();

        Blueprint actualBlueprint = blueprintReader.readBlueprint("simple_house");

        /*
        @Test (example)
        public void givenAnonymousInnerClass_thenInitialiseList() {
            List<String> cities = new ArrayList() {{
                add("New York");
                add("Rio");
                add("Tokyo");
            }};

            assertTrue(cities.contains("New York"));
        }
         */

        // An ArrayList is a more specific type of Collection

        Collection<BlockAssignment> blocks = new ArrayList() {{
            add(new BlockAssignment("cobblestone", "c"));
            add(new BlockAssignment("log", "w"));
            add(new BlockAssignment("air", " "));
        }};

        Collection<ResourceRequirement> resources = new ArrayList() {{
            add(new ResourceRequirement("cobblestone", 25));
            add(new ResourceRequirement("log", 79));
        }};

        Collection<String> layer1 = new ArrayList() {{
            add("ccccc");
            add("ccccc");
            add("ccccc");
            add("ccccc");
            add("ccccc");
        }};

        Collection<String> layer2 = new ArrayList() {{
            add("wwwww");
            add("w   w");
            add("w   w");
            add("w   w");
            add("wwwww");
        }};

        Collection<String> layer3 = new ArrayList() {{
            add(" www ");
            add(" w w ");
            add(" w w ");
            add(" w w ");
            add(" www ");
        }};

        Collection<String> layer4 = new ArrayList() {{
            add("     ");
            add("  w  ");
            add("  w  ");
            add("  w  ");
            add("     ");
        }};

        Collection<Collection<String>> layers = new ArrayList() {{
            add(layer1);
            add(layer2);
            add(layer2);
            add(layer2);
            add(layer2);
            add(layer3);
            add(layer4);
        }};

        Blueprint expectedBlueprint = new Blueprint("simple_house", blocks, resources, layers);

        /*
        Calls .equals on the first argument and asserts a boolean statement on the expression (used to interface with
        the built-in test code)
         */
        assertEquals(actualBlueprint, expectedBlueprint);
    }

}
