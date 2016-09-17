package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;

public class IntervalInterpreter {
    public static List<ConcreteInterval> interpretIntervals(String input) {
        input = input.substring(1, input.length() - 1);
        return interpretRestOfIntervals(new ArrayList<>(), input);
    }

    private static List<ConcreteInterval> interpretRestOfIntervals(List<ConcreteInterval> intervals, final String input) {
        if (input.trim().isEmpty())
            return intervals;
        HeadAndTail headAndTail = new HeadAndTail(input);
        intervals.add(new ConcreteInterval(headAndTail.getHead()));
        return interpretRestOfIntervals(intervals, headAndTail.getTail());
    }
}
