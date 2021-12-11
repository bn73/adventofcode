package bn73.adventofcode.day10;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day10 day10 = new Day10(Utilities.linesFromResource("day10_example"));

            assertThat(day10.syntaxCheckerScore()).isEqualTo(26397);
        }

        @Test
        void can_calculate_assignment() {
            Day10 day10 = new Day10(Utilities.linesFromResource("day10_assignment"));

            assertThat(day10.syntaxCheckerScore()).isEqualTo(315693);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day10 day10 = new Day10(Utilities.linesFromResource("day10_example"));

            assertThat(day10.autoCompletionScore()).isEqualTo(288957);
        }

        @Test
        void can_calculate_assignment() {
            Day10 day10 = new Day10(Utilities.linesFromResource("day10_assignment"));

            assertThat(day10.autoCompletionScore()).isEqualTo(1870887234);
        }
    }
}