package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcreteIntervalTest {
    @Test
    public void toString_OnValidInterval_ReturnsCorrectlyFormattedString() {
        final int lowerBound = 1;
        final int upperBound = 10;
        final String expectedOutput = lowerBound + "-" + upperBound;
        ConcreteInterval interval = new ConcreteInterval(lowerBound, upperBound);

        String actualOutput = interval.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void constructInterval_WhenLowerBoundGreaterThanUpperBound_SortsBounds() {
        final int lowerBound = 10;
        final int upperBound = 5;
        final String expectedOutput = upperBound + "-" + lowerBound;
        ConcreteInterval interval = new ConcreteInterval(lowerBound, upperBound);

        String actualOutput = interval.toString();

        assertEquals(expectedOutput, actualOutput);
    }
}
