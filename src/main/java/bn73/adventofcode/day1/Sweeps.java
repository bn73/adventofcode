package bn73.adventofcode.day1;

import java.io.BufferedReader;
import java.util.Arrays;

public class Sweeps {
    final long[] sweeps;

    public Sweeps(long[] sweeps) {
        this.sweeps = Arrays.copyOf(sweeps, sweeps.length);
    }

    public Sweeps(BufferedReader reader) {
        this.sweeps = reader.lines().mapToLong(Long::parseLong).toArray();
    }

    public long countIncreasingDepths1() {
        /*
        final long numberOfSweeps = sweeps.length;
        if (numberOfSweeps <= 1) return 0;

        long increasingDepths = 0;
        for (int i = 1; i < numberOfSweeps; i++) {
            long previousSweep = sweeps[i - 1];
            long currentSweep = sweeps[i];
            if (previousSweep < currentSweep) {
                increasingDepths++;
            }
        }
        return increasingDepths;
         */

        return countIncreasingDepths2(1);
    }

    public long countIncreasingDepths2(final long windowSize) {
        final long numberOfSweeps = sweeps.length;

        long increasingDepths = 0;
        for (int i = 1; i + windowSize - 1 < numberOfSweeps; i++) {
            long sumOfPreviousWindow = 0;
            long sumOfCurrentWindow = 0;
            for (int j = 0; j < windowSize; j++) {
                sumOfPreviousWindow += sweeps[(i - 1) + j];
                sumOfCurrentWindow += sweeps[i + j];
            }
            if (sumOfCurrentWindow > sumOfPreviousWindow) {
                increasingDepths++;
            }
        }
        return increasingDepths;
    }
}
