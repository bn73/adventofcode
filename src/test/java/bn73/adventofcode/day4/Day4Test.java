package bn73.adventofcode.day4;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Bingo bingo = new Bingo(Utilities.linesFromResource("day4_example"));

            List<Board> winningBoards = bingo.playGameUntilWinningBoards();
            assertThat(winningBoards).isNotEmpty();
            Board winningBoard = winningBoards.get(0);
            assertThat(winningBoard.unmarkedSum()).isEqualTo(188);
            assertThat(winningBoard.getLastPlayedNumber()).isEqualTo(24);
            assertThat(winningBoard.getResult()).isEqualTo(4512);
        }

        @Test
        void can_calculate_assignment() {
            Bingo bingo = new Bingo(Utilities.linesFromResource("day4_assignment"));

            List<Board> winningBoards = bingo.playGameUntilWinningBoards();
            assertThat(winningBoards).isNotEmpty();
            assertThat(winningBoards.get(0).getResult()).isEqualTo(32844);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Bingo bingo = new Bingo(Utilities.linesFromResource("day4_example"));

            List<Board> winningBoards = bingo.playGameUntilLastWinningBoards();
            assertThat(winningBoards).isNotEmpty();
            Board winningBoard = winningBoards.get(winningBoards.size() - 1);
            assertThat(winningBoard.unmarkedSum()).isEqualTo(148);
            assertThat(winningBoard.getLastPlayedNumber()).isEqualTo(13);
            assertThat(winningBoard.getResult()).isEqualTo(1924);
        }

        @Test
        void can_calculate_assignment() {
            Bingo bingo = new Bingo(Utilities.linesFromResource("day4_assignment"));

            List<Board> winningBoards = bingo.playGameUntilLastWinningBoards();
            assertThat(winningBoards.get(winningBoards.size() - 1).getResult()).isEqualTo(4920);
        }
    }

}