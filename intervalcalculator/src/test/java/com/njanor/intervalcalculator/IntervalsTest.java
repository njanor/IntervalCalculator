package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalsTest
{
    @Test
    public void toString_OnEmptyInterval_ShouldReturnNoValues() {
        Intervals intervals = new Intervals();

        assertEquals("No values", intervals.toString());
    }

    @Test
    public void toString_onInterval_ShouldReturnCorrectlyFormattedRange() {
        final int lowerBound = 2;
        final int upperBound = 20;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Intervals intervals = new Intervals();
        intervals.addValues(lowerBound, upperBound);

        String actualOutput = intervals.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
