package bn73.adventofcode.day2;

import java.util.stream.Stream;

public class Navigator2 {
    private long horizontalPosition;
    private long depth;
    private long aim;

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
                depth += aim * units;
                break;
            case "up":
                aim -= units;
                break;
            case "down":
                aim += units;
                break;
            default:
                // ignore
        }
    }
}
