package com.njanor.intervalcalculator;

import java.util.List;

public class IntervalCalculatorApplication
{
    public static void main( String[] args )
    {
        List<ConcreteInterval> includeIntervals = IntervalInterpreter.interpretIntervals(args[0]);
        List<ConcreteInterval> excludeIntervals = IntervalInterpreter.interpretIntervals(args[1]);

        Intervals intervals = new Intervals();
        includeIntervals.forEach(intervals::includeInterval);
        excludeIntervals.stream().forEach(ei -> intervals.excludeInterval(ei.getLowerBound(), ei.getUpperBound()));

        System.out.println(intervals);
    }
}
