package bn73.adventofcode.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrabAligner2 {
    private List<Long> positions;

    public CrabAligner2(Stream<String> input) {
        positions = input.flatMap(line ->
                        Arrays.stream(line.split(",")))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    public long costOfCheapestHorizontalPosition() {
        long maxPosition = positions.stream().mapToLong(Long::longValue).max().getAsLong();

        long fuelCost = Long.MAX_VALUE;
        for (long position = 0; position <= maxPosition; position++) {
            long fuelCostOfPosition = costOfMovingAllCrapsToPosition(position);
            if (fuelCostOfPosition < fuelCost) {
                fuelCost = fuelCostOfPosition;
            }
        }

        return fuelCost;
    }

    private long costOfMovingAllCrapsToPosition(long position) {
        return positions.stream().mapToLong(p -> {
            long distance = Math.abs(p - position);
            return (distance * (distance + 1)) / 2; // application of Gauss formula for summing a series
        }).sum();
    }
}
