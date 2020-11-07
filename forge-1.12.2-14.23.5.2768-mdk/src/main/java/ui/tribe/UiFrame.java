package ui.tribe;

import ui.tribe.general.TribesUi;

import javax.swing.*;

public class UiFrame extends JFrame {

    public UiFrame(){
        setTitle("Tribes");
        setSize(1200, 900);

        add(new TribesUi());
    }
}
