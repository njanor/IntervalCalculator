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
        List<Interval> actuallyIncludedIntervals = getIncludedIntervalsButRemoveExcludedIntervals(allIncludedIntervals, allExcludedIntervals);

        if (actuallyIncludedIntervals.isEmpty())
            return "No values";

        return String.join(",", actuallyIncludedIntervals.stream().map(Interval::toString).collect(Collectors.toList()));
    }

    private List<Interval> getNonOverlappingIntervals(List<Interval> intervals) {
        List<Interval> nonOverlappingIntervals = new ArrayList<>();

        intervals = intervals.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < intervals.size(); i++) {
            int lowerBound = intervals.get(i).getLowerBound();
            int upperBound = intervals.get(i).getUpperBound();

            while (i < intervals.size() - 1 && intervals.get(i + 1).lowerBoundLowerThanOrEqualTo(upperBound)) {
                i++;
                upperBound = Math.max(upperBound, intervals.get(i).getUpperBound());
            }

            nonOverlappingIntervals.add(new Interval(lowerBound, upperBound));
        }
        return nonOverlappingIntervals;
    }

    private List<Interval> getIncludedIntervalsButRemoveExcludedIntervals(List<Interval> includedIntervals, List<Interval> excludedIntervals) {
        List<Interval> finalIntervals = new ArrayList<>();
        for (Interval currentIncludedInterval : includedIntervals) {
            addIntervalsAfterExcludingRelevantIntervals(finalIntervals, currentIncludedInterval, excludedIntervals);
        }
        return finalIntervals;
    }

    private void addIntervalsAfterExcludingRelevantIntervals(List<Interval> finalIntervals, Interval currentIncludedInterval, List<Interval> excludedIntervals) {
        for (Interval currentExcludedInterval : excludedIntervals) {
            if (currentIncludedInterval.overlapsWith(currentExcludedInterval)) {
                currentIncludedInterval = addIntervalBeforeExcludedIntervalAndReturnIntervalAfterExcludedInterval(finalIntervals, currentIncludedInterval, currentExcludedInterval);
            }
            if (currentIncludedInterval == null)
                return;
        }
        finalIntervals.add(currentIncludedInterval);
    }

    private Interval addIntervalBeforeExcludedIntervalAndReturnIntervalAfterExcludedInterval(List<Interval> finalIntervals, Interval currentIncludedInterval, Interval currentExcludedInterval) {
        if (currentIncludedInterval.lowerBoundLowerThanOrEqualTo(currentExcludedInterval.getLowerBound())) {
            if (currentIncludedInterval.getLowerBound() != currentExcludedInterval.getLowerBound()) {
                finalIntervals.add(new Interval(currentIncludedInterval.getLowerBound(), currentExcludedInterval.getLowerBound() - 1));
            }
            if (currentIncludedInterval.upperBoundGreaterThanOrEqualTo(currentExcludedInterval.getUpperBound())) {
                if (currentIncludedInterval.getUpperBound() != currentExcludedInterval.getUpperBound()) {
                    currentIncludedInterval = new Interval(currentExcludedInterval.getUpperBound() + 1, currentIncludedInterval.getUpperBound());
                }
            } else {
                currentIncludedInterval = null;
            }
        }
        if (currentIncludedInterval != null && currentExcludedInterval.lowerBoundLowerThanOrEqualTo(currentIncludedInterval.getLowerBound())) {
            if (currentExcludedInterval.upperBoundGreaterThanOrEqualTo(currentIncludedInterval.getUpperBound())) {
                currentIncludedInterval = null;
            } else {
                currentIncludedInterval = new Interval(currentExcludedInterval.getUpperBound() + 1, currentIncludedInterval.getUpperBound());
            }
        }
        return currentIncludedInterval;
    }
}

