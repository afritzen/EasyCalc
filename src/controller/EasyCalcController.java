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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Listens for input in the view and processes it within the model.
 */
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
     * Converts value to desired format (xx,xx).
    */
    private DecimalFormat decimalFormat = new DecimalFormat("##.##");

    /**
     * Initialize view and model, add listeners.
     * @param valueContainer {@link #valueContainer}
     * @param view {@link #view}
     */
    public EasyCalcController (ValueContainer valueContainer, EasyCalcView view) {
        this.view = view;
        this.valueContainer = valueContainer;
        this.view.getFactorsToChoose().addActionListener(this);
        this.view.getClearBtn().addActionListener(this);
        this.view.getCalculateAllBtn().addActionListener(this);
        this.view.getCopyBtn().addActionListener(this);
        this.view.getInstructionsBtn().addActionListener(this);
        this.view.addKeyListener(new EasyCalcKeyListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getCalculateAllBtn()) {

                // calculate result for every value
                for (double value : valueContainer.getValues()) {
                    if (view.getFactorsToChoose().getSelectedItem() == view.FACTOR_HALF) {
                        valueContainer.calculate(value, 8);
                    } else {
                        valueContainer.calculate(value, 4);
                    }
                }
                // display results
                view.setResults(valueContainer.getValues(), valueContainer.getResults());

        } else if (e.getSource() == view.getClearBtn()) {

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
                stringBuilder.append(decimalFormat.format(result) + '\n');
            }
            // copy all values to clipboard
            StringSelection stringSelection = new StringSelection(stringBuilder.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        } else if (e.getSource() == view.getInstructionsBtn()) {
            view.displayMessage("Press Enter to store an entered value \n" +
                    "Before calculating, chose a factor the value has to be multiplied with.");
        }
    }

    /**
     * Inner class for storing an entered value in the model without
     * having to press a button every time (done by enter-key).
     */
    class EasyCalcKeyListener extends KeyAdapter {

        public void keyPressed (KeyEvent keyEvent) {

            if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {

                try {
                    // store value in container
                    valueContainer.getValues().add(view.getEnteredValue());
                    view.clearEnteredValue();
                } catch (NumberFormatException e) {
                    view.displayMessage("Please enter a valid number!");
                }
            }
        }

    }

}
