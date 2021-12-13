package bn73.adventofcode.day13;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Day13 day13 = new Day13(Utilities.linesFromResource("day13_example"));

            day13.doNextFold();
            assertThat(day13.numberOfDots()).isEqualTo(17);

            day13.doNextFold();
            assertThat(day13.numberOfDots()).isEqualTo(16);
        }

        @Test
        void can_calculate_assignment() {
            Day13 day13 = new Day13(Utilities.linesFromResource("day13_assignment"));

            day13.doNextFold();
            assertThat(day13.numberOfDots()).isEqualTo(814);
            // just finish the remaining folding instructions, and print it out to get the answer of part 2
            while (day13.doNextFold()) ;
            day13.renderDots("After the final fold, these are the eight capital letters for the answer:");
        }
    }

}