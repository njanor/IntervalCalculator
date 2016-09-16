package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Intervals {

    private List<ConcreteInterval> includedIntervals = new ArrayList<ConcreteInterval>();

    public void addValues(int lowerBound, int upperBound) {
        if (upperBound < lowerBound) {
            int swap = lowerBound;
            lowerBound = upperBound;
            upperBound = swap;
        }
        includedIntervals.add(new ConcreteInterval(lowerBound, upperBound));
    }

    @Override
    public String toString() {
        if (includedIntervals.isEmpty())
            return "No values";
        return String.join(",", includedIntervals.stream().map(ci -> ci.toString()).collect(Collectors.toList()));
    }

    private class ConcreteInterval {
        private final int lowerBound;
        private final int upperBound;

        public ConcreteInterval(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public String toString() {
            return lowerBound + "-" + upperBound;
        }
    }
}
