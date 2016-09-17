package com.njanor.intervalcalculator;

import java.util.ArrayList;
import java.util.List;

public class IntervalCalculatorApplication
{
    public static void main( String[] args )
    {
        List<Interval> intervalsToInclude = null;
        List<Interval> intervalsToExclude = null;

        if (args.length == 0) {
            System.out.println("Requires input of included intervals.");
            System.out.println();
            System.out.println("Example of input with only included intervals:");
            System.out.println("20-30,50-40,10-45");
            System.out.println();
            System.out.println("Example of input with both included and excluded intervals:");
            System.out.println("20-30,100-200 90-101,123-321");
            return;
        }

        try {
            intervalsToInclude = IntervalInterpreter.interpretIntervalsFromString(args[0]);
            if (args.length > 1) {
                intervalsToExclude = IntervalInterpreter.interpretIntervalsFromString(args[1]);
            } else {
                intervalsToExclude = new ArrayList<>();
            }
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
