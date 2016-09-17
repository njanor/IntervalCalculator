package com.njanor.intervalcalculator;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntervalInterpreterTest {
    @Test
    public void interpretString_WithNoIntervals_ReturnsEmptyList() {
        List<Interval> interpretedIntervals = IntervalInterpreter.interpretIntervals("()");

        assertEquals(0, interpretedIntervals.size());
    }

    @Test
    public void interpretString_WithSingleInterval_ReturnsListWithOneInterval() {
        final int lowerBound = 20;
        final int upperBound = 50;
        List<Interval> interpretedIntervals = IntervalInterpreter.interpretIntervals("(" + lowerBound + "-" + upperBound + ")");

        assertEquals(1, interpretedIntervals.size());
        assertEquals(lowerBound, interpretedIntervals.get(0).getLowerBound());
        assertEquals(upperBound, interpretedIntervals.get(0).getUpperBound());
    }

    @Test
    public void interpretString_WithMultipleIntervals_ReturnsListWithAllIntervals() {
        final String input = "2-10,11-100,250-300";
        List<Interval> interpretedIntervals = IntervalInterpreter.interpretIntervals("(" + input + ")");
        assertEquals(3, interpretedIntervals.size());
        Intervals intervals = new Intervals();
        interpretedIntervals.forEach(intervals::includeInterval);
        assertEquals(input, intervals.toString());
    }
}
