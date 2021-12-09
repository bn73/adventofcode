package bn73.adventofcode.day9;

import bn73.adventofcode.Day9;
import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day9 day9 = new Day9(Utilities.linesFromResource("day9_example"));

            assertThat(day9.sumOfRiskLevelsOfLowPoints()).isEqualTo(15);
        }

        @Test
        void can_calculate_assignment() {
            Day9 day9 = new Day9(Utilities.linesFromResource("day9_assignment"));

            assertThat(day9.sumOfRiskLevelsOfLowPoints()).isEqualTo(591);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Day9 day9 = new Day9(Utilities.linesFromResource("day9_example"));

            assertThat(day9.productOfThreeLargestBasins()).isEqualTo(1134);
        }

        @Test
        void can_calculate_assignment() {
            Day9 day9 = new Day9(Utilities.linesFromResource("day9_assignment"));

            assertThat(day9.productOfThreeLargestBasins()).isEqualTo(1113424);
        }
    }

}