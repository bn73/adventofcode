package bn73.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utilities {
    public static Stream<String> linesFromResource(String resourceName) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Utilities.class.getClassLoader().getResourceAsStream(resourceName)))) {
            return bufferedReader.lines().collect(Collectors.toList()).stream();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
