package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalsTest
{
    @Test
    public void toString_onEmptyInterval_returnsNoValues() {
        Intervals intervals = new Intervals();

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_onSingleInterval_returnsSameAsTheInterval() {
        final int lowerBound = 2;
        final int upperBound = 20;
        Interval interval = new Interval(lowerBound, upperBound);
        Intervals intervals = new Intervals();
        intervals.includeInterval(interval);

        String actualOutput = intervals.toString();

        assertEquals(interval.toString(), actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoRanges_returnsCorrectlyFormattedRanges() {
        final Interval firstInterval = new Interval(2, 4);
        final Interval secondInterval = new Interval(10, 200);
        final String exceptedOutput = firstInterval + "," + secondInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);

        String actualOutput = intervals.toString();

        assertEquals(exceptedOutput, actualOutput);
    }

    @Test
    public void toString_onTwoOverlappingIntervalsAdded_outputsSingleInterval() {
        final Interval firstInterval = new Interval(1, 50);
        final Interval secondInterval = new Interval(20, 100);
        final String expectedOutput = firstInterval.getLowerBound() + "-" + secondInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalAddedInDescendingOrder_outputsThemInAscendingOrder() {
        final Interval higherInterval = new Interval(20, 200);
        final Interval lowerInterval = new Interval(2, 10);
        Intervals intervals = new Intervals();
        intervals.includeInterval(higherInterval);
        intervals.includeInterval(lowerInterval);
        final String expectedOutput = lowerInterval + "," + higherInterval;

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onSomeOverlappingAndSomeNonOverlappingIntervals_outputsTheCorrectIntervals() {
        final Interval firstInterval = new Interval(1, 200);
        final Interval secondInterval = new Interval(202, 250);
        final Interval thirdInterval = new Interval(230, 235);
        final String expectedOutput = firstInterval + "," + secondInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);
        intervals.includeInterval(thirdInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithOverlappingExcludeInterval_outputsTheCorrectTwoIntervals() {
        final Interval includeInterval = new Interval(20, 2000);
        final Interval excludeInterval = new Interval(50, 1000);
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (excludeInterval.getLowerBound() - 1) + "," + (excludeInterval.getUpperBound() + 1) + "-" + includeInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(excludeInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoOverlappingExcludeIntervals_outputsTheCorrectThreeIntervals() {
        final Interval includeInterval = new Interval(100, 1000);
        final Interval firstExcludeInterval = new Interval(101, 150);
        final Interval secondExcludeInterval = new Interval(200, 350);
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (firstExcludeInterval.getLowerBound() - 1) + "," + (firstExcludeInterval.getUpperBound() + 1) + "-" + (secondExcludeInterval.getLowerBound() - 1) + "," + (secondExcludeInterval.getUpperBound() + 1) + "-" + includeInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(firstExcludeInterval);
        intervals.excludeInterval(secondExcludeInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoOutsideOverlappingExcludeInterval_outputsTheCorrectInterval() {
        final Interval includeInterval = new Interval(20, 30);
        final Interval firstExcludedInterval = new Interval(15, 22);
        final Interval secondExcludedInterval = new Interval(28, 40);
        final String expectedOutput = (firstExcludedInterval.getUpperBound() + 1) + "-" + (secondExcludedInterval.getLowerBound() - 1);
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(firstExcludedInterval);
        intervals.excludeInterval(secondExcludedInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalCompletelyExcluded_outputsNoValue() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(new Interval(10, 20));
        intervals.excludeInterval(new Interval(0, 100));

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_onIntervalsWithNonOverlappingExcludeInterval_outputsTheIntervals() {
        final Interval firstIncludeInterval = new Interval(1, 10);
        final Interval secondIncludeInterval = new Interval(100, 200);
        final Interval firstExcludeInterval = new Interval(0, 0);
        final Interval secondExcludeInterval = new Interval(20, 80);
        final Interval thirdExcludeInterval = new Interval(250, 1000);
        final String expectedOutput = firstIncludeInterval + "," + secondIncludeInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstIncludeInterval);
        intervals.includeInterval(secondIncludeInterval);
        intervals.excludeInterval(firstExcludeInterval);
        intervals.excludeInterval(secondExcludeInterval);
        intervals.excludeInterval(thirdExcludeInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onFourthExampleFromCase_worksCorrectly() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(new Interval(10, 100));
        intervals.includeInterval(new Interval(200, 300));
        intervals.includeInterval(new Interval(400, 500));
        intervals.excludeInterval(new Interval(95, 205));
        intervals.excludeInterval(new Interval(410, 420));
        final String expectedOutput = "10-94,206-300,400-409,421-500";

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
