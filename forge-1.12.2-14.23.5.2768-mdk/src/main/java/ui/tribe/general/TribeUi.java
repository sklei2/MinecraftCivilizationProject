package ui.tribe.general;

import scala.actors.threadpool.Arrays;

public class TribeUi extends DisplayWithList {

    private final String name;
    private final TribeQueuesUi tribeQueuesUi;

    public TribeUi(String name, TribeQueuesUi tribeQueuesUi){
        super("Views", Arrays.asList(new String[]{"Queues"}));

        this.name = name;

        this.tribeQueuesUi = tribeQueuesUi;
    }

    @Override
    protected void selectContent(int index, String selectedValue) {
        switch (selectedValue){
            case "Queues":
                super.updateContent(tribeQueuesUi);
                break;
            case "None":
                clearContent();
                break;
        }

        super.selectContent(index, selectedValue);

        revalidate();
    }
}
