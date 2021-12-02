package bn73.adventofcode.day1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Nested
    class CountIncreasingDepths1 {

        @Test
        void zero_or_just_one_sweep_has_zero_increases() {
            assertThat(new Sweeps(new long[]{}).countIncreasingDepths1()).isZero();
            assertThat(new Sweeps(new long[]{42}).countIncreasingDepths1()).isZero();
        }

        @Test
        void can_calculate_the_example_result() {

            Sweeps sweeps = new Sweeps(new long[]{
                    199,
                    200,
                    208,
                    210,
                    200,
                    207,
                    240,
                    269,
                    260,
                    263
            });

            assertThat(sweeps.countIncreasingDepths1()).isEqualTo(7);
        }

        @Test
        void calculate_the_given_assignment() throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("day1_assignment.csv")))) {
                //System.out.println(new Sweeps(reader).countIncreasingDepths());
                assertThat(new Sweeps(reader).countIncreasingDepths1()).isEqualTo(1715);
            }
        }
    }

    @Nested
    class CountIncreasingDepths2 {
        @Test
        void anything_less_than_two_full_windows_has_zero_increases() {
            final long windowSize = 3;
            assertThat(new Sweeps(new long[]{}).countIncreasingDepths2(windowSize)).isZero();
            assertThat(new Sweeps(new long[]{1}).countIncreasingDepths2(windowSize)).isZero();
            assertThat(new Sweeps(new long[]{1, 2}).countIncreasingDepths2(windowSize)).isZero();
            assertThat(new Sweeps(new long[]{1, 2, 3}).countIncreasingDepths2(windowSize)).isZero();

            assertThat(new Sweeps(new long[]{1, 2, 3, 4}).countIncreasingDepths2(windowSize)).isEqualTo(1);
            assertThat(new Sweeps(new long[]{1, 2, 3, 4, 5}).countIncreasingDepths2(windowSize)).isEqualTo(2);
            assertThat(new Sweeps(new long[]{1, 2, 3, 4, 5, 6}).countIncreasingDepths2(windowSize)).isEqualTo(3);
        }

        @Test
        void can_calculate_the_example_result() {

            Sweeps sweeps = new Sweeps(new long[]{
                    199,
                    200,
                    208,
                    210,
                    200,
                    207,
                    240,
                    269,
                    260,
                    263
            });

            assertThat(sweeps.countIncreasingDepths2(3)).isEqualTo(5);
        }

        @Test
        void calculate_the_given_assignment() throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("day1_assignment.csv")))) {
                //System.out.println(new Sweeps(reader).countIncreasingDepths2(3));
                assertThat(new Sweeps(reader).countIncreasingDepths2(3)).isEqualTo(1739);
            }
        }
    }
}