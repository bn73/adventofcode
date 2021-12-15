package bn73.adventofcode.day14;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14 {
    Map<Character, Long> characterCounts = new HashMap<>();
    private List<Character> characters = new LinkedList<>();
    private Map<String, Character> replacements;
    private Map<String, Long> pairCounts;

    public Day14(Stream<String> input) {
        Pattern pattern = Pattern.compile("(..) -> (.)");
        List<String> lines = input.collect(Collectors.toList());
        String string = lines.get(0);
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            characters.add(c);
            characterCounts.put(c, characterCounts.getOrDefault(c, 0L) + 1);
        }
        replacements = lines
                .stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(Matcher::toMatchResult)
                .collect(Collectors.toMap(match -> match.group(1), match -> match.group(2).charAt(0)));

        countPairs();
    }

    private void countPairs() {
        pairCounts = new HashMap<>();
        for (int i = 0; i < characters.size(); i++) {
            String pair = (i + 1 < characters.size()) ? "" + characters.get(i) + characters.get(i + 1) : "" + characters.get(i);
            pairCounts.put(pair, pairCounts.getOrDefault(pair, 0L) + 1);
        }
    }

    public void runStep() {
        // naive implementation
//        for (int i = 0; i < characters.size(); i++) {
//            String pair = (i + 1  < characters.size()) ? "" + characters.get(i) + characters.get(i +1) : "" + characters.get(i);
//            Character replacement = replacements.get(pair);
//            if (replacement != null) {
//                characters.add(i + 1, replacement);
////                characterCounts.put(replacement, characterCounts.getOrDefault(replacement, 0L) + 1);
//                i++;
//            } else {
//                System.out.println("no mapping for pair " + pair);
//            }
//        }
        //memory saving implementation
        System.out.println("-------------");
        Map<String, Long> newPairCounts = new HashMap<>();
        pairCounts.entrySet().forEach(entry -> {
            String pair = entry.getKey();
            Character replacement = replacements.get(pair);
            Long countOfPair = entry.getValue();
            if (replacement == null) {
                newPairCounts.put(pair, newPairCounts.getOrDefault(pair, 0L) + countOfPair);
            } else {
                // count one added character
                characterCounts.put(replacement, characterCounts.getOrDefault(replacement, 0L) + countOfPair);
                // generate two new pairs
                String newPair1 = "" + pair.charAt(0) + replacement;
                String newPair2 = "" + replacement + pair.charAt(1);
                Long newPair1PreCount = newPairCounts.getOrDefault(newPair1, 0L);
                Long newPair2PreCount = newPairCounts.getOrDefault(newPair2, 0L);
                newPairCounts.put(newPair1, newPair1PreCount + countOfPair);
                System.out.println(String.format("Splitting %s(%d) into %s(%d -> %d) and %s(%d -> %d)",
                        pair, countOfPair,
                        newPair1, newPair1PreCount, newPair1PreCount + countOfPair,
                        newPair2, newPair2PreCount, newPair2PreCount + countOfPair));
                newPairCounts.put(newPair2, newPair2PreCount + countOfPair);
            }
        });
        pairCounts = newPairCounts;
    }

    public String getCharacters() {
        StringBuilder sb = new StringBuilder();
        characters.forEach(sb::append);
        return sb.toString();
    }

    public int getResult() {
        Map<Character, Integer> characterCounts = new HashMap<>();
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            characterCounts.put(c, characterCounts.getOrDefault(c, 0) + 1);
        }

        int min = characterCounts.values().stream().mapToInt(Integer::intValue).min().getAsInt();
        int max = characterCounts.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        return max - min;
    }

    public long getResult2() {
        long min = characterCounts.values().stream().mapToLong(Long::longValue).min().getAsLong();
        long max = characterCounts.values().stream().mapToLong(Long::longValue).max().getAsLong();
        return max - min;
    }
}
