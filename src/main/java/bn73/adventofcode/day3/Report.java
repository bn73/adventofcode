package bn73.adventofcode.day3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Report {

    private final int numberOfStrings;
    private final int lengthOfStrings;
    private final List<String> strings;


    public Report(Stream<String> lines) {
        strings = lines.collect(Collectors.toList());
        numberOfStrings = strings.size();
        lengthOfStrings = strings.stream().mapToInt(String::length).max().orElse(0);
    }

    public long powerConsumption() {
        return calculateGamma() * calculateEpsilon();
    }

    public long lifeSupportRating() {
        return calculateOxygenGeneratorRating() * calculateCO2ScrubberRating();
    }

    long calculateGamma() {
        StringBuilder gamma = new StringBuilder();
        for (int i = 0; i < lengthOfStrings; i++) {
            long ones = countStringsWithValueAtIndex(strings, '1', i);
            long zeroes = countStringsWithValueAtIndex(strings, '0', i);
            gamma.append((ones >= zeroes) ? '1' : '0');
        }
        return Long.parseLong(gamma.toString(), 2);
    }

    long calculateEpsilon() {
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < lengthOfStrings; i++) {
            long ones = countStringsWithValueAtIndex(strings, '1', i);
            long zeroes = countStringsWithValueAtIndex(strings, '0', i);

            epsilon.append((zeroes >= ones) ? '1' : '0');
        }
        return Long.parseLong(epsilon.toString(), 2);
    }

    long calculateOxygenGeneratorRating() {
        List<String> remainingStrings = strings;
        for (int i = 0; i < lengthOfStrings && (remainingStrings.size() > 1); i++) {
            long ones = countStringsWithValueAtIndex(remainingStrings, '1', i);
            long zeroes = countStringsWithValueAtIndex(remainingStrings, '0', i);

            remainingStrings = stringsToKeep(remainingStrings, (ones >= zeroes) ? '1' : '0', i);
        }
        return Long.parseLong(remainingStrings.get(0), 2);
    }

    long calculateCO2ScrubberRating() {
        List<String> remainingStrings = strings;
        for (int i = 0; i < lengthOfStrings && (remainingStrings.size() > 1); i++) {
            long ones = countStringsWithValueAtIndex(remainingStrings, '1', i);
            long zeroes = countStringsWithValueAtIndex(remainingStrings, '0', i);

            remainingStrings = stringsToKeep(remainingStrings, (ones < zeroes) ? '1' : '0', i);
        }
        return Long.parseLong(remainingStrings.get(0), 2);
    }

    private long countStringsWithValueAtIndex(List<String> stringsToCount, char value, int index) {
        return stringsToCount.stream().filter(s -> s.charAt(index) == value).count();
    }

    private List<String> stringsToKeep(List<String> stringsToConsider, char value, int index) {
        return stringsToConsider.stream().filter(s -> s.charAt(index) == value).collect(Collectors.toList());
    }
}
