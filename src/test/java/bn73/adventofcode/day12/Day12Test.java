package bn73.adventofcode.day12;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {
    @Nested
    class Assignment1 {
        @ParameterizedTest
        @CsvSource(
                delimiter = '|',
                value = {
                        "day12_example1|10",
                        "day12_example2|19",
                        "day12_example3|226"
                })
        void can_calculate_example(String resourceName, String expectedResult) {
            Day12 day12 = new Day12(Utilities.linesFromResource(resourceName));

            assertThat(day12.numberOfPathsVisitingSmallCavesAtMostOnce()).isEqualTo(Long.parseLong(expectedResult));
        }

        @Test
        void can_calculate_assignment() {
            Day12 day12 = new Day12(Utilities.linesFromResource("day12_assignment"));

            assertThat(day12.numberOfPathsVisitingSmallCavesAtMostOnce()).isEqualTo(5576);
        }
    }

    @Nested
    class Assignment2 {
        @ParameterizedTest
        @CsvSource(
                delimiter = '|',
                value = {
                        "day12_example1|36",
                        "day12_example2|103",
                        "day12_example3|3509"
                })
        void can_calculate_example(String resourceName, String expectedResult) {
            Day12 day12 = new Day12(Utilities.linesFromResource(resourceName));

            assertThat(day12.numberOfPathsVisitingAtMostOneSmallCaveTwice()).isEqualTo(Long.parseLong(expectedResult));
        }

        @Test
        void can_calculate_assignment() {
            Day12 day12 = new Day12(Utilities.linesFromResource("day12_assignment"));

            assertThat(day12.numberOfPathsVisitingAtMostOneSmallCaveTwice()).isEqualTo(152837);
        }
    }
}