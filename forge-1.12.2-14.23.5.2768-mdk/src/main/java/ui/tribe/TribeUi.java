package ui.tribe;

import com.minecraftcivproject.mcp.server.managers.tribe.TribeManager;

import javax.swing.*;

public class TribeUi extends JFrame {

    private final TribeManager tribeManager;

    public TribeUi(TribeManager tribeManager){
        this.tribeManager = tribeManager;
        setTitle(tribeManager.getTribeName());
        setSize(500, 500);
    }
}
