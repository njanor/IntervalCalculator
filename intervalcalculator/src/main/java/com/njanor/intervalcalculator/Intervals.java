package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Intervals {

    private List<ConcreteInterval> allIncludedIntervals = new ArrayList<>();
    private List<ConcreteInterval> allExcludedIntervals = new ArrayList<>();

    public void includeInterval(ConcreteInterval interval) {
        allIncludedIntervals.add(interval);
    }

    public void excludeInterval(int lowerBound, int upperBound) {
        allExcludedIntervals.add(new ConcreteInterval(lowerBound, upperBound));
    }

    @Override
    public String toString() {
        allIncludedIntervals = getNonOverlappingIntervals(allIncludedIntervals);
        allExcludedIntervals = getNonOverlappingIntervals(allExcludedIntervals);
        List<ConcreteInterval> finalIntervals = getIntervalsAndExclude(allIncludedIntervals, allExcludedIntervals);

        if (finalIntervals.isEmpty())
            return "No values";

        return String.join(",", finalIntervals.stream().map(ci -> ci.toString()).collect(Collectors.toList()));
    }

    private List<ConcreteInterval> getNonOverlappingIntervals(List<ConcreteInterval> concreteIntervals) {
        List<ConcreteInterval> finalIncludedIntervals = new ArrayList<>();

        concreteIntervals = concreteIntervals.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < concreteIntervals.size(); i++) {
            int lowerBound = concreteIntervals.get(i).getLowerBound();
            int upperBound = concreteIntervals.get(i).getUpperBound();

            while (i < concreteIntervals.size() - 1 && concreteIntervals.get(i + 1).lowerBoundLowerThan(upperBound)) {
                i++;
                upperBound = Math.max(upperBound, concreteIntervals.get(i).getUpperBound());
            }

            finalIncludedIntervals.add(new ConcreteInterval(lowerBound, upperBound));
        }
        return finalIncludedIntervals;
    }

    private List<ConcreteInterval> getIntervalsAndExclude(List<ConcreteInterval> includedIntervals, List<ConcreteInterval> excludedIntervals) {
        List<ConcreteInterval> intervals = new ArrayList<>();
        for (int i = 0; i < includedIntervals.size(); i++) {
            ConcreteInterval currentIncludeInterval = includedIntervals.get(i);
            for (int j = 0; j < excludedIntervals.size() && currentIncludeInterval != null; j++) {
                ConcreteInterval currentExcludeInterval = excludedIntervals.get(j);
                if (currentIncludeInterval.overlapsWith(currentExcludeInterval)) {
                    if (currentIncludeInterval.lowerBoundLowerThan(currentExcludeInterval.getLowerBound())) {
                        intervals.add(new ConcreteInterval(currentIncludeInterval.getLowerBound(), currentExcludeInterval.getLowerBound() - 1));
                        if (currentIncludeInterval.upperBoundGreaterThan(currentExcludeInterval.getUpperBound())) {
                            currentIncludeInterval = new ConcreteInterval(currentExcludeInterval.getUpperBound() + 1, currentIncludeInterval.getUpperBound());
                        } else {
                            currentIncludeInterval = null;
                        }
                    }
                    if (currentIncludeInterval != null && currentExcludeInterval.lowerBoundLowerThan(currentIncludeInterval.getLowerBound())) {
                        if (currentExcludeInterval.upperBoundGreaterThan(currentIncludeInterval.getUpperBound())) {
                            currentIncludeInterval = null;
                        } else {
                            currentIncludeInterval = new ConcreteInterval(currentExcludeInterval.getUpperBound() + 1, currentIncludeInterval.getUpperBound());
                        }
                    }
                }
            }
            if (currentIncludeInterval != null)
                intervals.add(currentIncludeInterval);
        }
        return intervals;
    }
}

