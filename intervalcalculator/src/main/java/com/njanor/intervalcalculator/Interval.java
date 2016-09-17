package com.njanor.intervalcalculator;

public class Interval implements Comparable<Interval> {
    private final int lowerBound;
    private final int upperBound;

    public Interval(String input) {
        validateInput(input);

        int lowerBoundFromInput = getLowerBoundFromInput(input);
        int upperBoundFromInput = getUpperBoundFromInput(input);

        lowerBound = Math.min(lowerBoundFromInput, upperBoundFromInput);
        upperBound = Math.max(lowerBoundFromInput, upperBoundFromInput);
    }

    private void validateInput(String input) {
        final String intervalPattern = "^\\s*[0-9]+\\-[0-9]+";
        if (input == null || !input.matches(intervalPattern)) {
            throw new IllegalArgumentException();
        }
    }

    private static int getLowerBoundFromInput(String input) {
        return Integer.parseInt(input.substring(0, input.indexOf('-')));
    }

    private static int getUpperBoundFromInput(String input) {
        return Integer.parseInt(input.substring(input.indexOf('-') + 1, input.length()));
    }

    public Interval(int lowerBound, int upperBound) {
        this(lowerBound + "-" + upperBound);
    }

    public boolean lowerBoundLowerThanOrEqualTo(int value) {
        return lowerBound <= value;
    }

    public boolean upperBoundGreaterThanOrEqualTo(int value) {
        return value <= upperBound;
    }

    public boolean overlapsWith(Interval interval) {
        return (lowerBoundLowerThanOrEqualTo(interval.lowerBound) && upperBoundGreaterThanOrEqualTo(interval.lowerBound)) ||
                (lowerBoundLowerThanOrEqualTo(interval.upperBound) && upperBoundGreaterThanOrEqualTo(interval.upperBound)) ||
                (interval.lowerBoundLowerThanOrEqualTo(lowerBound) && interval.upperBoundGreaterThanOrEqualTo(upperBound));
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
    public int compareTo(Interval other) {
        return lowerBound - other.lowerBound;
    }
}
