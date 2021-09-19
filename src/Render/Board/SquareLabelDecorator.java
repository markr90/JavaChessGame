package Render.Board;

import Game.Spot;

import java.awt.*;

public class SquareLabelDecorator extends BoardDecorator {
    public SquareLabelDecorator(IBoardDrawer boardDrawer) {
        super(boardDrawer);
    }

    public void draw(Graphics g) {
        graphic.draw(g);
        paintLabels(g);
    }

    private void paintLabels(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        int borderWidth = graphic.getBorderWidth();
        int squareWidth = graphic.getSquareWidth();
        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col++) {
                g2D.setColor(Color.BLACK);
                Spot spot = graphic.getBoard().getSpot(row, col);
                String algebraic = new String(spot.getCoordinate().algebraic());
                g2D.drawString(algebraic, borderWidth + col * squareWidth, (row + 1) * squareWidth + borderWidth);
            }
        }
    }
}
