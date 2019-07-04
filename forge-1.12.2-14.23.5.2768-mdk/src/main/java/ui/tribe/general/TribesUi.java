package ui.tribe.general;

import registry.TribeRegistry;

import java.util.Collection;

public class TribesUi extends DisplayWithList {

    public TribesUi(){
        super("Tribes");
        TribeRegistry.addObserver(this::updateTribes);
    }

    @Override
    protected void selectContent(String selectedValue) {
        super.updateContent(TribeRegistry.getTribeUi(selectedValue));

        revalidate();
    }

    private void updateTribes(){
        Collection<String> tribes = TribeRegistry.getAllTribes();

        super.updateList(tribes);

        revalidate();
    }
}
