package com.njanor.intervalcalculator;

import java.util.List;

public class IntervalCalculatorApplication
{
    public static void main( String[] args )
    {
        List<Interval> includeIntervals = null;
        List<Interval> excludeIntervals = null;

        try {
            includeIntervals = IntervalInterpreter.interpretIntervals(args[0]);
            excludeIntervals = IntervalInterpreter.interpretIntervals(args[1]);
        } catch (IllegalArgumentException iae) {
            System.out.println("Illegal input");
            return;
        }

        IntervalCalculator intervalCalculator = new IntervalCalculator();
        includeIntervals.forEach(intervalCalculator::includeInterval);
        excludeIntervals.forEach(intervalCalculator::excludeInterval);

        System.out.println(intervalCalculator);
    }
}
