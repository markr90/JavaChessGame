package Render.Listeners;

import Game.Spot;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {
    IBoardClickChecker boardClickChecker;

    public MouseClickListener(IBoardClickChecker boardClickChecker) {
        this.boardClickChecker = boardClickChecker;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Spot spot = boardClickChecker.getSpotClicked(e);
        System.out.println(spot.getCoordinate().algebraic());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
