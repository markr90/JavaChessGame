package GameDisplay.MouseClicks;

import GameDisplay.Board.BoardSquare;
import Game.Game;
import Game.Move;

import java.awt.event.ActionEvent;

public class MoveHandler {
    private Game game;
    private BoardSquare lastSquareClicked;
    private int clickCount;

    public MoveHandler(Game game) {
        this.game = game;
    }

    public void subscribeSquare(BoardSquare square) {
        square.addActionListener((ae) -> handleSquareAction(ae));
    }

    public void unsubscribeSquare(BoardSquare square) {
        square.removeActionListener((ae) -> handleSquareAction(ae));
    }

    private void handleSquareAction(ActionEvent actionEvent) {
        System.out.println("action");
        BoardSquare boardSquare = (BoardSquare) actionEvent.getSource();

        if (clickCount == 0) {
            clickCount++;
            lastSquareClicked = boardSquare;
            boardSquare.setHighlighted(true);
        } else {
            Move move = new Move(lastSquareClicked.getSpot().getCoordinate(), boardSquare.getSpot().getCoordinate());
            game.handleMove(move);
            lastSquareClicked.setHighlighted(false);
            lastSquareClicked.drawPiece();
            boardSquare.drawPiece();
            clickCount = 0;
        }
    }
}
