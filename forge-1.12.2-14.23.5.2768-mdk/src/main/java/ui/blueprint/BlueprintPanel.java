package ui.blueprint;

import javax.swing.*;
import java.awt.*;

public class BlueprintPanel extends JPanel {

    public BlueprintPanel(){
        setLayout(new BorderLayout());
        add(new BlueprintGrid(20), BorderLayout.CENTER);
        add(new TabSelectionPanel(), BorderLayout.EAST);
    }

}
