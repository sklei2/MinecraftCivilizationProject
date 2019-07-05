package ui.tribe.queuedisplay;

import com.minecraftcivproject.mcp.server.managers.building.construction.ConstructionProject;

import javax.swing.*;
import java.util.List;
import java.util.Queue;

public class ConstructionQueueDisplay extends QueueDisplay<ConstructionProject> {

    public ConstructionQueueDisplay(Queue<ConstructionProject> queue) {
        super(queue);
    }

    @Override
    public JPanel displayElement(ConstructionProject constructionProject) {
        JPanel panel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(panel);
        panel.setLayout(groupLayout);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        JLabel jLabel = new JLabel(constructionProject.getName());
        List<String> resourceRequirements = constructionProject.getResourceRequirements().getRequirements();

        GroupLayout.ParallelGroup resourceRequirementsGroupVertical = groupLayout.createParallelGroup();
        resourceRequirementsGroupVertical.addComponent(jLabel);

        GroupLayout.SequentialGroup resourceRequirementsGroupHorizontal = groupLayout.createSequentialGroup();
        resourceRequirementsGroupHorizontal.addComponent(jLabel);


        for(String resourceRequirement : resourceRequirements){
            JLabel resourceLabel = new JLabel(resourceRequirement);
            JLabel requirementLabel = new JLabel(constructionProject.getResourceRequirements().getRequirement(resourceRequirement) + "");

            GroupLayout.SequentialGroup resourceRequirementGroupVertical = groupLayout.createSequentialGroup();
            resourceRequirementGroupVertical.addComponent(resourceLabel);
            resourceRequirementGroupVertical.addComponent(requirementLabel);

            GroupLayout.ParallelGroup resourceRequirementGroupHorizontal = groupLayout.createParallelGroup();
            resourceRequirementGroupHorizontal.addComponent(resourceLabel);
            resourceRequirementGroupHorizontal.addComponent(requirementLabel);

            resourceRequirementsGroupVertical.addGroup(resourceRequirementGroupVertical);
            resourceRequirementsGroupHorizontal.addGroup(resourceRequirementGroupHorizontal);
        }

        groupLayout.setVerticalGroup(
                resourceRequirementsGroupVertical
        );

        groupLayout.setHorizontalGroup(
                resourceRequirementsGroupHorizontal
        );

        return panel;
    }

    @Override
    protected String displayElementName(ConstructionProject constructionProject) {
        return constructionProject.getName();
    }
}
