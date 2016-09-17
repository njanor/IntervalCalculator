package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;

public class IntervalInterpreter {
    public static List<Interval> interpretIntervals(String input) {
        input = input.substring(1, input.length() - 1);
        return interpretRestOfIntervals(new ArrayList<>(), input);
    }

    private static List<Interval> interpretRestOfIntervals(List<Interval> intervals, final String input) {
        if (input.trim().isEmpty())
            return intervals;
        HeadAndTail headAndTail = new HeadAndTail(input);
        intervals.add(new Interval(headAndTail.getHead()));
        return interpretRestOfIntervals(intervals, headAndTail.getTail());
    }
}
