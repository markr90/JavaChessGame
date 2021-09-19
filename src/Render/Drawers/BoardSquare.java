package Render.Drawers;

import Game.Spot;
import Render.Listeners.ISpotClickChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class BoardSquare extends JPanel implements ISpotClickChecker {
    private Rectangle2D rect;

    public BoardSquare(Spot spot, int boardOffset, int squareWidth) {
        int i = spot.getCoordinate().X();
        int j = spot.getCoordinate().Y();
        int x = boardOffset + i*squareWidth;
        int y = boardOffset + j*squareWidth;
        rect = new Rectangle2D.Float(x, y, squareWidth, squareWidth);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)(g);
        g2d.fill(rect);
    }

    @Override
    public boolean wasSpotClicked(MouseEvent mouseEvent) {
        return rect.contains(mouseEvent.getX(), mouseEvent.getY());
    }
}
