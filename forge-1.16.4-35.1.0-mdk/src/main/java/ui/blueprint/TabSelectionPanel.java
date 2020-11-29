package ui.blueprint;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;

public class TabSelectionPanel extends JPanel {

    private JComponent contentPanel;
    private Block selectedBlock;

    public TabSelectionPanel() {
        setSize(200, 900);
        setLayout(new BorderLayout());

        displayTabButtonPanel();
    }

    public Block getSelectedBlock(){
        return selectedBlock;
    }

    private void displayBlocksForTab(String tab){
        removeAll();

        contentPanel = getBlocksForTabPanel(tab);
        add(contentPanel, BorderLayout.CENTER);

        validate();
    }

    private JPanel getBlocksForTabPanel(String tab){
        Collection<Block> blocks = ForgeRegistries.BLOCKS.getValuesCollection().stream().filter(block -> {
            String blockTab = "";

            if(block.getCreativeTabToDisplayOn() == null){
                blockTab = "other";
            }else {
                blockTab = block.getCreativeTabToDisplayOn().getTabLabel();
            }

            return blockTab.equals(tab);
        }).collect(Collectors.toSet());

        JPanel blocksAndBackButton = new JPanel();

        blocksAndBackButton.setLayout(new BorderLayout());

        JButton back = new JButton("Back");
        back.addActionListener(actionEvent -> displayTabButtonPanel());
        back.setSize(100, 40);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new FlowLayout());
        backButtonPanel.add(back);

        backButtonPanel.setSize(100, 60);

        blocksAndBackButton.add(backButtonPanel, BorderLayout.NORTH);

        JPanel blocksForTabPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 2);
        gridLayout.setHgap(3);
        gridLayout.setVgap(3);

        blocksForTabPanel.setLayout(gridLayout);

        for(Block b : blocks){
            blocksForTabPanel.add(getBlockButton(b));
        }


        JScrollPane jScrollPane = new JScrollPane(blocksForTabPanel);

        blocksAndBackButton.add(jScrollPane, BorderLayout.CENTER);

        return blocksAndBackButton;
    }

    private JButton getBlockButton(Block block){
        JButton jButton = new JButton(block.getLocalizedName());

        Image image = BlockToImage.getImageForBlock(block, 16);

        if(image != null){
            jButton.setIcon(new ImageIcon(image));
        }

        jButton.setActionCommand(block.getRegistryName().getResourcePath());
        jButton.addActionListener(actionEvent -> {
            Block b = Block.getBlockFromName(actionEvent.getActionCommand());
            selectBlock(b);
        });

        return jButton;
    }

    private void selectBlock(Block b){
        System.out.println("SELECT block: " + b.getUnlocalizedName());
        selectedBlock = b;
    }

    private void displayTabButtonPanel(){
        removeAll();

        contentPanel = getTabButtonPanel();
        add(contentPanel, BorderLayout.CENTER);

        validate();
    }

    private JPanel getTabButtonPanel(){
        JPanel tabButtonPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(20, 1);
        gridLayout.setVgap(5);
        tabButtonPanel.setLayout(gridLayout);

        Collection<String> tabs = ForgeRegistries.BLOCKS.getValuesCollection().stream().map(block -> {
            if(block.getCreativeTabToDisplayOn() == null){
                return "other"; // somehow there are blocks not on a tab
            }

            return block.getCreativeTabToDisplayOn().getTabLabel();
        }).collect(Collectors.toSet());

        for(String tab : tabs){
            addTab(tabButtonPanel, tab);
        }

        return tabButtonPanel;
    }

    private void addTab(JPanel panel, String tabName){
        JButton button = new JButton(tabName);
        button.setActionCommand(tabName);

        button.addActionListener(actionEvent -> displayBlocksForTab(actionEvent.getActionCommand()));

        panel.add(button);
    }
}
