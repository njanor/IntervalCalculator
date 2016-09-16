package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Intervals {

    private List<ConcreteInterval> allIncludedIntervals = new ArrayList<ConcreteInterval>();

    public void addInterval(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            int swap = lowerBound;
            lowerBound = upperBound;
            upperBound = swap;
        }
        allIncludedIntervals.add(new ConcreteInterval(lowerBound, upperBound));
    }

    @Override
    public String toString() {
        if (allIncludedIntervals.isEmpty())
            return "No values";

        allIncludedIntervals = allIncludedIntervals.stream().sorted().collect(Collectors.toList());
        List<ConcreteInterval> finalIncludedIntervals = getNonOverlappingIncludedIntervals();

        return String.join(",", finalIncludedIntervals.stream().map(ci -> ci.toString()).collect(Collectors.toList()));
    }

    private List<ConcreteInterval> getNonOverlappingIncludedIntervals() {
        List<ConcreteInterval> finalIncludedIntervals = new ArrayList<>();

        for (int i = 0; i < allIncludedIntervals.size(); i++) {
            int lowerBound = allIncludedIntervals.get(i).lowerBound;
            int upperBound = allIncludedIntervals.get(i).upperBound;

            while (i < allIncludedIntervals.size() - 1 && allIncludedIntervals.get(i + 1).lowerBoundLowerThan(upperBound)) {
                i++;
                upperBound = Math.max(upperBound, allIncludedIntervals.get(i).upperBound);
            }

            finalIncludedIntervals.add(new ConcreteInterval(lowerBound, upperBound));
        }
        return finalIncludedIntervals;
    }

    private class ConcreteInterval implements Comparable<ConcreteInterval> {
        private final int lowerBound;
        private final int upperBound;

        public ConcreteInterval(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public boolean lowerBoundLowerThan(int value) {
            return lowerBound <= value;
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
}
