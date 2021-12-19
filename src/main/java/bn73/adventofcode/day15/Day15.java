package bn73.adventofcode.day15;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day15 {
    private Node[][] grid;
    private int gridWidth, gridHeight;

    public Day15(Stream<String> input, int copies) {
        List<String> lines = input.collect(Collectors.toList());

        int tileHeight = lines.size();
        int tileWidth = lines.get(0).length();

        gridHeight = tileHeight * copies;
        gridWidth = tileWidth * copies;
        grid = new Node[gridHeight][gridWidth];
        // fill out first row
        for (int copy = 0; copy < copies; copy++) {
            for (int y = 0; y < tileHeight; y++) {
                for (int x = 0; x < tileWidth; x++) {
                    grid[y][(copy * tileWidth) + x] = new Node(y, (copy * tileWidth) + x, copy + Integer.parseInt("" + lines.get(y).charAt(x)));
                }
            }
        }
        // fill out remaining rows
        for (int copy = 1; copy < copies; copy++) {
            for (int y = 0; y < tileHeight; y++) {
                for (int x = 0; x < gridWidth; x++) {
                    grid[(copy * tileHeight) + y][x] = new Node((copy * tileHeight) + y, x, copy + grid[y][x].getRiskLevel());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                sb.append(grid[y][x].toString());
            }
            sb.append('\n');
        }

        System.out.println(sb);
        // register neighbours
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                grid[y][x].registerNeighbours();
            }
        }
    }

    public long lowestRisk() {
        grid[0][0].leastKnownTotalRisk = 0;
        SortedSet<Node> unvisitedNodes = getUnvisitedNodes();

        while (!unvisitedNodes.isEmpty()) {
            Node currentNode = unvisitedNodes.first();
            unvisitedNodes.remove(currentNode);
            currentNode.getNeighbours()
                    .stream()
                    .filter(unvisitedNodes::contains)
                    .forEach(neighbour -> {
                        long alt = currentNode.getLeastKnownTotalRisk() + neighbour.getRiskLevel();
                        if (alt < (neighbour).getLeastKnownTotalRisk()) {
                            // in order to keep unvisitedNodes sorted, remove it, and reinsert it with updated totalRisk
                            unvisitedNodes.remove(neighbour);
                            neighbour.setLeastKnownTotalRisk(alt);
                            unvisitedNodes.add(neighbour);
                        }
                    });
        }
        return grid[gridHeight - 1][gridWidth - 1].getLeastKnownTotalRisk();
    }

    private SortedSet<Node> getUnvisitedNodes() {
        SortedSet<Node> unvisitedNodes = new TreeSet<>();
        for (int y = 0; y < gridHeight; y++) {
            for (int x = 0; x < gridWidth; x++) {
                unvisitedNodes.add(grid[y][x]);
            }
        }
        return unvisitedNodes;
    }

    class Node implements Comparable<Node> {
        final int x, y;
        final long riskLevel;
        long leastKnownTotalRisk = Long.MAX_VALUE;
        Set<Node> neighbours = new HashSet<>();

        public Node(int y, int x, long riskLevel) {
            this.x = x;
            this.y = y;
            this.riskLevel = riskLevel > 9 ? riskLevel - 9 : riskLevel;
        }

        public long getRiskLevel() {
            return riskLevel;
        }

        public long getLeastKnownTotalRisk() {
            return leastKnownTotalRisk;
        }

        public void setLeastKnownTotalRisk(long leastKnownTotalRisk) {
            this.leastKnownTotalRisk = leastKnownTotalRisk;
        }

        boolean validCoordinate(int y, int x) {
            if (x < 0 || x >= gridWidth) {
                return false;
            }
            if (y < 0 || y >= gridHeight) {
                return false;
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node that = (Node) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return Long.toString(riskLevel);
        }

        public Set<Node> getNeighbours() {
            return Collections.unmodifiableSet(neighbours);
        }

        public void registerNeighbours() {
            if (validCoordinate(y + 1, x)) {
                neighbours.add(grid[y + 1][x]);
            }
            if (validCoordinate(y - 1, x)) {
                neighbours.add(grid[y - 1][x]);
            }
            if (validCoordinate(y, x + 1)) {
                neighbours.add(grid[y][x + 1]);
            }
            if (validCoordinate(y, x - 1)) {
                neighbours.add(grid[y][x - 1]);
            }
        }

        @Override
        public int compareTo(Node that) {
            if (this.leastKnownTotalRisk == that.leastKnownTotalRisk) {
                if (this.x == that.x) {
                    return Integer.compare(this.y, that.y);
                } else {
                    return Integer.compare(this.x, that.x);
                }
            } else {
                return Long.compare(this.leastKnownTotalRisk, that.leastKnownTotalRisk);
            }
        }
    }
}
