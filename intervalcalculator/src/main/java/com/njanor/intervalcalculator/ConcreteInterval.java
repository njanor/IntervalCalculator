package com.njanor.intervalcalculator;

public class ConcreteInterval implements Comparable<ConcreteInterval> {
    private final int lowerBound;
    private final int upperBound;

    public ConcreteInterval(String input) {
        this(getLowerBoundFromInput(input), getUpperBoundFromInput(input));
    }

    private static int getLowerBoundFromInput(String input) {
        return Integer.parseInt(input.substring(0, input.indexOf('-')));
    }

    private static int getUpperBoundFromInput(String input) {
        return Integer.parseInt(input.substring(input.indexOf('-') + 1, input.length()));
    }

    public ConcreteInterval(int lowerBound, int upperBound) {
        this.lowerBound = Math.min(lowerBound, upperBound);
        this.upperBound = Math.max(lowerBound, upperBound);
    }

    public boolean lowerBoundLowerThan(int value) {
        return lowerBound <= value;
    }

    public boolean upperBoundGreaterThan(int value) {
        return value <= upperBound;
    }

    public boolean overlapsWith(ConcreteInterval interval) {
        return (lowerBoundLowerThan(interval.lowerBound) && upperBoundGreaterThan(interval.lowerBound)) ||
                (lowerBoundLowerThan(interval.upperBound) && upperBoundGreaterThan(interval.upperBound)) ||
                (interval.lowerBoundLowerThan(lowerBound) && interval.upperBoundGreaterThan(upperBound));
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public String toString() {
        return lowerBound + "-" + upperBound;
    }

    @Override
    public int compareTo(ConcreteInterval other) {
        return lowerBound - other.lowerBound;
    }
}
