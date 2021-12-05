package bn73.adventofcode.day5;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {
    private final long[][] grid;
    private final boolean supportsDiagonalLines;

    public Day5(Stream<String> input, boolean supportsDiagonalLines) {
        List<VentLine> ventLines = input.map(VentLine::new).collect(Collectors.toList());
        this.supportsDiagonalLines = supportsDiagonalLines;
        this.grid = instantiateGrid(ventLines);
        renderGrid(ventLines);
    }

    private static long[][] instantiateGrid(List<VentLine> ventLines) {
        int gridXSize = ventLines
                .stream()
                .mapToInt(line -> Integer.max(line.x1, line.x2))
                .max()
                .getAsInt() + 1;
        int gridYSize = ventLines
                .stream()
                .mapToInt(line -> Integer.max(line.y1, line.y2))
                .max()
                .getAsInt() + 1;

        return new long[gridYSize][gridXSize];
    }

    private void renderGrid(List<VentLine> ventLines) {
        for (VentLine line : ventLines) {
            if (line.x1 == line.x2) {
                renderVerticalLine(line);
            } else if (line.y1 == line.y2) {
                renderHorizontalLine(line);
            } else if (supportsDiagonalLines) {
                renderDiagonalLine(line);
            }
        }
    }

    private void renderDiagonalLine(VentLine line) {
        int verticalDirection = Integer.compare(line.y1, line.y2);
        int horizontalDirection = Integer.compare(line.x1, line.x2);
        int numberOfPoints = Math.abs(line.y2 - line.y1) + 1;

        int x = line.x1;
        int y = line.y1;

        for (int i = 0; i < numberOfPoints; i++, y -= verticalDirection, x -= horizontalDirection) {
            grid[y][x]++;
        }
    }

    private void renderHorizontalLine(VentLine line) {
        int minX = Integer.min(line.x1, line.x2);
        int maxX = Integer.max(line.x1, line.x2);
        for (int x = minX; x <= maxX; x++) {
            try {
                grid[line.y1][x]++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            }
        }
    }

    private void renderVerticalLine(VentLine line) {
        int minY = Integer.min(line.y1, line.y2);
        int maxY = Integer.max(line.y1, line.y2);
        for (int y = minY; y <= maxY; y++) {
            grid[y][line.x1]++;
        }
    }

    public long getNumberOfPointsWithAtLeastTwoOverlappingLines() {
        long result = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] >= 2) {
                    result++;
                }
            }
        }
        return result;
    }

    static class VentLine {
        private static Pattern inputPattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
        final int x1, y1, x2, y2;

        public VentLine(String input) {
            Matcher matcher = inputPattern.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Input is garbage");
            }
            x1 = Integer.parseInt(matcher.group(1));
            y1 = Integer.parseInt(matcher.group(2));
            x2 = Integer.parseInt(matcher.group(3));
            y2 = Integer.parseInt(matcher.group(4));
        }
    }
}
