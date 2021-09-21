package GameDisplay.MouseClicks;

import Game.Game;
import Game.Move;
import GameDisplay.Board.BoardSquare;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseMoveHandler implements MouseListener {
    private Game game;
    private BoardSquare from;
    private BoardSquare currentMouseOverTarget;
    private int clickCount = 0;

    public MouseMoveHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        BoardSquare boardSquare = (BoardSquare) e.getSource();
        if (clickCount == 0) {
            this.from = boardSquare;
            from.setHighlighted(true);
        }

        if (clickCount == 1) {
            Move move = new Move(from.getSpot().getCoordinate(), boardSquare.getSpot().getCoordinate());
            game.handleMove(move);
            from.setHighlighted(false);
            from.drawPiece();
            boardSquare.drawPiece();
            clickCount = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (from != null && currentMouseOverTarget.getSpot().getCoordinate().equals(from.getSpot().getCoordinate())) {
            clickCount++;
        }
        if (from != null && currentMouseOverTarget != null) {
            game.handleMove(new Move(from.getSpot().getCoordinate(), currentMouseOverTarget.getSpot().getCoordinate()));
            from.drawPiece();
            currentMouseOverTarget.drawPiece();
        }

        if (clickCount == 0) {
            from.setHighlighted(false);
            from = null;
            currentMouseOverTarget = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        BoardSquare square = (BoardSquare) e.getSource();
        currentMouseOverTarget = square;
        square.setHighlighted(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        BoardSquare square = (BoardSquare) e.getSource();
        if (from != null && square.getSpot().getCoordinate().equals(from.getSpot().getCoordinate())) {
            return;
        }

        square.setHighlighted(false);
    }
}
