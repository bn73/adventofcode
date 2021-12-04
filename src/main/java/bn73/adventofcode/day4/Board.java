package bn73.adventofcode.day4;

import java.util.List;

public class Board {
    private NumberOnBoard[][] numbersOnBoard = new NumberOnBoard[5][5];
    private Long lastPlayedNumber;

    public Board(List<String> boardLines) {
        for (int row = 0; row < 5; row++) {
            String[] columnsInRow = boardLines.get(row).trim().split("\\s+");
            for (int column = 0; column < 5; column++) {
                numbersOnBoard[row][column] = new NumberOnBoard(Long.parseLong(columnsInRow[column]));
            }
        }
    }

    public long getResult() {
        return unmarkedSum() * getLastPlayedNumber();
    }

    public void playNumber(long number) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (numbersOnBoard[row][column].playNumber(number)) {
                    lastPlayedNumber = number;
                }
            }
        }
    }

    public boolean hasWinningRow() {
        for (int row = 0; row < 5; row++) {
            boolean rowIsWinner = true;
            for (int column = 0; column < 5; column++) {
                if (numbersOnBoard[row][column].isUnmarked()) {
                    rowIsWinner = false;
                    break;
                }
            }
            if (rowIsWinner) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWinningColumn() {
        for (int column = 0; column < 5; column++) {
            boolean columnIsWinner = true;
            for (int row = 0; row < 5; row++) {
                if (numbersOnBoard[row][column].isUnmarked()) {
                    columnIsWinner = false;
                    break;
                }
            }
            if (columnIsWinner) {
                return true;
            }
        }
        return false;
    }

    public long unmarkedSum() {
        long unmarkedSum = 0;
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 5; column++) {
                if (numbersOnBoard[row][column].isUnmarked()) {
                    unmarkedSum += numbersOnBoard[row][column].number;
                }
            }
        }
        return unmarkedSum;
    }

    public Long getLastPlayedNumber() {
        return lastPlayedNumber;
    }

    private static class NumberOnBoard {
        long number;
        boolean marked;

        NumberOnBoard(long number) {
            this.number = number;
        }

        boolean playNumber(long number) {
            if (this.number == number) {
                marked = true;
                return true;
            } else {
                return false;
            }
        }

        boolean isUnmarked() {
            return !marked;
        }
    }
}
