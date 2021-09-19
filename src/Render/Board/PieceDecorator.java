package Render.Board;

import Game.Board;
import Game.Spot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PieceDecorator extends BoardDecorator {
    public PieceDecorator(IBoardDrawer boardDrawer) {
        super(boardDrawer);
    }

    public void draw(Graphics g) {
        graphic.draw(g);
        paintPieces(g);
    }

    public void paintPieces(Graphics g) {
        Board board = graphic.getBoard();
        int borderWidth = graphic.getBorderWidth();
        int squareWidth = graphic.getSquareWidth();
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = board.getSpot(i, j);

                if (spot.hasPiece()) {
                    BufferedImage image = spot.getPiece().Image();
                    image = resize(image, squareWidth, squareWidth);
                    g2D.drawImage(image, borderWidth + j * squareWidth, i * squareWidth + borderWidth, null);
                }
            }
        }
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
