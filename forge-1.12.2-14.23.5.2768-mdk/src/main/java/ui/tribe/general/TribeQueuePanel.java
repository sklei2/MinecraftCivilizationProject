package ui.tribe.general;


import ui.tribe.queuedisplay.QueueDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TribeQueuePanel extends JPanel {

    private List<JButton> buttons = new ArrayList<>();

    public TribeQueuePanel(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(1, 0));
    }

    public void display(QueueDisplay<?> queueDisplay){
        clearButtons();

        List<String> displayStrings = queueDisplay.getDisplay();

        for(String string: displayStrings){
            JButton button = new JButton(string);
            buttons.add(button);
            add(button);
        }

        revalidate();
    }

    private void clearButtons(){
        for(JButton button : buttons){
            this.remove(button);
        }

        buttons = new ArrayList<>();
    }
}
