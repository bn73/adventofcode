package bn73.adventofcode.day11;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day11 day11 = new Day11(Utilities.linesFromResource("day11_example"));

            assertThat(day11.totalNumberOfFlashesAfterSteps(100)).isEqualTo(1656);
        }

        @Test
        void can_calculate_assignment() {
            Day11 day11 = new Day11(Utilities.linesFromResource("day11_assignment"));

            assertThat(day11.totalNumberOfFlashesAfterSteps(100)).isEqualTo(1743);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day11 day11 = new Day11(Utilities.linesFromResource("day11_example"));

            assertThat(day11.firstStepOfAllFlashing()).isEqualTo(195);
        }

        @Test
        void can_calculate_assignment() {
            Day11 day11 = new Day11(Utilities.linesFromResource("day11_assignment"));

            assertThat(day11.firstStepOfAllFlashing()).isEqualTo(364);
        }
    }
}