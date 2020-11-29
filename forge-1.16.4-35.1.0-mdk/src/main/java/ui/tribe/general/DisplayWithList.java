package ui.tribe.general;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DisplayWithList extends JPanel {

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> list = new JList<>(listModel);
    private final List<String> defaultOptions;
    private final JPanel listPanel;
    private JPanel content;

    private int index = 0;
    private String selectedValue = "";

    public DisplayWithList(String listName){
        this(listName, new ArrayList<>());
    }

    public DisplayWithList(String listName, List<String> defaultOptions){
        setLayout(new BorderLayout());

        this.defaultOptions = defaultOptions;

        //create the list panel
        listPanel = new JPanel(new BorderLayout());

        JLabel listLabel = new JLabel(listName);
        listLabel.setPreferredSize(new Dimension(100, 25));
        listLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(list, BorderLayout.CENTER);

        listPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        setList(this.defaultOptions);

        list.addListSelectionListener(this::valueChanged);

        listPanel.setVisible(true);
        add(listPanel, BorderLayout.WEST);

        //create the content pane
        clearContent();
    }

    protected void selectContent(int index, String selectedValue){
        this.index = index;
        this.selectedValue = selectedValue;
    }

    protected void updateList(Collection<String> listValues){
        setList(listValues);
    }

    protected void updateContent(JPanel jPanel){
        remove(content);

        content = jPanel;
        add(content, BorderLayout.CENTER);
        content.setVisible(true);
    }

    protected void clearContent(){
        content = new JPanel(new BorderLayout());
        content.setVisible(true);

        add(content, BorderLayout.CENTER);
    }

    protected int getIndex(){
        return this.index;
    }

    protected String getSelectedValue(){
        return this.selectedValue;
    }

    private void setList(Collection<String> options){
        listModel.removeAllElements();

        for(String option : options){
            listModel.addElement(option);
        }
    }


    private void valueChanged(ListSelectionEvent e){
        if(!e.getValueIsAdjusting()){
            if(list.getSelectedIndex() == -1){
                clearContent();
            } else{
                selectContent(list.getSelectedIndex(), list.getSelectedValue());
            }
        }
    }

}
