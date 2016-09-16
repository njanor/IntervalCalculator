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
        intervals.addValues(10, 1);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onInterval_ReturnsCorrectlyFormattedRange() {
        final int lowerBound = 2;
        final int upperBound = 20;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Intervals intervals = new Intervals();
        intervals.addValues(lowerBound, upperBound);

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
        intervals.addValues(firstLowerBound, firstUpperBound);
        intervals.addValues(secondLowerBound, secondUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(exceptedOutput, actualOutput);
    }
}
