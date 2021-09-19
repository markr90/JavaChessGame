package Render.Drawers;

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
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                g2D.setBackground(new Color(0,0,0,0));
                g2D.setColor(Color.BLACK);
                Spot spot = graphic.getBoard().getSpot(i, j);
                String algebraic = new String(spot.getCoordinate().Algebraic());
                g2D.drawString(algebraic, borderWidth + j * squareWidth, (i + 1) * squareWidth + borderWidth);
            }
        }
    }
}
