package bn73.adventofcode.day4;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Bingo {
    private Queue<Long> numbers;
    private List<Board> boards = new ArrayList<>();

    public Bingo(Stream<String> lines) {
        List<String> allLines = lines.filter(s -> !s.isEmpty()).collect(toList());
        numbers = Arrays.stream(allLines.get(0).split(",")).map(Long::valueOf).collect(toCollection(LinkedList::new));
        for (int i = 1; i < allLines.size(); i += 5) {
            List<String> boardLines = allLines.subList(i, i + 5);
            boards.add(new Board(boardLines));
        }
    }

    /**
     * Draw the next number.
     *
     * @return if number caused boards to win, the winning boards
     * @throws NoSuchElementException if out of numbers
     */
    public List<Board> drawNextNumber() {
        long number = numbers.remove();
        boards.forEach(board -> {
            board.playNumber(number);
        });
        return boards.stream().filter(board -> board.hasWinningColumn() || board.hasWinningRow()).collect(toList());
    }

    public List<Board> playGameUntilWinningBoards() {
        List<Board> winningBoards;
        do {
            winningBoards = drawNextNumber();
        } while (winningBoards.isEmpty());
        return winningBoards;
    }

    public List<Board> playGameUntilLastWinningBoards() {
        List<Board> lastWinningBoards = emptyList();
        try {
            while (true) {
                lastWinningBoards = playGameUntilWinningBoards();
                boards.removeAll(lastWinningBoards);
            }
        } catch (NoSuchElementException e) {
            // out of numbers, we are done
        }
        return lastWinningBoards;
    }
}
;