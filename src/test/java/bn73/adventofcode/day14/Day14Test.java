package bn73.adventofcode.day14;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {
    @Nested
    class Assignment1 {
//        @Test
//        void can_transform_example() {
//            Day14 day14 = new Day14(Utilities.linesFromResource("day14_example"));
//            assertThat(day14.getCharacters()).isEqualTo("NNCB");
//            day14.runStep();
//            assertThat(day14.getCharacters()).isEqualTo("NCNBCHB");
//
//            day14.runStep();
//            assertThat(day14.getCharacters()).isEqualTo("NBCCNBBBCBHCB");
//
//            day14.runStep();
//            assertThat(day14.getCharacters()).isEqualTo("NBBBCNCCNBBNBNBBCHBHHBCHB");
//
//            day14.runStep();
//            assertThat(day14.getCharacters()).isEqualTo("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB");
//        }

        @Test
        void can_calculate_example() {
            Day14 day14 = new Day14(Utilities.linesFromResource("day14_example"));

            for (int i = 0; i < 10; i++) {
                day14.runStep();
            }
            assertThat(day14.getResult2()).isEqualTo(1588);
        }

        @Test
        void can_calculate_assignment() {
            Day14 day14 = new Day14(Utilities.linesFromResource("day14_assignment"));

            for (int i = 0; i < 10; i++) {
                day14.runStep();
            }
            assertThat(day14.getResult2()).isEqualTo(2068);
        }
    }

    @Nested
    class Assignment2 {

        @Test
        void can_calculate_example() {
            Day14 day14 = new Day14(Utilities.linesFromResource("day14_example"));

            for (int i = 0; i < 40; i++) {
                day14.runStep();
            }
            assertThat(day14.getResult2()).isEqualTo(2188189693529L);
        }

        @Test
        void can_calculate_assignment() {
            Day14 day14 = new Day14(Utilities.linesFromResource("day14_assignment"));

            for (int i = 0; i < 40; i++) {
                day14.runStep();
            }
            assertThat(day14.getResult2()).isEqualTo(2158894777814L);
        }
    }
}