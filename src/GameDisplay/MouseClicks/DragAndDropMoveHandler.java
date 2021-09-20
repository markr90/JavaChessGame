package GameDisplay.MouseClicks;

import Game.Game;
import Game.Move;
import GameDisplay.Board.BoardSquare;

public class DragAndDropMoveHandler {
    private Game game;
    private BoardSquare from;
    private BoardSquare currentMouseOverTarget;

    public DragAndDropMoveHandler(Game game) {
        this.game = game;
    }

    public void handlePress(BoardSquare from) {
        this.from = from;
        from.setHighlighted(true);
    }

    public void handleDrop() {
        if (from != null && currentMouseOverTarget != null) {
            game.handleMove(new Move(from.getSpot().getCoordinate(), currentMouseOverTarget.getSpot().getCoordinate()));
            from.drawPiece();
            currentMouseOverTarget.drawPiece();
        }
        from.setHighlighted(false);
        from = null;
        currentMouseOverTarget = null;
    }

    public void handleEnter(BoardSquare square) {
        currentMouseOverTarget = square;
        square.setHighlighted(true);
    }

    public void handleExit(BoardSquare square) {
        if (from != null && square.getSpot().getCoordinate().equals(from.getSpot().getCoordinate())) {
            return;
        }

        square.setHighlighted(false);
    }
}
