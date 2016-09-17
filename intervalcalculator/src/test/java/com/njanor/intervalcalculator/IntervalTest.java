package com.njanor.intervalcalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class IntervalTest {
    @Test
    public void toString_OnValidInterval_ReturnsCorrectlyFormattedString() {
        final int lowerBound = 1;
        final int upperBound = 10;
        final String expectedOutput = lowerBound + "-" + upperBound;
        Interval interval = new Interval(lowerBound, upperBound);

        String actualOutput = interval.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void constructInterval_whenLowerBoundGreaterThanUpperBound_sortsBounds() {
        final int lowerBound = 10;
        final int upperBound = 5;

        Interval interval = new Interval(lowerBound, upperBound);

        assertEquals(lowerBound, interval.getUpperBound());
        assertEquals(upperBound, interval.getLowerBound());
    }

    @Test
    public void construct_WithStringInput_generatesCorrectInterval() {
        final int lowerBound = 2;
        final int upperBound = 200;
        final String input = lowerBound + "-" + upperBound;

        Interval interval = new Interval(input);

        assertEquals(lowerBound, interval.getLowerBound());
        assertEquals(upperBound, interval.getUpperBound());
    }

    @Test
    public void construct_withInvalidString_throwsException() {
        try {
            new Interval("1-2-3");
            fail();
        } catch (IllegalArgumentException iae) {
            // Pass the test
        }
    }

    @Test
    public void construct_withStringBeeingNull_throwsException() {
        try {
            new Interval(null);
            fail();
        } catch (IllegalArgumentException iae) {
            // Pass
        }
    }

    @Test
    public void construct_withIllegalCharactersInInput_throwsException() {
        try {
            new Interval("abc");
            fail();
        } catch (IllegalArgumentException iae) {
            //Pass
        }
    }
}
