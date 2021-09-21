package GameDisplay.Board;

import Game.Spot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardSquare extends JButton {
    private static Color BROWN = new Color(143, 103, 66);
    private static Color LIGHT = new Color(255,248,220);
    private static Color HIGHLIGHTED = new Color(212, 227, 250);

    private Spot spot;

    public BoardSquare(Spot spot) {
        setMargin(new Insets(0,0,0,0));
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        this.spot = spot;
        setMargin(new Insets(0, 0, 0, 0));
        setBackground(squareColor());
        drawPiece();
    }

    public Spot getSpot() {
        return spot;
    }

    public void setHighlighted(boolean value) {
        if (value) {
            setBackground(HIGHLIGHTED);
        } else {
            setBackground(squareColor());
        }
    }

    public Color squareColor() {
        if (isLightSquare(spot.getCoordinate().row(), spot.getCoordinate().col())) {
            return LIGHT;
        } else {
            return BROWN;
        }
    }

    public void drawPiece() {
        if (spot.hasPiece()) {
            setIcon(spot.getPiece().icon());
        } else {
            setIcon(null);
        }
    }

    private boolean isLightSquare(int row, int col) {
        return row % 2 == 0 && col % 2 == 0 || row % 2 == 1 && col % 2 == 1;
    }

    private static BufferedImage resizeIcon(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}
