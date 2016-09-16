package com.njanor.intervalcalculator;

public class Intervals {

    private ConcreteInterval concreteInterval;

    public void addValues(int lowerBound, int upperBound) {
        concreteInterval = new ConcreteInterval(lowerBound, upperBound);
    }

    @Override
    public String toString() {
        if (concreteInterval == null)
            return "No values";
        return concreteInterval.toString();
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
