# IntervalCalculator

This is a very simple interval calculator. You can provide it with (possibly overlapping) intervals which it should includ, and you may provide it with (possibly overlapping) intervals which is should exclude.

## Running the application from the console

The console runner requires input of included intervals. The format for inputing intervals is

    {lower-bound-1}-{upper-bound-1},{lower-bound-2}-{upper-bound-2}[...]

To run using maven (after having built and tested), type

    mvn exec:java -Dexec.args="{your included intervals here} {your excluded intervals here}"

E.G., if I wanted to run the application, including the intervals 100-200 and 150-300, and exclude the intervals 1-20, 120-220, and 300-330, I would type

    mvn exec:java -Dexec.args="100-200,150-300 1-20,120-220,300-330"

The exclude intervals are optional; simply leave them out if they are not needed.

## Algorithmic complexity (Big-O)

The complexity chosen is O(n*m) where n is the number of included intervals and m the number of excluded intervals. The basic reason is that upon printing the intervals, every excluded interval is compared to every included interval.

This can be simplified to O(n log n) (where n is the number of intervals), but this would increase the complexity of the code, reducing readability as well as maintainability. The complexity here comes from sorting the lists of intervals. After that, you could run through the lists simultaneously, removing the handled elements one at a time.
