package bn73.adventofcode.day7;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            CrabAligner1 crabAligner = new CrabAligner1(Utilities.linesFromResource("day7_example"));

            assertThat(crabAligner.costOfCheapestHorizontalPosition()).isEqualTo(37);
        }

        @Test
        void can_calculate_assignment() {
            CrabAligner1 crabAligner = new CrabAligner1(Utilities.linesFromResource("day7_assignment"));

            assertThat(crabAligner.costOfCheapestHorizontalPosition()).isEqualTo(323647);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            CrabAligner2 crabAligner = new CrabAligner2(Utilities.linesFromResource("day7_example"));

            assertThat(crabAligner.costOfCheapestHorizontalPosition()).isEqualTo(168);
        }

        @Test
        void can_calculate_assignment() {
            CrabAligner2 crabAligner = new CrabAligner2(Utilities.linesFromResource("day7_assignment"));

            assertThat(crabAligner.costOfCheapestHorizontalPosition()).isEqualTo(87640209);
        }
    }
}