package view;

import controller.EasyCalcController;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * The view for the application containing all buttons and textfields.
 */
public class EasyCalcView extends JFrame{

    public static final int SIZE = 600;
    public static final String TITLE = "EasyCalc 1.0";

    private JLabel valueLabel = new JLabel("Value:");
    private JTextField enterValue = new JTextField(10);
    private JButton calculateAllBtn = new JButton("Calculate all");
    private JLabel resultLabel = new JLabel("Results:");
    private JButton clearBtn = new JButton("Clear");
    private JButton copyBtn = new JButton("Copy to clipboard");
    private JTextArea calcResults = new JTextArea(30, 15);

    /**
     * Initialize and add components.
     */
    public EasyCalcView (EasyCalcController controller) {

        JPanel easyCalcPanel = new JPanel();
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE, SIZE);

        // add all components
        easyCalcPanel.add(valueLabel);
        easyCalcPanel.add(enterValue);
        easyCalcPanel.add(calculateAllBtn);
        easyCalcPanel.add(resultLabel);
        easyCalcPanel.add(calcResults);
        // make textarea read-only
        calcResults.setEditable(false);
        easyCalcPanel.add(clearBtn);
        easyCalcPanel.add(copyBtn);
        this.add(easyCalcPanel);
    }

    public double getEnteredValue () {
        return Double.parseDouble(enterValue.getText());
    }

    public void clearEnteredValue() {
        enterValue.setText("");
    }

    public void clearResults() {
        calcResults.setText("");
    }

    /**
     * Display entered values and calculated results in textarea.
     * @param values all values
     * @param results all results
     */
    public void setResults(ArrayList<Double> values, ArrayList<Double> results) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            stringBuilder.append(Double.toString(values.get(i)) + " --> " + results.get(i) + '\n');
        }
        calcResults.setText(stringBuilder.toString());
    }

    public JButton getCalculateAllBtn() {
        return calculateAllBtn;
    }

    public JButton getClearBtn() {
        return clearBtn;
    }

    public JButton getCopyBtn() {
        return copyBtn;
    }

    public JTextArea getCalcResults() {
        return calcResults;
    }

    public void addKeyListener(KeyListener keyListener) {
        enterValue.addKeyListener(keyListener);
    }

    public void displayErrorMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
