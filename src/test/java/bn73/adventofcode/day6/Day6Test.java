package bn73.adventofcode.day6;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day6Test {
    @Nested
    class Assignment1 {
        @Test
        void can_calculate_example() {
            FishSimulator fishSimulator = new FishSimulator(Utilities.linesFromResource("day6_example"));

            for (int day = 0; day < 80; day++) {
                fishSimulator.nextDay();
            }
            assertThat(fishSimulator.currentNumberOfFish()).isEqualTo(5934);
        }

        @Test
        void can_calculate_assignment() {
            FishSimulator fishSimulator = new FishSimulator(Utilities.linesFromResource("day6_assignment"));

            for (int day = 0; day < 80; day++) {
                fishSimulator.nextDay();
            }
            assertThat(fishSimulator.currentNumberOfFish()).isEqualTo(390011);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_example() {
            FishSimulator fishSimulator = new FishSimulator(Utilities.linesFromResource("day6_example"));

            for (int day = 0; day < 256; day++) {
                fishSimulator.nextDay();
            }
            assertThat(fishSimulator.currentNumberOfFish()).isEqualTo(26984457539L);
        }

        @Test
        void can_calculate_assignment() {
            FishSimulator fishSimulator = new FishSimulator(Utilities.linesFromResource("day6_assignment"));

            for (int day = 0; day < 256; day++) {
                fishSimulator.nextDay();
            }
            assertThat(fishSimulator.currentNumberOfFish()).isEqualTo(1746710169834L);
        }
    }
}