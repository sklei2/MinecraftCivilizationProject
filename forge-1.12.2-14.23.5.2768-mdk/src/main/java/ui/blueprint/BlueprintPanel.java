package ui.blueprint;

import javax.swing.*;
import java.awt.*;

public class BlueprintPanel extends JPanel {

    public BlueprintPanel(){
        setLayout(new BorderLayout());

        TabSelectionPanel tabSelectionPanel = new TabSelectionPanel();

        add(new BlueprintGrid(20, tabSelectionPanel), BorderLayout.CENTER);
        add(tabSelectionPanel, BorderLayout.EAST);
    }

}
