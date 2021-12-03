package bn73.adventofcode.day3;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            Report report = new Report(Utilities.linesFromResource("day3_example.csv"));

            assertThat(report.calculateGamma()).isEqualTo(22);
            assertThat(report.calculateEpsilon()).isEqualTo(9);
            assertThat(report.powerConsumption()).isEqualTo(198);
        }

        @Test
        void can_calculate_assignment() {
            Report report = new Report(Utilities.linesFromResource("day3_assignment.csv"));

            assertThat(report.powerConsumption()).isEqualTo(2583164);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            Report report = new Report(Utilities.linesFromResource("day3_example.csv"));

            assertThat(report.calculateOxygenGeneratorRating()).isEqualTo(23);
            assertThat(report.calculateCO2ScrubberRating()).isEqualTo(10);
            assertThat(report.lifeSupportRating()).isEqualTo(230);
        }

        @Test
        void can_calculate_assignment() {
            Report report = new Report(Utilities.linesFromResource("day3_assignment.csv"));

            assertThat(report.lifeSupportRating()).isEqualTo(2784375);
        }
    }
}