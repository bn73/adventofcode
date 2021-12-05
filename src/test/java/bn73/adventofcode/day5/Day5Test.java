package bn73.adventofcode.day5;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day5Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day5 day5 = new Day5(Utilities.linesFromResource("day5_example"), false);

            assertThat(day5.getNumberOfPointsWithAtLeastTwoOverlappingLines()).isEqualTo(5);
        }

        @Test
        void can_calculate_assignment() {
            Day5 day5 = new Day5(Utilities.linesFromResource("day5_assignment"), false);

            assertThat(day5.getNumberOfPointsWithAtLeastTwoOverlappingLines()).isEqualTo(5698);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day5 day5 = new Day5(Utilities.linesFromResource("day5_example"), true);

            assertThat(day5.getNumberOfPointsWithAtLeastTwoOverlappingLines()).isEqualTo(12);
        }

        @Test
        void can_calculate_assignment() {
            Day5 day5 = new Day5(Utilities.linesFromResource("day5_assignment"), true);

            assertThat(day5.getNumberOfPointsWithAtLeastTwoOverlappingLines()).isEqualTo(15463);
        }
    }
}