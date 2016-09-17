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

        Intervals intervals = new Intervals();
        includeIntervals.forEach(intervals::includeInterval);
        excludeIntervals.forEach(intervals::excludeInterval);

        System.out.println(intervals);
    }
}
