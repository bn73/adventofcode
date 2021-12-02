package bn73.adventofcode.day2;

import bn73.adventofcode.Utilities;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Nested
    class Assignment1 {
        @Test
        void can_calculate_the_example() {
            Navigator1 navigator = new Navigator1();
            String[] commands = {
                    "forward 5",
                    "down 5",
                    "forward 8",
                    "up 3",
                    "down 8",
                    "forward 2"
            };

            try (Stream<String> commandStream = Arrays.stream(commands)) {
                navigator.applyCommandStream(commandStream);
            }
            assertThat(navigator.getHorizontalPosition() * navigator.getDepth()).isEqualTo(150);
        }

        @Test
        void can_calculate_the_assignment() {
            Navigator1 navigator = new Navigator1();

            try (Stream<String> commandStream = Utilities.linesFromResource("day2_assignment.csv")) {
                navigator.applyCommandStream(commandStream);
            }
            assertThat(navigator.getHorizontalPosition() * navigator.getDepth()).isEqualTo(1938402);
        }
    }

    @Nested
    class Assignment2 {
        @Test
        void can_calculate_the_example() {
            Navigator2 navigator = new Navigator2();
            String[] commands = {
                    "forward 5",
                    "down 5",
                    "forward 8",
                    "up 3",
                    "down 8",
                    "forward 2"
            };

            try (Stream<String> commandStream = Arrays.stream(commands)) {
                navigator.applyCommandStream(commandStream);
            }
            assertThat(navigator.getHorizontalPosition() * navigator.getDepth()).isEqualTo(900);
        }

        @Test
        void can_calculate_the_assignment() {
            Navigator2 navigator = new Navigator2();

            try (Stream<String> commandStream = Utilities.linesFromResource("day2_assignment.csv")) {
                navigator.applyCommandStream(commandStream);
            }
            assertThat(navigator.getHorizontalPosition() * navigator.getDepth()).isEqualTo(1947878632);
        }
    }

}