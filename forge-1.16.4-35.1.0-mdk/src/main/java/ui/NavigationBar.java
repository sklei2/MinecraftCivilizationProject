package ui;

import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {

    private MainContentPane contentPane;

    public NavigationBar(MainContentPane contentPane){

        this.contentPane = contentPane;

        GridLayout gridLayout = new GridLayout(20, 1);
        gridLayout.setVgap(5);

        setLayout(gridLayout);

        add(createNavButton("Tribes"));
        add(createNavButton("Blueprints"));
    }


    private JButton createNavButton(String name){
        JButton jButton = new JButton(name);
        jButton.setActionCommand(name);

        jButton.setMaximumSize(new Dimension(200, 100));

        jButton.addActionListener(e -> contentPane.setContent(e.getActionCommand()));

        return jButton;
    }
}
