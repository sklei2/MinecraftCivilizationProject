package ui.blueprint;

import javax.swing.*;
import java.awt.*;

public class BlueprintPanel extends JPanel {
    private final TabSelectionPanel tabSelectionPanel;

    private BlueprintGrid blueprintGrid;

    public BlueprintPanel(){
        setLayout(new BorderLayout());

        tabSelectionPanel = new TabSelectionPanel();

        this.blueprintGrid = new BlueprintGrid(20, 20, tabSelectionPanel);

        JPanel gridSizePanel = new JPanel();
        gridSizePanel.setLayout(new FlowLayout());
        gridSizePanel.setPreferredSize(new Dimension(100, 1000));

        JSpinner xSpinner = createGridSpinner(20);
        JSpinner zSpinner = createGridSpinner(20);
        JSpinner ySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));

        xSpinner.addChangeListener(changeEvent -> blueprintGrid.setGridSize((Integer)xSpinner.getValue(), (Integer)zSpinner.getValue()));
        zSpinner.addChangeListener(changeEvent -> blueprintGrid.setGridSize((Integer)xSpinner.getValue(), (Integer)zSpinner.getValue()));
        ySpinner.addChangeListener(changeEvent -> blueprintGrid.setYLevel((Integer)ySpinner.getValue()));

        gridSizePanel.add(createLabelPanel(xSpinner, "x: "));
        gridSizePanel.add(createLabelPanel(ySpinner, "y: "));
        gridSizePanel.add(createLabelPanel(zSpinner, "z: "));

        JScrollPane scrollPane = new JScrollPane(blueprintGrid);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.getViewport().setPreferredSize(new Dimension(500, 500));

        add(scrollPane, BorderLayout.CENTER);

        JButton zoomIn = new JButton("Zoom In");
        zoomIn.addActionListener(actionEvent -> blueprintGrid.zoomIn());

        JButton zoomOut = new JButton("Zoom Out");
        zoomOut.addActionListener(actionEvent -> blueprintGrid.zoomOut());

        gridSizePanel.add(zoomIn);
        gridSizePanel.add(zoomOut);

        add(tabSelectionPanel, BorderLayout.EAST);
        add(gridSizePanel, BorderLayout.WEST);
    }

    private JPanel createLabelPanel(JComponent component, String label){
        JPanel panel = new JPanel();
        JLabel jLabel = new JLabel(label);

        panel.setLayout(new FlowLayout());
        panel.add(jLabel);
        panel.add(component);

        return panel;
    }

    private JSpinner createGridSpinner(int startingValue){
        return new JSpinner(new SpinnerNumberModel(startingValue, 1, 1000, 1));
    }

}
