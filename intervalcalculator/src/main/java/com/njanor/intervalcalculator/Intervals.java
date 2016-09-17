package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Intervals {

    private List<Interval> allIncludedIntervals = new ArrayList<>();
    private List<Interval> allExcludedIntervals = new ArrayList<>();

    public void includeInterval(Interval interval) {
        allIncludedIntervals.add(interval);
    }

    public void excludeInterval(Interval interval) {
        allExcludedIntervals.add(interval);
    }

    @Override
    public String toString() {
        allIncludedIntervals = getNonOverlappingIntervals(allIncludedIntervals);
        allExcludedIntervals = getNonOverlappingIntervals(allExcludedIntervals);
        List<Interval> finalIntervals = getIntervalsAndExclude(allIncludedIntervals, allExcludedIntervals);

        if (finalIntervals.isEmpty())
            return "No values";

        return String.join(",", finalIntervals.stream().map(ci -> ci.toString()).collect(Collectors.toList()));
    }

    private List<Interval> getNonOverlappingIntervals(List<Interval> intervals) {
        List<Interval> finalIncludedIntervals = new ArrayList<>();

        intervals = intervals.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < intervals.size(); i++) {
            int lowerBound = intervals.get(i).getLowerBound();
            int upperBound = intervals.get(i).getUpperBound();

            while (i < intervals.size() - 1 && intervals.get(i + 1).lowerBoundLowerThan(upperBound)) {
                i++;
                upperBound = Math.max(upperBound, intervals.get(i).getUpperBound());
            }

            finalIncludedIntervals.add(new Interval(lowerBound, upperBound));
        }
        return finalIncludedIntervals;
    }

    private List<Interval> getIntervalsAndExclude(List<Interval> includedIntervals, List<Interval> excludedIntervals) {
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < includedIntervals.size(); i++) {
            Interval currentIncludeInterval = includedIntervals.get(i);
            for (int j = 0; j < excludedIntervals.size() && currentIncludeInterval != null; j++) {
                Interval currentExcludeInterval = excludedIntervals.get(j);
                if (currentIncludeInterval.overlapsWith(currentExcludeInterval)) {
                    if (currentIncludeInterval.lowerBoundLowerThan(currentExcludeInterval.getLowerBound())) {
                        intervals.add(new Interval(currentIncludeInterval.getLowerBound(), currentExcludeInterval.getLowerBound() - 1));
                        if (currentIncludeInterval.upperBoundGreaterThan(currentExcludeInterval.getUpperBound())) {
                            currentIncludeInterval = new Interval(currentExcludeInterval.getUpperBound() + 1, currentIncludeInterval.getUpperBound());
                        } else {
                            currentIncludeInterval = null;
                        }
                    }
                    if (currentIncludeInterval != null && currentExcludeInterval.lowerBoundLowerThan(currentIncludeInterval.getLowerBound())) {
                        if (currentExcludeInterval.upperBoundGreaterThan(currentIncludeInterval.getUpperBound())) {
                            currentIncludeInterval = null;
                        } else {
                            currentIncludeInterval = new Interval(currentExcludeInterval.getUpperBound() + 1, currentIncludeInterval.getUpperBound());
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

