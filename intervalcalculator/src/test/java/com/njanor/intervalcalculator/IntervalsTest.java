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
        intervals.includeInterval(10, 1);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_onInterval_ReturnsCorrectlyFormattedRange() {
        final int lowerBound = 2;
        final int upperBound = 20;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Intervals intervals = new Intervals();
        intervals.includeInterval(lowerBound, upperBound);

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
        intervals.includeInterval(firstLowerBound, firstUpperBound);
        intervals.includeInterval(secondLowerBound, secondUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(exceptedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalAddedInDescendingOrder_OutputsThemInAscendingOrder() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(20, 200);
        intervals.includeInterval(2, 10);
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
        intervals.includeInterval(firstLowerBound, firstUpperBound);
        intervals.includeInterval(secondLowerBound, secondUpperBound);

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
        intervals.includeInterval(firstLowerBound, firstUpperBound);
        intervals.includeInterval(secondLowerBound, secondUpperBound);
        intervals.includeInterval(thirdLowerBound, thirdUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithOverlappingExcludeInterval_OutputsTheCorrectTwoIntervals() {
        final int includeLowerBound = 20;
        final int includeUpperBound = 2000;
        final int excludeLowerBound = 50;
        final int excludeUpperBound = 1000;
        final String expectedOutput = includeLowerBound + "-" + (excludeLowerBound - 1) + "," + (excludeUpperBound + 1) + "-" + includeUpperBound;
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeLowerBound, includeUpperBound);
        intervals.excludeInterval(excludeLowerBound, excludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoOverlappingExcludeintervals_OutputsTheCorrectThreeIntervals() {
        final int includeLowerBound = 100;
        final int includeUpperBound = 1000;
        final int firstExcludeLowerBound = 101;
        final int firstExcludeUpperBound = 150;
        final int secondExcludeLowerBound = 200;
        final int secondExcludeUpperBound = 350;
        final String expectedOutput = includeLowerBound + "-" + (firstExcludeLowerBound - 1) + "," + (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1) + "," + (secondExcludeUpperBound + 1) + "-" + includeUpperBound;
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeLowerBound, includeUpperBound);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalWithTwoOusideOverlappingExcludeInterval_OutputTheCorrectInterval() {
        final int includeLowerBound = 20;
        final int includeUpperBound = 30;
        final int firstExcludeLowerBound = 15;
        final int firstExcludeUpperBound = 22;
        final int secondExcludeLowerBound = 28;
        final int secondExcludeUpperBound = 40;
        final String expectedOutput = (firstExcludeUpperBound + 1) + "-" + (secondExcludeLowerBound - 1);
        Intervals intervals = new Intervals();
        intervals.includeInterval(includeLowerBound, includeUpperBound);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnIntervalCompletelyExcluded_OutputsNoValue() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(10, 20);
        intervals.excludeInterval(0, 100);

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_OnIntervalsWithNonOverlappingExcludeInterval_OutputsTheIntervals() {
        final int firstIncludeLowerBound = 1;
        final int firstIncludeUpperBound = 10;
        final int secondIncludeLowerBound = 100;
        final int secondIncludeUpperBound = 200;
        final int firstExcludeLowerBound = -10;
        final int firstExcludeUpperBound = 0;
        final int secondExcludeLowerBound = 20;
        final int secondExcludeUpperBound = 80;
        final int thirdExcludeLowerBound = 250;
        final int thirdExcludeUpperBound = 1000;
        final String expectedOutput = firstIncludeLowerBound + "-" + firstIncludeUpperBound + "," + secondIncludeLowerBound + "-" + secondIncludeUpperBound;
        Intervals intervals = new Intervals();
        intervals.includeInterval(firstIncludeLowerBound, firstIncludeUpperBound);
        intervals.includeInterval(secondIncludeLowerBound, secondIncludeUpperBound);
        intervals.excludeInterval(firstExcludeLowerBound, firstExcludeUpperBound);
        intervals.excludeInterval(secondExcludeLowerBound, secondExcludeUpperBound);
        intervals.excludeInterval(thirdExcludeLowerBound, thirdExcludeUpperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void toString_OnFourtExcampleFromCase_WorksCorrectly() {
        Intervals intervals = new Intervals();
        intervals.includeInterval(10, 100);
        intervals.includeInterval(200, 300);
        intervals.includeInterval(400, 500);
        intervals.excludeInterval(95, 205);
        intervals.excludeInterval(410, 420);
        final String expectedOutput = "10-94,206-300,400-409,421-500";

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
