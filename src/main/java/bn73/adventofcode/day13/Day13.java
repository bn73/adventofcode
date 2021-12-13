package bn73.adventofcode.day13;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 {
    private boolean[][] grid;
    private int gridWidth, gridHeight;
    private Iterator<FoldInstruction> foldInstructionIterator;

    public Day13(Stream<String> input) {
        Pattern dotPattern = Pattern.compile("(\\d+),(\\d+)");
        Pattern foldPattern = Pattern.compile("fold along (x|y)=(\\d+)");
        List<String> lines = input.collect(Collectors.toList());
        List<Coordinate> dots = lines
                .stream()
                .map(dotPattern::matcher)
                .filter(Matcher::matches)
                .map(Matcher::toMatchResult)
                .map(match -> new Coordinate(Integer.parseInt(match.group(2)), Integer.parseInt(match.group(1))))
                .collect(Collectors.toList());

        gridWidth = dots.stream().mapToInt(dot -> dot.x).max().getAsInt() + 1;
        gridHeight = dots.stream().mapToInt(dot -> dot.y).max().getAsInt() + 1;
        grid = new boolean[gridHeight][gridWidth];

        dots.forEach(dot -> grid[dot.y][dot.x] = true);

        foldInstructionIterator = lines
                .stream()
                .map(foldPattern::matcher)
                .filter(Matcher::matches)
                .map(Matcher::toMatchResult)
                .map(match -> new FoldInstruction(match.group(1).charAt(0), Integer.parseInt(match.group(2))))
                .collect(Collectors.toList())
                .listIterator();
    }

    public void renderDots(String header) {
        StringBuilder sb = new StringBuilder(header).append('\n');
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                sb.append(grid[y][x] ? '#' : ' ');
            }
            sb.append('\n');
        }
        sb.append('\n');
        System.out.println(sb);
    }

    public boolean doNextFold() {
        if (foldInstructionIterator.hasNext()) {
            FoldInstruction foldInstruction = foldInstructionIterator.next();
            if (foldInstruction.axis == 'y') {
                foldOnY(foldInstruction.line);
            } else {
                foldOnX(foldInstruction.line);
            }
            return true;
        } else {
            return false;
        }
    }

    private void foldOnX(int line) {
        for (int dx = 1; dx <= line; dx++) {
            for (int y = 0; y < gridHeight; y++) {
                grid[y][line - dx] |= grid[y][line + dx];
            }
        }

        gridWidth = line;
    }

    private void foldOnY(int line) {
        for (int dy = 1; dy <= line; dy++) {
            for (int x = 0; x < gridWidth; x++) {
                grid[line - dy][x] |= grid[line + dy][x];
            }
        }

        gridHeight = line;
    }

    public long numberOfDots() {
        long result = 0;
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                result += grid[y][x] ? 1 : 0;
            }
        }
        return result;
    }

    static class FoldInstruction {
        final char axis;
        final int line;

        public FoldInstruction(char axis, int line) {
            this.axis = axis;
            this.line = line;
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
            Day13.Coordinate that = (Day13.Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
