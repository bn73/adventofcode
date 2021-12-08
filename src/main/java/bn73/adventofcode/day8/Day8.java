package bn73.adventofcode.day8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Day8 {

    private final List<List<String>> signals;
    private final List<List<String>> output;

    public Day8(Stream<String> input) {
        List<String[]> arrays = input.map(line -> line.split(" ")).collect(toList());
        signals = arrays
                .stream()
                .map(a -> Arrays.copyOfRange(a, 0, 10))
                .map(a -> asList(a))
                .collect(toList());
        output = arrays
                .stream()
                .map(a -> Arrays.copyOfRange(a, 11, 15))
                .map(a -> asList(a))
                .collect(toList());
    }

    public long computeSumOfOutputs() {
        long sum = 0;
        for (int i = 0; i < signals.size(); i++) {
            sum += computeOutput(signals.get(i), output.get(i));
        }
        return sum;
    }

    private long computeOutput(List<String> currentSignals, List<String> currentOutput) {
        List<String> normalizedSignals = currentSignals
                .stream()
                .map(String::toCharArray)
                .map(a -> {
                    Arrays.sort(a);
                    return new String(a);
                })
                .collect(toList());

        List<String> normalizedOutput = currentOutput
                .stream()
                .map(String::toCharArray)
                .map(a -> {
                    Arrays.sort(a);
                    return new String(a);
                })
                .collect(toList());

        StringBuilder builder = new StringBuilder();
        String segments1 = normalizedSignals.stream().filter(o -> o.length() == 2).findFirst().get();
        String segments7 = normalizedSignals.stream().filter(o -> o.length() == 3).findFirst().get();
        String segments4 = normalizedSignals.stream().filter(o -> o.length() == 4).findFirst().get();
        String segments8 = normalizedSignals.stream().filter(o -> o.length() == 7).findFirst().get();
        String segments3 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 5)
                .filter(o -> containsSegments(o, segments1))
                .findFirst().get();
        String segments6 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 6)
                .filter(o -> !containsSegments(o, segments1))
                .findFirst().get();
        String segments0 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 6)
                .filter(o -> containsSegments(o, segments1) && !containsSegments(o, segments4))
                .findFirst().get();
        String segments9 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 6)
                .filter(o -> containsSegments(o, segments1) && containsSegments(o, segments4))
                .findFirst().get();
        String segments5 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 5)
                .filter(o -> !o.equals(segments3))
                .filter(o -> removeSegments(o, segments6).isEmpty())
                .findFirst().get();
        String segments2 = normalizedSignals
                .stream()
                .filter(o -> o.length() == 5)
                .filter(o -> !o.equals(segments3))
                .filter(o -> !removeSegments(o, segments6).isEmpty())
                .findFirst().get();

        normalizedOutput.forEach(s -> {
            if (s.equals(segments1)) {
                builder.append("1");
            } else if (s.equals(segments7)) {
                builder.append("7");
            } else if (s.equals(segments8)) {
                builder.append("8");
            } else if (s.equals(segments4)) {
                builder.append("4");
            } else if (s.equals(segments6)) {
                builder.append("6");
            } else if (s.equals(segments0)) {
                builder.append("0");
            } else if (s.equals(segments9)) {
                builder.append("9");
            } else if (s.equals(segments3)) {
                builder.append("3");
            } else if (s.equals(segments2)) {
                builder.append("2");
            } else if (s.equals(segments5)) {
                builder.append("5");
            } else {
                throw new IllegalArgumentException("Cannot compute " + s);
            }
        });
        System.out.println(builder);
        return Long.parseLong(builder.toString());
    }

    private String removeSegments(String o, String segments) {
        String result = o;
        for (int i = 0; i < segments.length(); i++) {
            result = result.replaceAll("" + segments.charAt(i), "");
        }
        return result;
    }

    private boolean containsSegments(String o, String segments) {
        for (int i = 0; i < segments.length(); i++) {
            if (o.indexOf(segments.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    public long countOutputDigit(int digit) {
        switch (digit) {
            case 1:
                return output.stream().flatMap(List::stream).filter(o -> o.length() == 2).count();
            case 4:
                return output.stream().flatMap(List::stream).filter(o -> o.length() == 4).count();
            case 7:
                return output.stream().flatMap(List::stream).filter(o -> o.length() == 3).count();
            case 8:
                return output.stream().flatMap(List::stream).filter(o -> o.length() == 7).count();
            default:
                throw new IllegalArgumentException("Does not recognise digit yet:" + digit);
        }
    }

    public long countOfDigitsWithUniqueSegments() {
        return countOutputDigit(1) + countOutputDigit(4) + countOutputDigit(7) + countOutputDigit(8);
    }
}
