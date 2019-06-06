package tictactoe;

import org.junit.Before;
import org.junit.Test;
import tictactoe.exception.BoardSizeUnavailableException;
import tictactoe.exception.InvalidPositionException;
import tictactoe.exception.SpotUnavailableException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TicTacToeTest {
    private TicTacToe ticTacToe;

    @Before
    public void setUp() throws BoardSizeUnavailableException {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void givenUnavailableBoardSize_whenNewGame_thenBoardUnavailableExceptionIsThrown() {
        assertThatThrownBy(
                () -> new TicTacToe(9)
        ).isInstanceOf(BoardSizeUnavailableException.class);
    }

    @Test
    public void givenARowIsOutsideOfBoard_whenPlay_thenRuntimeExceptionIsThrown() {
        assertThatThrownBy(
                () -> ticTacToe.play(5, 2)
        ).isInstanceOf(InvalidPositionException.class);
    }

    @Test
    public void givenAColumnIsOutsideOfBoard_whenPlay_thenRuntimeExceptionIsThrown() {
        assertThatThrownBy(
                () -> ticTacToe.play(2, 5)
        ).isInstanceOf(InvalidPositionException.class);
    }

    @Test
    public void givenAPlay_whenSpotIsOccupied_thenRuntimeExceptionIsThrown() {
        ticTacToe.play(2, 2);

        assertThatThrownBy(
                () -> ticTacToe.play(2, 2)
        ).isInstanceOf(SpotUnavailableException.class);
    }

    @Test
    public void givenFirstTurn_whenNextPlayer_thenItShouldBePlayerX() {
        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');
    }

    @Test
    public void givenSecondTurn_whenNextPlayer_thenItShouldBePlayerY() {
        ticTacToe.play(2, 2); // X

        assertThat(ticTacToe.nextPlayer()).isEqualTo('O');
    }

    @Test
    public void givenSecondRound_whenNextPlayer_thenItShouldBePlayerX() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(2, 2); // O

        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');
    }

    @Test
    public void givenACertainMoves_whenPlay_thenNoWinners() {
        assertThat(ticTacToe.play(1, 1)).isEqualTo(PlayResult.NO_WINNER); // X is NOT the WINNER
        assertThat(ticTacToe.play(1, 2)).isEqualTo(PlayResult.NO_WINNER); // O is NOT the WINNER
        assertThat(ticTacToe.play(2, 1)).isEqualTo(PlayResult.NO_WINNER); // X is NOT the WINNER
        assertThat(ticTacToe.play(2, 2)).isEqualTo(PlayResult.NO_WINNER); // O is NOT the WINNER
        assertThat(ticTacToe.play(3, 3)).isEqualTo(PlayResult.NO_WINNER); // X is NOT the WINNER
        assertThat(ticTacToe.play(3, 1)).isEqualTo(PlayResult.NO_WINNER); // O is NOT the WINNER
    }

    @Test
    public void givenXHasPlayedAllHorizontalLines_whenPlay_thenPlayerXIsTheWinner() {
        ticTacToe.play(3, 1); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(3, 2); // X
        ticTacToe.play(2, 2); // O

        assertThat(ticTacToe.play(3, 3)).isEqualTo(PlayResult.WINNER); // X is the WINNER
    }

    @Test
    public void givenOHasPlayedAllHorizontalLines_whenPlay_thenPlayerOIsTheWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 3); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(3, 3); // X

        assertThat(ticTacToe.play(3, 2)).isEqualTo(PlayResult.WINNER); // O is the WINNER
    }

    @Test
    public void givenXHasPlayedAllVerticalLines_whenPlay_thenPlayerXIsTheWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 3); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 3); // O

        assertThat(ticTacToe.play(3, 1)).isEqualTo(PlayResult.WINNER); // X is the WINNER
    }

    @Test
    public void givenOHasPlayedAllVerticalLines_whenPlay_thenPlayerOIsTheWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(1, 3); // X

        assertThat(ticTacToe.play(3, 2)).isEqualTo(PlayResult.WINNER); // O is the WINNER
    }

    @Test
    public void givenXHasPlayedFromTopLeftToBottomRight_whenPlay_thenPlayerXIsTheWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(2, 3); // O

        assertThat(ticTacToe.play(3, 3)).isEqualTo(PlayResult.WINNER); // X is the WINNER
    }

    @Test
    public void givenOHasPlayedFromTopLeftToBottomRight_whenPlay_thenPlayerOIsTheWinner() {
        ticTacToe.play(1, 2); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(3, 2); // X

        assertThat(ticTacToe.play(3, 3)).isEqualTo(PlayResult.WINNER); // O is the WINNER
    }

    @Test
    public void givenXHasPlayedFromBottomLeftToTopRight_whenPlay_thenPlayerXIsTheWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(2, 1); // O

        assertThat(ticTacToe.play(3, 1)).isEqualTo(PlayResult.WINNER); // X is the WINNER
    }

    @Test
    public void givenOHasPlayedFromBottomLeftToTopRight_whenPlay_thenPlayerOIsTheWinner() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 3); // O
        ticTacToe.play(2, 1); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(3, 2); // X

        assertThat(ticTacToe.play(3, 1)).isEqualTo(PlayResult.WINNER); // O is the WINNER
    }

    @Test
    public void givenAllSpotsAreFilled_whenPlay_thenTheResultIsDraw() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(2, 1); // O
        ticTacToe.play(1, 2); // X
        ticTacToe.play(2, 2); // O
        ticTacToe.play(2, 3); // X
        ticTacToe.play(1, 3); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(3, 2); // O

        assertThat(ticTacToe.play(3, 3)).isEqualTo(PlayResult.DRAW); // All spots have been field, so we have a DRAW
    }

}
