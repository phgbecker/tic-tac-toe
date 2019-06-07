package tictactoe;

import tictactoe.exception.InvalidPositionException;
import tictactoe.exception.SpotUnavailableException;
import tictactoe.strategy.BottomLeftToTopRightStrategy;
import tictactoe.strategy.HorizontalStrategy;
import tictactoe.strategy.TopLeftToBottomRightStrategy;
import tictactoe.strategy.VerticalStrategy;

public class TicTacToe {
    private final Board board;
    private char lastPlayer;
    private static final char EMPTY = ' ';

    public TicTacToe(int boardSize) {
        board = new Board(boardSize);
    }

    public PlayResult play(int row, int column) {
        checkPosition(row);
        checkPosition(column);
        lastPlayer = nextPlayer();
        assignSpot(row, column, lastPlayer);

        return getPlayResult();
    }

    private void checkPosition(int position) {
        if (position < 1 || position > board.getSize())
            throw new InvalidPositionException("The position played is invalid!");
    }

    private void assignSpot(int row, int column, char player) {
        if (board.getGrid()[row - 1][column - 1] == EMPTY) {
            board.getGrid()[row - 1][column - 1] = player;
        } else {
            throw new SpotUnavailableException("This spot has already been taken");
        }
    }

    public char nextPlayer() {
        return lastPlayer == 'X' ? 'O' : 'X';
    }

    private PlayResult getPlayResult() {
        if (new HorizontalStrategy().matches(this)
                || new VerticalStrategy().matches(this)
                || new TopLeftToBottomRightStrategy().matches(this)
                || new BottomLeftToTopRightStrategy().matches(this)) {
            return PlayResult.WINNER;
        }

        if (isDraw()) {
            return PlayResult.DRAW;
        }

        return PlayResult.NO_WINNER;
    }

    private boolean isDraw() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int column = 0; column < board.getSize(); column++) {
                if (board.getGrid()[row][column] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public char getLastPlayer() {
        return lastPlayer;
    }
}
