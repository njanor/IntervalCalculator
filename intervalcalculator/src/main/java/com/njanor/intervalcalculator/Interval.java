package com.njanor.intervalcalculator;

public class Interval {
    private final int lowerBound;
    private final int upperBound;

    public Interval(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return lowerBound + "-" + upperBound;
    }
}
