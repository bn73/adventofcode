package bn73.adventofcode.day6;

import java.util.Arrays;
import java.util.stream.Stream;

public class FishSimulator {
    private long[] numberOfFishInPhase = new long[9];
    private long currentNumberOfFish;

    public FishSimulator(Stream<String> input) {
        input.flatMap(line ->
                        Arrays.stream(line.split(",")))
                .map(Integer::parseInt)
                .forEach(phase -> {
                    numberOfFishInPhase[phase]++;
                    currentNumberOfFish++;
                });
    }

    public void nextDay() {
        long numberOfFishThatSpawnsNewFish = numberOfFishInPhase[0];
        for (int phase = 0; phase < 8; phase++) {
            numberOfFishInPhase[phase] = numberOfFishInPhase[phase + 1];
        }
        numberOfFishInPhase[6] += numberOfFishThatSpawnsNewFish; // those that were moved from phase 7 + those that wrapped around
        numberOfFishInPhase[8] = numberOfFishThatSpawnsNewFish; // new fish
        currentNumberOfFish += numberOfFishThatSpawnsNewFish; // new fish
    }

    public long currentNumberOfFish() {
        return currentNumberOfFish;
    }
}
