package Render.Listeners;

import Game.Move;
import Game.MovePublisher;
import Render.Drawers.BoardSquare;
import Render.GameFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoveClickListener implements MouseListener {
    private IBoardClickChecker boardClickChecker;
    private BoardSquare lastSquareClicked;
    private final MovePublisher movePublisher;
    private GameFrame gameFrame;

    public MoveClickListener(MovePublisher movePublisher, GameFrame gameFrame) {
        this.movePublisher = movePublisher;
        this.gameFrame = gameFrame;
    }

    public void registerBoard(IBoardClickChecker boardClickChecker) {
        this.boardClickChecker = boardClickChecker;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        BoardSquare boardSquare = boardClickChecker.getSquareClicked(e);
        if (boardSquare != null && !boardSquare.getSpot().hasPiece() && lastSquareClicked == null) {
            return;
        }

        if (boardSquare != null && lastSquareClicked != null) {
            Move move = new Move(lastSquareClicked.getCoordinate(), boardSquare.getCoordinate());
            movePublisher.sendMove(move);
            lastSquareClicked.setHighlighted(false);
            lastSquareClicked = null;
        } else if (boardSquare != null && lastSquareClicked == null) {
            boardSquare.setHighlighted(true);
            lastSquareClicked = boardSquare;
        } else if (lastSquareClicked != null) {
            lastSquareClicked.setHighlighted(false);
            lastSquareClicked = null;
        }

        gameFrame.Update();

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
