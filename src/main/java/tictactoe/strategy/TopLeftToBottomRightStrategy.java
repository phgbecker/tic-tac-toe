package tictactoe.strategy;

import tictactoe.TicTacToe;

public class TopLeftToBottomRightStrategy implements PlayStrategy {

    @Override
    public boolean matches(TicTacToe game) {
        int numOfMatches = 0;

        for (int i = 0; i < game.getBoard().getSize(); i++) {
            if (game.getBoard().getGrid()[i][i] == game.getLastPlayer()) {
                numOfMatches++;
            }
        }

        return numOfMatches == game.getBoard().getSize();
    }
}
