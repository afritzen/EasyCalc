package controller;

import model.ValueContainer;
import view.EasyCalcView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EasyCalcController implements  ActionListener {

    /**
     * The model holding the data and methods for calculation.
     */
    private ValueContainer valueContainer;
    /**
     * The view displaying the actual application.
     */
    private EasyCalcView view;

    /**
     * Initialize view and model, add listeners.
     * @param valueContainer {@link #valueContainer}
     * @param view {@link #view}
     */
    public EasyCalcController (ValueContainer valueContainer, EasyCalcView view) {
        this.view = view;
        this.valueContainer = valueContainer;
        this.view.getClearBtn().addActionListener(this);
        this.view.getCalculateAllBtn().addActionListener(this);
        this.view.getCopyBtn().addActionListener(this);
        this.view.addKeyListener(new EasyCalcKeyListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getCalculateAllBtn()) {

            try {
                // calculate result for every value
                for (double value : valueContainer.getValues()) {
                    valueContainer.calculate(value);
                }
                // display results
                view.setResults(valueContainer.getValues(), valueContainer.getResults());

            } catch (NumberFormatException nfe) {
                view.displayErrorMessage("Please enter a valid number!");
            }

        } else if (e.getSource() == (view.getClearBtn())) {

            view.clearResults();
            // create new lists instead of emptying the old ones
            valueContainer.setValues(new ArrayList<>());
            valueContainer.setResults(new ArrayList<>());

        } else if (e.getSource() == view.getCopyBtn()) {

            if (view.getCalcResults().getText() == "") {
                return;
            }

            // concatenate values to build a single string
            StringBuilder stringBuilder = new StringBuilder();
            for (double result : valueContainer.getResults()) {
                stringBuilder.append(Double.toString(result) + '\n');
            }
            // copy all values to clipboard
            StringSelection stringSelection = new StringSelection(stringBuilder.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.out.println(stringBuilder.toString());

        }
    }

    /**
     * Inner class for storing an entered value in the model without
     * having to press a button every time (done by enter-key).
     */
    class EasyCalcKeyListener extends KeyAdapter {

        public void keyPressed (KeyEvent keyEvent) {

            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("Test");

                try {
                    // store value in container
                    valueContainer.getValues().add(view.getEnteredValue());
                    view.clearEnteredValue();
                } catch (NumberFormatException e) {
                    view.displayErrorMessage("Please enter a value!");
                }
            }
        }

    }

}
