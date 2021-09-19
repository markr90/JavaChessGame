package Render.Drawers;

import Game.Coordinate;
import Game.Spot;
import Render.Listeners.ISpotClickChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class BoardSquare extends JComponent implements ISpotClickChecker {
    private static Color BROWN = new Color(143, 103, 66);
    private static Color LIGHT = new Color(255,248,220);
    private static Color HIGHLIGHTED = new Color(212, 227, 250);

    private Rectangle2D rect;
    private Spot spot;

    private boolean isHighlighted = false;

    public BoardSquare(Spot spot, int boardOffset, int squareWidth) {
        int row = spot.getCoordinate().row();
        int col = spot.getCoordinate().col();
        int x = boardOffset + col*squareWidth;
        int y = boardOffset + row*squareWidth;
        rect = new Rectangle2D.Float(x, y, squareWidth, squareWidth);
        this.spot = spot;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)(g);
        g2d.setColor(squareColor());
        g2d.fill(rect);
    }

    public Coordinate getCoordinate() {
        return spot.getCoordinate();
    }

    public Spot getSpot() {
        return spot;
    }

    @Override
    public boolean wasSpotClicked(MouseEvent mouseEvent) {
        return rect.contains(mouseEvent.getX(), mouseEvent.getY());
    }

    public void setHighlighted(boolean value) {
        isHighlighted = value;
    }

    public Color squareColor() {
        if (isHighlighted) {
            return HIGHLIGHTED;
        }

        if (isLightSquare(spot.getCoordinate().row(), spot.getCoordinate().col())) {
            return LIGHT;
        } else {
            return BROWN;
        }
    }

    private boolean isLightSquare(int row, int col) {
        return row % 2 == 0 && col % 2 == 0 || row % 2 == 1 && col % 2 == 1;
    }
}
