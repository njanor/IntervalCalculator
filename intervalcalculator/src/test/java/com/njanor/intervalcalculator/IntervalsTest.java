package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalsTest
{
    @Test
    public void toString_OnEmptyInterval_ReturnsNoValues() {
        Intervals intervals = new Intervals();

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_onSingleInterval_returnsSameAsTheInterval() {
        final int lowerBound = 2;
        final int upperBound = 20;
        ConcreteInterval interval = new ConcreteInterval(lowerBound, upperBound);
        Intervals intervals = new Intervals();
        intervals.includeInterval(interval);

        String actualOutput = intervals.toString();

        assertEquals(interval.toString(), actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoRanges_ReturnsCorrectlyFormattedRanges() {
        final ConcreteInterval firstInterval = new ConcreteInterval(2, 4);
        final int secondLowerBound = 10;
        final int secondUpperBound = 200;
        final ConcreteInterval secondInterval = new ConcreteInterval(10, 200);
        final String exceptedOutput = firstInterval + "," + secondInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);

        String actualOutput = intervals.toString();

        assertEquals(exceptedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalAddedInDescendingOrder_OutputsThemInAscendingOrder() {
        final ConcreteInterval higherInterval = new ConcreteInterval(20, 200);
        final ConcreteInterval lowerInterval = new ConcreteInterval(2, 10);
        Intervals intervals = new Intervals();
        intervals.includeInterval(higherInterval);
        intervals.includeInterval(lowerInterval);
        final String expectedOutput = lowerInterval + "," + higherInterval;

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnTwoOverlappingIntervalsAdded_OutputsSingleInterval() {
        final ConcreteInterval firstInterval = new ConcreteInterval(1, 50);
        final ConcreteInterval secondInterval = new ConcreteInterval(20, 100);
        final String expectedOutput = firstInterval.getLowerBound() + "-" + secondInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnSomeOverlappingAndSomeNonOverlappingIntervals_OutputsTheCorrectIntervals() {
        final ConcreteInterval firstInterval = new ConcreteInterval(1, 200);
        final ConcreteInterval secondInterval = new ConcreteInterval(202, 250);
        final ConcreteInterval thirdInterval = new ConcreteInterval(230, 235);
        final String expectedOutput = firstInterval + "," + secondInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstInterval);
        intervals.includeInterval(secondInterval);
        intervals.includeInterval(thirdInterval);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithOverlappingExcludeInterval_OutputsTheCorrectTwoIntervals() {
        final ConcreteInterval includeInterval = new ConcreteInterval(20, 2000);
        final int excludeLowerBound = 50;
        final int excludeUpperBound = 1000;
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (excludeLowerBound - 1) + "," + (excludeUpperBound + 1) + "-" + includeInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(excludeLowerBound, excludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoOverlappingExcludeintervals_OutputsTheCorrectThreeIntervals() {
        final ConcreteInterval includeInterval = new ConcreteInterval(100, 1000);
        final int firstExcludeLowerBound = 101;
        final int firstExcludeUpperBound = 150;
        final int secondExcludeLowerBound = 200;
        final int secondExcludeUpperBound = 350;
        final String expectedOutput = includeInterval.getLowerBound() + "-" + (firstExcludeLowerBound - 1) + "," + (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1) + "," + (secondExcludeUpperBound + 1) + "-" + includeInterval.getUpperBound();
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoOusideOverlappingExcludeInterval_OutputTheCorrectInterval() {
        final ConcreteInterval includeInterval = new ConcreteInterval(20, 30);
        final int firstExcludeLowerBound = 15;
        final int firstExcludeUpperBound = 22;
        final int secondExcludeLowerBound = 28;
        final int secondExcludeUpperBound = 40;
        final String expectedOutput = (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1);
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeInterval);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalCompletelyExcluded_OutputsNoValue() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(new ConcreteInterval(10, 20));
        intervals.excludeInterval(0, 100);

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_OnIntervalsWithNonOverlappingExcludeInterval_OutputsTheIntervals() {
        final ConcreteInterval firstIncludeInterval = new ConcreteInterval(1, 10);
        final ConcreteInterval secondIncludeInterval = new ConcreteInterval(100, 200);
        final int firstExcludeLowerBound = -10;
        final int firstExcludeUpperBound = 0;
        final int secondExcludeLowerBound = 20;
        final int secondExcludeUpperBound = 80;
        final int thirdExcludeLowerBound = 250;
        final int thirdExcludeUpperBound = 1000;
        final String expectedOutput = firstIncludeInterval + "," + secondIncludeInterval;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstIncludeInterval);
        intervals.includeInterval(secondIncludeInterval);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);
        intervals.excludeInterval(thirdExcludeLowerBound, thirdExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnFourtExcampleFromCase_WorksCorrectly() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(new ConcreteInterval(10, 100));
        intervals.includeInterval(new ConcreteInterval(200, 300));
        intervals.includeInterval(new ConcreteInterval(400, 500));
        intervals.excludeInterval(95, 205);
        intervals.excludeInterval(410, 420);
        final String expectedOutput = "10-94,206-300,400-409,421-500";

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
