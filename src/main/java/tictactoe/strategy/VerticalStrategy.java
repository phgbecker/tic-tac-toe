package tictactoe.strategy;

import tictactoe.TicTacToe;

public class VerticalStrategy implements PlayStrategy {

    @Override
    public boolean matches(TicTacToe game) {
        int numOfMatches;

        for (int row = 0; row < game.getBoard().getSize(); row++) {
            numOfMatches = 0;

            for (int column = 0; column < game.getBoard().getSize(); column++) {
                if (game.getBoard().getGrid()[column][row] == game.getLastPlayer()) {
                    numOfMatches++;
                }
            }

            if (numOfMatches == game.getBoard().getSize()) {
                return true;
            }
        }

        return false;
    }
}
