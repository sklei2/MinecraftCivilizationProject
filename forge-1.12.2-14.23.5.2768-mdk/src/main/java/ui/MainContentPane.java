package ui;

import javax.swing.*;
import java.awt.*;

public class MainContentPane extends JPanel {

    private final JPanel tribes;
    private final JPanel blueprints;

    private JPanel activePanel = new JPanel();

    private final String currentContent = "";

    public MainContentPane(JPanel tribes, JPanel blueprints){
        this.tribes = tribes;
        this.blueprints = blueprints;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
    }

    public void setContent(String content){

        if(content.equals(currentContent)){
            return;
        }

        removeAll();

        activePanel.setVisible(false);

        switch (content){
            case "Tribes":
                activePanel = tribes;
                break;

            case "Blueprints":
                activePanel = blueprints;
                break;

            default:
                System.out.println("UMMM this isn't a window: " + content);
        }

        activePanel.setVisible(true);
        add(activePanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
