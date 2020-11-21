package ui;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {

    private final JPanel tribes;
    private final JPanel blueprints;

    private final String currentContent = "";

    public MainContentPane(JPanel tribes, JPanel blueprints){
        this.tribes = tribes;
        this.blueprints = blueprints;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setContent(String content){

        if(content.equals(currentContent)){
            return;
        }

        removeAll();

        switch (content){
            case "Tribes":
                add(tribes);
                break;

            case "Blueprints":
                add(blueprints);
                break;

            default:
                System.out.println("UMMM this isn't a window: " + content);
        }

        validate();
    }
}
