package ui;

import javax.swing.*;
import java.awt.*;

public class BlueprintPanel extends JPanel {

    public BlueprintPanel(){
        setLayout(new BorderLayout());
        add(new BlueprintGrid(20), BorderLayout.CENTER);
    }

}
