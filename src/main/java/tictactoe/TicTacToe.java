package tictactoe;

import tictactoe.exception.BoardSizeUnavailableException;
import tictactoe.exception.InvalidPositionException;
import tictactoe.exception.SpotUnavailableException;

public class TicTacToe {
    private final int boardSize;
    private char[][] board;
    private static final char EMPTY = '\0';
    private char lastPlayer;

    public TicTacToe() {
        this.boardSize = 3;
        setUpBoard(boardSize);
    }

    public TicTacToe(int boardSize) {
        this.boardSize = boardSize;
        setUpBoard(boardSize);
    }

    private void setUpBoard(int size) {
        if (size > 3 || size < 3) {
            throw new BoardSizeUnavailableException("Oops, board sizes greater, or less, than 3 have not yet been implemented");
        }

        board = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                board[row][column] = EMPTY;
            }
        }
    }

    public PlayResult play(int row, int column) {
        checkPosition(row);
        checkPosition(column);
        lastPlayer = nextPlayer();
        assignSpot(row, column, lastPlayer);

        return getPlayResult();
    }

    private void checkPosition(int position) {
        if (position < 1 || position > boardSize)
            throw new InvalidPositionException("The position played is invalid!");
    }

    private void assignSpot(int row, int column, char player) {
        if (board[row - 1][column - 1] == EMPTY) {
            board[row - 1][column - 1] = player;
        } else {
            throw new SpotUnavailableException("This spot has already been taken");
        }
    }

    public char nextPlayer() {
        return lastPlayer == 'X' ? 'O' : 'X';
    }

    private PlayResult getPlayResult() {
        for (int scanIndex = 0; scanIndex < boardSize; scanIndex++) {
            if (playMatchesHorizontalLines(scanIndex)
                    || playMatchesVerticalLines(scanIndex)
                    || playMatchesTopLeftToBottomRight()
                    || playMatchesBottomLeftToTopRight()) {
                return PlayResult.WINNER;
            }
        }

        if (isDraw()) {
            return PlayResult.DRAW;
        }

        return PlayResult.NO_WINNER;
    }

    private boolean playMatchesHorizontalLines(int row) {
        return board[row][0] == lastPlayer
                && board[row][1] == lastPlayer
                && board[row][2] == lastPlayer;
    }

    private boolean playMatchesVerticalLines(int column) {
        return board[0][column] == lastPlayer
                && board[1][column] == lastPlayer
                && board[2][column] == lastPlayer;
    }

    private boolean playMatchesTopLeftToBottomRight() {
        return board[0][0] == lastPlayer
                && board[1][1] == lastPlayer
                && board[2][2] == lastPlayer;
    }

    private boolean playMatchesBottomLeftToTopRight() {
        return board[0][2] == lastPlayer
                && board[1][1] == lastPlayer
                && board[2][0] == lastPlayer;
    }

    private boolean isDraw() {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                if (board[row][column] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

}
