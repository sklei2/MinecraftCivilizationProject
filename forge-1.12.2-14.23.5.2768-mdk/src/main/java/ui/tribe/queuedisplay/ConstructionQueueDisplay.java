package ui.tribe.queuedisplay;

import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionProject;

import java.util.Queue;

public class ConstructionQueueDisplay extends QueueDisplay<ConstructionProject> {

    public ConstructionQueueDisplay(Queue<ConstructionProject> queue) {
        super(queue);
    }

    @Override
    public String displayElement(ConstructionProject constructionProject) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Construction Project\n");
        for(String resource : constructionProject.getResourceRequirements().getRequirements()){
            stringBuilder
                    .append("\t")
                    .append(resource)
                    .append(": ")
                    .append(constructionProject.getResourceRequirements().getRequirement(resource))
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
