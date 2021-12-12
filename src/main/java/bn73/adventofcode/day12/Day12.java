package bn73.adventofcode.day12;

import java.util.*;
import java.util.stream.Stream;

public class Day12 {
    private Map<String, List<String>> caveMap = new HashMap<>();
    private List<Path> knownPathsToExit = new ArrayList<>();

    public Day12(Stream<String> input) {
        input.forEach(line -> {
            String[] cave = line.split("-");
            caveMap.computeIfAbsent(cave[0], key -> new ArrayList<>()).add(cave[1]);
            caveMap.computeIfAbsent(cave[1], key -> new ArrayList<>()).add(cave[0]);
        });
    }

    public long numberOfPathsVisitingSmallCavesAtMostOnce() {
        new Path().travelPathVisitingSmallCavesOnlyOnce("start");
        return knownPathsToExit.size();
    }

    public long numberOfPathsVisitingAtMostOneSmallCaveTwice() {
        new Path().travelPathVisitingAtMostOneSmallCaveTwice("start");
        return knownPathsToExit.size();
    }

    private boolean caveIsSmall(String cave) {
        return cave.equals(cave.toLowerCase());
    }

    class Path {
        Set<String> smallCavesVisited = new HashSet<>();
        List<String> caves = new ArrayList<>();
        String smallCaveVisitedTwice = null;

        Path() {
        }

        Path(Path pathTravelledSoFar) {
            this.caves = new ArrayList<>(pathTravelledSoFar.caves);
            this.smallCavesVisited = new HashSet<>(pathTravelledSoFar.smallCavesVisited);
            this.smallCaveVisitedTwice = pathTravelledSoFar.smallCaveVisitedTwice;
        }

        void travelPathVisitingSmallCavesOnlyOnce(String cave) {
            caves.add(cave);
            if (cave.equals("end")) {
                knownPathsToExit.add(this);
                return;
            }
            if (caveIsSmall(cave) && !smallCavesVisited.add(cave)) {
                return;
            }
            caveMap.get(cave).forEach(nextCave -> new Path(this).travelPathVisitingSmallCavesOnlyOnce(nextCave));
        }

        void travelPathVisitingAtMostOneSmallCaveTwice(String cave) {
            caves.add(cave);
            if (cave.equals("end")) {
                knownPathsToExit.add(this);
                return;
            }
            boolean caveIsSmallAndAlreadyVisited = caveIsSmall(cave) && !smallCavesVisited.add(cave);
            if (caveIsSmallAndAlreadyVisited) {
                if (smallCaveVisitedTwice != null) {
                    return;
                } else {
                    smallCaveVisitedTwice = cave;
                }
            }

            caveMap.get(cave)
                    .stream()
                    .filter(nextCave -> !nextCave.equals("start"))
                    .forEach(nextCave -> new Path(this).travelPathVisitingAtMostOneSmallCaveTwice(nextCave));
        }
    }
}
