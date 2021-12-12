package bn73.adventofcode.day11;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
    private int[][] grid = new int[10][10];
    private long flashCount = 0;

    public Day11(Stream<String> input) {
        List<String> lines = input.collect(Collectors.toList());
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x] = Integer.parseInt("" + lines.get(y).charAt(x));
            }
        }
    }

    public long firstStepOfAllFlashing() {
        long step = 1;
        while (doStep().size() != 100) {
            step++;
        }
        return step;
    }

    public long totalNumberOfFlashesAfterSteps(int steps) {
        for (int step = 0; step < steps; step++) {
            Set<Coordinate> coordinatesThatHasFlashedInStep = doStep();
            flashCount += coordinatesThatHasFlashedInStep.size();
        }
        return flashCount;
    }

    private Set<Coordinate> doStep() {
        increaseAllEnergyLevels();
        Set<Coordinate> coordinatesThatHasFlashedInStep = new HashSet<>();
        discoverNewFlashes(coordinatesThatHasFlashedInStep);
        resetEnergyLevelsOfCoordinatesThatFlashed(coordinatesThatHasFlashedInStep);
        return coordinatesThatHasFlashedInStep;
    }

    private void resetEnergyLevelsOfCoordinatesThatFlashed(Set<Coordinate> coordinatesThatHasFlashedInStep) {
        coordinatesThatHasFlashedInStep.forEach(c -> grid[c.y][c.x] = 0);
    }

    private void discoverNewFlashes(Set<Coordinate> coordinatesThatHasFlashedInStep) {
        Set<Coordinate> newFlashes = new HashSet<>();
        do {
            newFlashes.clear();
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid[y][x] > 9) {
                        Coordinate coordinate = new Coordinate(y, x);
                        if (coordinatesThatHasFlashedInStep.add(coordinate)) {
                            newFlashes.add(coordinate);
                        }
                    }
                }
            }
            newFlashes.forEach(this::increaseEnergyLevelsOfNeighbours);
        } while (!newFlashes.isEmpty());
    }

    private void increaseEnergyLevelsOfNeighbours(Coordinate coordinate) {
        increaseEnergyLevelOf(coordinate.y - 1, coordinate.x - 1);
        increaseEnergyLevelOf(coordinate.y - 1, coordinate.x);
        increaseEnergyLevelOf(coordinate.y - 1, coordinate.x + 1);
        increaseEnergyLevelOf(coordinate.y, coordinate.x - 1);
        increaseEnergyLevelOf(coordinate.y, coordinate.x + 1);
        increaseEnergyLevelOf(coordinate.y + 1, coordinate.x - 1);
        increaseEnergyLevelOf(coordinate.y + 1, coordinate.x);
        increaseEnergyLevelOf(coordinate.y + 1, coordinate.x + 1);
    }

    private void increaseEnergyLevelOf(int y, int x) {
        if (y >= 0 && y < 10 && x >= 0 && x < 10) {
            grid[y][x] += 1;
        }
    }

    private void increaseAllEnergyLevels() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                grid[y][x] += 1;
            }
        }
    }

    static class Coordinate {
        final int x, y;

        public Coordinate(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
