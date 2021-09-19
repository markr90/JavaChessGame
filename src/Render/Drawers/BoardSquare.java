package Render.Drawers;

import Game.Spot;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BoardSquare extends JPanel {
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
}
