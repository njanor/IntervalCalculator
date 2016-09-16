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
    public void toString_WhenAddingIntervalWithUpperLowerThanLowerBound_SortsTheBoundsInTheOutput() {
        final int lowerBound = 10;
        final int upperBound = 1;
        final String expectedOutput = upperBound + "-" + lowerBound;
        Intervals intervals = new Intervals();
        intervals.addInterval(10, 1);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onInterval_ReturnsCorrectlyFormattedRange() {
        final int lowerBound = 2;
        final int upperBound = 20;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Intervals intervals = new Intervals();
        intervals.addInterval(lowerBound, upperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoRanges_ReturnsCorrectlyFormattedRanges() {
        final int firstLowerBound = 2;
        final int firstUpperBound = 4;
        final int secondLowerBound = 10;
        final int secondUpperBound = 200;
        final String exceptedOutput = firstLowerBound + "-" + firstUpperBound + "," + secondLowerBound + "-" + secondUpperBound;
        Intervals intervals = new Intervals();
        intervals.addInterval(firstLowerBound, firstUpperBound);
        intervals.addInterval(secondLowerBound, secondUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(exceptedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalAddedInDescendingOrder_OutputsThemInAscendingOrder() {
        Intervals intervals = new Intervals();
        intervals.addInterval(20, 200);
        intervals.addInterval(2, 10);
        final String expectedOutput = "2-10,20-200";

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnTwoOverlappingIntervalsAdded_OutputsSingleInterval() {
        final int firstLowerBound = 1;
        final int firstUpperBound = 50;
        final int secondLowerBound = 20;
        final int secondUpperBound = 100;
        final String expectedOutput = firstLowerBound + "-" + secondUpperBound;
        Intervals intervals = new Intervals();
        intervals.addInterval(firstLowerBound, firstUpperBound);
        intervals.addInterval(secondLowerBound, secondUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnSomeOverlappingAndSomeNonOverlappingIntervals_OutputsTheCorrectIntervals() {
        final int firstLowerBound = 1;
        final int firstUpperBound = 200;
        final int secondLowerBound = 202;
        final int secondUpperBound = 250;
        final int thirdLowerBound = 230;
        final int thirdUpperBound = 235;
        final String expectedOutput = firstLowerBound + "-" + firstUpperBound + "," + secondLowerBound + "-" + secondUpperBound;
        Intervals intervals = new Intervals();
        intervals.addInterval(firstLowerBound, firstUpperBound);
        intervals.addInterval(secondLowerBound, secondUpperBound);
        intervals.addInterval(thirdLowerBound, thirdUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
