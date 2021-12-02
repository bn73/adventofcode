package bn73.adventofcode.day2;

import java.util.stream.Stream;

public class Navigator1 {
    private long horizontalPosition;
    private long depth;

    public long getHorizontalPosition() {
        return horizontalPosition;
    }

    public long getDepth() {
        return depth;
    }

    public void applyCommandStream(Stream<String> commandStream) {
        commandStream.forEach(this::applyCommand);
    }

    public void applyCommand(String command) {
        String[] args = command.split(" ");
        String movement = args[0];
        long units = Long.parseLong(args[1]);

        switch (movement) {
            case "forward":
                horizontalPosition += units;
                break;
            case "up":
                depth -= units;
                break;
            case "down":
                depth += units;
                break;
            default:
                // ignore
        }
    }
}
