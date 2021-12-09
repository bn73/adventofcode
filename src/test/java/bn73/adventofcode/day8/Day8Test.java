package bn73.adventofcode.day8;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day8 day8 = new Day8(Utilities.linesFromResource("day8_example"));

            assertThat(day8.countOfDigitsWithUniqueSegments()).isEqualTo(26);
        }

        @Test
        void can_calculate_assignment() {
            Day8 day8 = new Day8(Utilities.linesFromResource("day8_assignment"));

            assertThat(day8.countOfDigitsWithUniqueSegments()).isEqualTo(303);
        }
    }


    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day8 day8 = new Day8(Utilities.linesFromResource("day8_example"));

            assertThat(day8.computeSumOfOutputs()).isEqualTo(61229);
        }

        @Test
        void can_calculate_assignment() {
            Day8 day8 = new Day8(Utilities.linesFromResource("day8_assignment"));

            assertThat(day8.computeSumOfOutputs()).isEqualTo(961734);
        }
    }
}