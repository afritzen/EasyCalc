package model;

import java.util.ArrayList;

/**
 * Holds all glucose values and calculates the exact amount.
 */
public class ValueContainer {

    /**
     * All values that are supposed to be calculated.
     */
    private ArrayList<Double> values = new ArrayList<>();
    /**
     * All results that have been calculated.
     */
    private ArrayList<Double> results = new ArrayList<>();


    /**
     * Default constructor, not needed here.
     */
    public ValueContainer () {}

    /**
     * Performs a simple calculation for glucose concentration.
     * @param value value to be calculated
     * @return result needed for lab and table
     */
    public double calculate(double value, double factor) {
        double result = value*factor*0.01;
        results.add(result);
        return result;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public ArrayList<Double> getResults() {
        return results;
    }

    public void setResults(ArrayList<Double> results) {
        this.results = results;
    }

}
