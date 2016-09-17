package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalCalculatorTest
{
    @Test
    public void toString_onEmptyInterval_returnsNoValues() {
        IntervalCalculator intervalCalculator = new IntervalCalculator();

        assertEquals("No values", intervalCalculator.toString());
    }

    @Test
    public void toString_onSingleInterval_returnsSameAsTheInterval() {
        final int lowerBound = 2;
        final int upperBound = 20;
        Interval interval = new Interval(lowerBound, upperBound);
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(interval);

        String actualOutput = intervalCalculator.toString();

        assertEquals(interval.toString(), actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoRanges_returnsCorrectlyFormattedRanges() {
        final Interval firstInterval = new Interval(2, 4);
        final Interval secondInterval = new Interval(10, 200);
        final String exceptedOutput = firstInterval + "," + secondInterval;
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(firstInterval);
        intervalCalculator.includeInterval(secondInterval);

        String actualOutput = intervalCalculator.toString();

        assertEquals(exceptedOutput, actualOutput);
    }

    @Test
    public void toString_onTwoOverlappingIntervalsAdded_outputsSingleInterval() {
        final Interval firstInterval = new Interval(1, 50);
        final Interval secondInterval = new Interval(20, 100);
        final String expectedOutput = firstInterval.getLowerBound() + "-" + secondInterval.getUpperBound();
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(firstInterval);
        intervalCalculator.includeInterval(secondInterval);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalAddedInDescendingOrder_outputsThemInAscendingOrder() {
        final Interval higherInterval = new Interval(20, 200);
        final Interval lowerInterval = new Interval(2, 10);
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(higherInterval);
        intervalCalculator.includeInterval(lowerInterval);
        final String expectedOutput = lowerInterval + "," + higherInterval;

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onSomeOverlappingAndSomeNonOverlappingIntervals_outputsTheCorrectIntervals() {
        final Interval firstInterval = new Interval(1, 200);
        final Interval secondInterval = new Interval(202, 250);
        final Interval thirdInterval = new Interval(230, 235);
        final String expectedOutput = firstInterval + "," + secondInterval;
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(firstInterval);
        intervalCalculator.includeInterval(secondInterval);
        intervalCalculator.includeInterval(thirdInterval);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithOverlappingExcludeInterval_outputsTheCorrectTwoIntervals() {
        final Interval includeInterval = new Interval(20, 2000);
        final int excludeLowerBound = 50;
        final int excludeUpperBound = 1000;
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (excludeLowerBound - 1) + "," + (excludeUpperBound + 1) + "-" + includeInterval.getUpperBound();
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(includeInterval);
        intervalCalculator.excludeInterval(excludeLowerBound, excludeUpperBound);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoOverlappingExcludeIntervals_outputsTheCorrectThreeIntervals() {
        final Interval includeInterval = new Interval(100, 1000);
        final int firstExcludeLowerBound = 101;
        final int firstExcludeUpperBound = 150;
        final int secondExcludeLowerBound = 200;
        final int secondExcludeUpperBound = 350;
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (firstExcludeLowerBound - 1) + "," + (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1) + "," + (secondExcludeUpperBound + 1) + "-" + includeInterval.getUpperBound();
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(includeInterval);
        intervalCalculator.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervalCalculator.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalWithTwoOutsideOverlappingExcludeInterval_outputsTheCorrectInterval() {
        final Interval includeInterval = new Interval(20, 30);
        final int firstExcludeLowerBound = 15;
        final int firstExcludeUpperBound = 22;
        final int secondExcludeLowerBound = 28;
        final int secondExcludeUpperBound = 40;
        final String expectedOutput = (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1);
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(includeInterval);
        intervalCalculator.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervalCalculator.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onIntervalCompletelyExcluded_outputsNoValue() {
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(new Interval(10, 20));
        intervalCalculator.excludeInterval(0, 100);

        assertEquals("No values", intervalCalculator.toString());
    }

    @Test
    public void toString_onIntervalsWithNonOverlappingExcludeInterval_outputsTheIntervals() {
        final Interval firstIncludeInterval = new Interval(1, 10);
        final Interval secondIncludeInterval = new Interval(100, 200);
        final int firstExcludeLowerBound = 0;
        final int firstExcludeUpperBound = 0;
        final int secondExcludeLowerBound = 20;
        final int secondExcludeUpperBound = 80;
        final int thirdExcludeLowerBound = 250;
        final int thirdExcludeUpperBound = 1000;
        final String expectedOutput = firstIncludeInterval + "," + secondIncludeInterval;
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(firstIncludeInterval);
        intervalCalculator.includeInterval(secondIncludeInterval);
        intervalCalculator.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervalCalculator.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);
        intervalCalculator.excludeInterval(thirdExcludeLowerBound, thirdExcludeUpperBound);

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onFourthExampleFromCase_worksCorrectly() {
        IntervalCalculator intervalCalculator = new IntervalCalculator();
        intervalCalculator.includeInterval(new Interval(10, 100));
        intervalCalculator.includeInterval(new Interval(200, 300));
        intervalCalculator.includeInterval(new Interval(400, 500));
        intervalCalculator.excludeInterval(95, 205);
        intervalCalculator.excludeInterval(410, 420);
        final String expectedOutput = "10-94,206-300,400-409,421-500";

        String actualOutput = intervalCalculator.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
