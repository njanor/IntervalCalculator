package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntervalTest
{
    @Test
    public void toString_onInterval_ShouldReturnCorrectlyFormattedRange() {
        final int lowerBound = 2;
        final int upperBound = 20;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Interval interval = new Interval(lowerBound, upperBound);

        String actualOutput = interval.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
