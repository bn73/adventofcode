package bn73.adventofcode.day15;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day15Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day15 day15 = new Day15(Utilities.linesFromResource("day15_example"), 1);

            assertThat(day15.lowestRisk()).isEqualTo(40);
        }

        @Test
        void can_calculate_assignment() {
            Day15 day15 = new Day15(Utilities.linesFromResource("day15_assignment"), 1);

            assertThat(day15.lowestRisk()).isEqualTo(540);
        }

    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day15 day15 = new Day15(Utilities.linesFromResource("day15_example"), 5);

            assertThat(day15.lowestRisk()).isEqualTo(315);
        }

        @Test
        void can_calculate_assignment() {
            Day15 day15 = new Day15(Utilities.linesFromResource("day15_assignment"), 5);

            assertThat(day15.lowestRisk()).isEqualTo(2879);
        }

    }
}