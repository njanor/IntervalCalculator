package com.njanor.intervalcalculator;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void except_withIntervalOverlappingStart_returnsTheCorrectInterval() {
        final Interval firstInterval = new Interval(10, 20);
        final Interval secondInterval = new Interval(5, 15);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(1, result.size());
        assertEquals(new Interval(secondInterval.getUpperBound() + 1, firstInterval.getUpperBound()).toString(), result.get(0).toString());
    }

    @Test
    public void except_withIntervalOverlappingEnd_returnsTheCorrectInterval() {
        final Interval firstInterval = new Interval(20, 30);
        final Interval secondInterval = new Interval(25, 35);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(1, result.size());
        assertEquals(new Interval(firstInterval.getLowerBound(), secondInterval.getLowerBound() - 1).toString(), result.get(0).toString());
    }

    @Test
    public void except_withIntervalOverlappingTheMiddle_returnsTheCorrectTwoIntervals() {
        final Interval firstInterval = new Interval(10, 30);
        final Interval secondInterval = new Interval(15, 25);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(2, result.size());
        assertEquals(new Interval(firstInterval.getLowerBound(), secondInterval.getLowerBound() - 1).toString(), result.get(0).toString());
        assertEquals(new Interval(secondInterval.getUpperBound() + 1, firstInterval.getUpperBound()).toString(), result.get(1).toString());
    }

    @Test
    public void except_withIntervalOverlappingEntire_returnsNoIntervals() {
        final Interval firstInterval = new Interval(15, 25);
        final Interval secondInterval = new Interval(10, 35);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(0, result.size());
    }

    @Test
    public void except_withTheSameInterval_returnsNoIntervals() {
        final Interval interval = new Interval(123, 432);

        List<Interval> result = interval.except(interval);

        assertEquals(0, result.size());
    }

    @Test
    public void except_withNonOverlappingInterval_returnsOriginalInterval() {
        final Interval firstInterval = new Interval(40, 45);
        final Interval secondInterval = new Interval(1, 3);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(1, result.size());
        assertEquals(firstInterval.toString(), result.get(0).toString());
    }

    @Test
    public void except_withOverlappingLeavingIntervalsOfOneOnBothSides_returnsTheCorrectIntervals() {
        final Interval firstInterval = new Interval(10, 15);
        final Interval secondInterval = new Interval(11, 14);

        List<Interval> result = firstInterval.except(secondInterval);

        assertEquals(2, result.size());
        assertEquals(new Interval(firstInterval.getLowerBound(), firstInterval.getLowerBound()).toString(), result.get(0).toString());
        assertEquals(new Interval(firstInterval.getUpperBound(), firstInterval.getUpperBound()).toString(), result.get(1).toString());
    }
}
