package bn73.adventofcode.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {
    private List<String> lines;

    public Day10(Stream<String> input) {
        this.lines = input.collect(Collectors.toList());
    }


    private long validateLine(String line) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if ("([{<".indexOf(c) != -1) {
                stack.push(c);
            } else {
                char sc = stack.peek();
                switch (c) {
                    case '>':
                        if (sc == '<') {
                            stack.pop();
                            break;
                        } else {
                            return 25137;
                        }
                    case ')':
                        if (sc == '(') {
                            stack.pop();
                            break;
                        } else {
                            return 3;

                        }
                    case '}':
                        if (sc == '{') {
                            stack.pop();
                            break;
                        } else {
                            return 1197;

                        }
                    case ']':
                        if (sc == '[') {
                            stack.pop();
                            break;
                        } else {
                            return 57;
                        }
                    default:
                        break;
                }
            }
        }
        return 0;
    }

    public long syntaxCheckerScore() {
        long score = 0;
        List<String> linesToDiscard = new ArrayList<>();
        for (String line : lines) {
            long lineScore = validateLine(line);
            if (lineScore != 0) {
                score += lineScore;
                linesToDiscard.add(line);
            }
        }
        lines.removeAll(linesToDiscard);
        return score;
    }

    public long autoCompletionScore() {
        syntaxCheckerScore(); // discards all invalid lines;
        List<Long> scores = lines.stream()
                .map(this::charactersToComplete)
                .map(this::score)
                .sorted()
                .collect(Collectors.toList());

        return scores.get(scores.size() / 2);
    }

    private long score(String s) {
        long totalScore = 0;
        for (int i = 0; i < s.length(); i++) {
            totalScore = totalScore * 5 + pointsFor(s.charAt(i));
        }
        return totalScore;
    }

    private long pointsFor(char c) {
        switch (c) {
            case ')':
                return 1;
            case ']':
                return 2;
            case '}':
                return 3;
            case '>':
                return 4;
            default:
                return 0;
        }
    }

    private String charactersToComplete(String line) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if ("([{<".indexOf(c) != -1) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        // what remains on the stack needs to be completed
        StringBuilder completionCharacters = new StringBuilder();

        while (!stack.isEmpty()) {
            Character c = stack.pop();
            switch (c) {
                case '(':
                    completionCharacters.append(')');
                    break;
                case '<':
                    completionCharacters.append('>');
                    break;
                case '[':
                    completionCharacters.append(']');
                    break;
                case '{':
                    completionCharacters.append('}');
                    break;
                default:
                    break;
            }
        }
        return completionCharacters.toString();
    }
}
