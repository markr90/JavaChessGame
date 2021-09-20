package GameDisplay.MouseClicks;

import GameDisplay.Board.BoardSquare;
import Game.Game;
import Game.Move;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickMoveHandler implements MouseListener {
    private Game game;
    private BoardSquare lastSquareClicked;
    private int clickCount;

    public ClickMoveHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        BoardSquare boardSquare = (BoardSquare) e.getSource();

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

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
