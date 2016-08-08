package main;

import controller.EasyCalcController;
import model.ValueContainer;
import view.EasyCalcView;

public class EasyCalcMain {

    /**
     * Sets up application and initializes all components needed for MVC.
     * @param args commandline arguments
     */
    public static void main (String[] args) {

        ValueContainer valueContainer = new ValueContainer();
        EasyCalcView easyCalcView = new EasyCalcView(null);
        EasyCalcController easyCalcController = new EasyCalcController(valueContainer, easyCalcView);

        // display main window
        easyCalcView.setVisible(true);
    }

}
