package GameDisplay.MouseClicks;

import GameDisplay.Board.BoardSquare;
import Game.Game;
import Game.Move;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MoveHandler {
    private ArrayList<BoardSquare> squares;
    private Game game;
    private BoardSquare lastSquareClicked;
    private int clickCount;

    public MoveHandler(Game game) {
        squares = new ArrayList<>();
        this.game = game;
    }

    public void subscribeSquare(BoardSquare square) {
        square.addActionListener((ae) -> handleSquareAction(ae));
        squares.add(square);
    }

    private void handleSquareAction(ActionEvent actionEvent) {
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
