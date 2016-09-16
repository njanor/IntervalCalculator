package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Intervals {

    private List<ConcreteInterval> allIncludedIntervals = new ArrayList<ConcreteInterval>();

    public void includeInterval(int lowerBound, int upperBound) {
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

        List<ConcreteInterval> finalIncludedIntervals = getNonOverlappingIntervals(allIncludedIntervals);

        return String.join(",", finalIncludedIntervals.stream().map(ci -> ci.toString()).collect(Collectors.toList()));
    }

    private List<ConcreteInterval> getNonOverlappingIntervals(List<ConcreteInterval> concreteIntervals) {
        List<ConcreteInterval> finalIncludedIntervals = new ArrayList<>();

        concreteIntervals = concreteIntervals.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < concreteIntervals.size(); i++) {
            int lowerBound = concreteIntervals.get(i).lowerBound;
            int upperBound = concreteIntervals.get(i).upperBound;

            while (i < concreteIntervals.size() - 1 && concreteIntervals.get(i + 1).lowerBoundLowerThan(upperBound)) {
                i++;
                upperBound = Math.max(upperBound, concreteIntervals.get(i).upperBound);
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
