package ui.tribe.general;

import registry.TribeRegistry;

import java.util.Collection;

public class TribesUi extends DisplayWithList {

    public TribesUi(){
        super("Tribes");
        TribeRegistry.addObserver(this::updateTribes);
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        super.updateContent(TribeRegistry.getTribeUi(selectedValue));

        super.selectContent(index, selectedValue);

        System.out.println("Revalidate Tribes:selectContent");
        revalidate();
    }

    private void updateTribes(){
        Collection<String> tribes = TribeRegistry.getAllTribes();

        super.updateList(tribes);

        System.out.println("Revalidate Tribes:updateTribes");
        repaint();
    }
}
