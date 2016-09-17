package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;

public class IntervalInterpreter {
    static List<Interval> interpretIntervalsFromString(String input) {
        return interpretRestOfIntervals(new ArrayList<>(), input);
    }

    private static List<Interval> interpretRestOfIntervals(List<Interval> intervals, final String remainingInput) {
        if (remainingInput.trim().isEmpty())
            return intervals;
        HeadAndTail headAndTail = new HeadAndTail(remainingInput);
        intervals.add(new Interval(headAndTail.getHead()));
        return interpretRestOfIntervals(intervals, headAndTail.getTail());
    }
}
