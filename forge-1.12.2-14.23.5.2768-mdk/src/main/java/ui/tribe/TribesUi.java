package ui.tribe;

import registry.TribeRegistry;

import javax.swing.*;
import java.util.*;

public class TribesUi extends JFrame {

    List<JButton> buttons = new ArrayList<>();

    public TribesUi(){
        setTitle("Tribes");
        setSize(300, 200);

        TribeRegistry.addObserver(this::updateTribes);
    }

    private void updateTribes(){
        Collection<String> tribes = TribeRegistry.getAllTribes();

        clearButtons();

        for(String tribe : tribes){
            addButton(tribe);
        }

        revalidate();
    }

    private void addButton(String text){
        JButton button = new JButton(text);
        button.addActionListener(
                e -> clickButton(text)
        );
        this.add(button);
    }

    private void clickButton(String text){
        TribeUi tribeUi = TribeRegistry.getTribeUi(text);
        tribeUi.setVisible(true);
    }

    private void clearButtons(){
        for(JButton button : buttons){
            this.remove(button);
        }

        buttons = new ArrayList<>();
    }
}
