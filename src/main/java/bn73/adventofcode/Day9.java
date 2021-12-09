package bn73.adventofcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9 {

    private final int height;
    private final int width;
    private int[][] heightmap;


    public Day9(Stream<String> input) {
        List<String> lines = input.collect(Collectors.toList());
        height = lines.size();
        width = lines.get(0).length();
        heightmap = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                heightmap[y][x] = Integer.parseInt("" + lines.get(y).charAt(x));
            }
        }
    }

    private boolean isLowPoint(int y, int x) {
        int currentPoint = point(y, x);
        return currentPoint < point(y + 1, x) &&
                currentPoint < point(y - 1, x) &&
                currentPoint < point(y, x - 1) &&
                currentPoint < point(y, x + 1);
    }

    private int point(int y, int x) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return Integer.MAX_VALUE;
        }
        return heightmap[y][x];
    }

    public long sumOfRiskLevelsOfLowPoints() {
        long sum = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isLowPoint(y, x)) {
                    sum += heightmap[y][x] + 1;
                }
            }
        }

        return sum;
    }

    public long productOfThreeLargestBasins() {
        List<Integer> basinSizes = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isLowPoint(y, x)) {
                    Set<Coordinates> basin = new HashSet<>();
                    extendBasin(basin, y, x);
                    basinSizes.add(basin.size());
                }
            }
        }
        List<Integer> threeLargestBasins = basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());

        return threeLargestBasins.get(0) * threeLargestBasins.get(1) * threeLargestBasins.get(2);
    }

    private void extendBasin(Set<Coordinates> basin, int y, int x) {
        if (!basin.add(new Coordinates(y, x))) {
            // coordinates already included in basin
            return;
        }
        int point = heightmap[y][x];
        if ((x < (width - 1)) && pointIsPartOfBasin(point, heightmap[y][x + 1])) {
            extendBasin(basin, y, x + 1);
        }
        if ((x > 0) && pointIsPartOfBasin(point, heightmap[y][x - 1])) {
            extendBasin(basin, y, x - 1);
        }
        if ((y < (height - 1)) && pointIsPartOfBasin(point, heightmap[y + 1][x])) {
            extendBasin(basin, y + 1, x);
        }
        if ((y > 0) && pointIsPartOfBasin(point, heightmap[y - 1][x])) {
            extendBasin(basin, y - 1, x);
        }
    }

    private boolean pointIsPartOfBasin(int lowPoint, int point) {
        return point != 9 && lowPoint < point;
    }

    static class Coordinates {
        final int x, y;

        public Coordinates(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
