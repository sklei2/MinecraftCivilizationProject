package ui;

import ui.blueprint.BlueprintPanel;
import ui.tribe.general.TribesUi;

import javax.swing.*;
import java.awt.*;

public class UiFrame extends JFrame {

    private final NavigationBar navigationBar;
    private final MainContentPane mainContentPane;

    public UiFrame(){
        setTitle("Tribes");
        setSize(1200, 900);

        setLayout(new BorderLayout());

        mainContentPane = new MainContentPane(new TribesUi(), new BlueprintPanel());
        navigationBar = new NavigationBar(mainContentPane);

        navigationBar.setSize(200, 900);
        mainContentPane.setSize(1000, 900);

        add(navigationBar, BorderLayout.WEST);

        add(mainContentPane, BorderLayout.CENTER);
    }
}
