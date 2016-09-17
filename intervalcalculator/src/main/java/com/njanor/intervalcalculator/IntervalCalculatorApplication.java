package com.njanor.intervalcalculator;

import java.util.List;

public class IntervalCalculatorApplication
{
    public static void main( String[] args )
    {
        List<Interval> intervalsToInclude = null;
        List<Interval> intervalsToExclude = null;

        try {
            intervalsToInclude = IntervalInterpreter.interpretIntervalsFromString(args[0]);
            intervalsToExclude = IntervalInterpreter.interpretIntervalsFromString(args[1]);
        } catch (IllegalArgumentException iae) {
            System.out.println("Illegal input");
            return;
        }

        Intervals intervals = new Intervals();
        intervalsToInclude.forEach(intervals::includeInterval);
        intervalsToExclude.forEach(intervals::excludeInterval);

        System.out.println(intervals);
    }
}
