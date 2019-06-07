package tictactoe;

import tictactoe.exception.InvalidGridSizeException;

public class Board {
    private final int size;
    private char[][] grid;
    private static final char EMPTY = ' ';

    public Board(int size) {
        this.size = size;

        if (size > 2) {
            setUp();
        } else {
            throw new InvalidGridSizeException("In order for this game to work, grid sizes should be at least 3x3");
        }
    }

    private void setUp() {
        grid = new char[size][size];

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                grid[row][column] = EMPTY;
            }
        }
    }

    public String show() {
        StringBuilder board = new StringBuilder();

        for (int row = 0; row < size; row++) {
            StringBuilder columnSeparator = new StringBuilder();
            addRow(board, grid[row], columnSeparator);
            addRowSeparator(board, columnSeparator);
        }

        removeLastRowSeparator(board);

        return board.toString();
    }

    private void addRow(StringBuilder board, char[] chars, StringBuilder columnSeparator) {
        for (int column = 0; column < size; column++) {
            board.append(" ");
            board.append(chars[column]);
            board.append(" |");
            columnSeparator.append("---|");
        }
    }

    private void addRowSeparator(StringBuilder board, StringBuilder columnSeparator) {
        board.deleteCharAt(board.length() - 1);
        board.append("\n");
        columnSeparator.deleteCharAt(columnSeparator.length() - 1);
        board.append(columnSeparator);
        board.append("\n");
    }

    private void removeLastRowSeparator(StringBuilder board) {
        board.delete((board.deleteCharAt(board.length() - 1).lastIndexOf("\n")), board.length());
    }

    public int getSize() {
        return size;
    }

    public char[][] getGrid() {
        return grid;
    }
}
