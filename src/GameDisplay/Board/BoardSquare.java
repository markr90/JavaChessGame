package GameDisplay.Board;

import Game.Spot;
import GameDisplay.MouseClicks.DragAndDropMoveHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class BoardSquare extends JButton implements MouseListener {
    private static Color BROWN = new Color(143, 103, 66);
    private static Color LIGHT = new Color(255,248,220);
    private static Color HIGHLIGHTED = new Color(212, 227, 250);

    private DragAndDropMoveHandler dragAndDropMoveHandler;

    private Spot spot;

    public BoardSquare(Spot spot) {
        setMargin(new Insets(0,0,0,0));
        setHorizontalTextPosition(JButton.CENTER);
        setVerticalTextPosition(JButton.CENTER);
        this.spot = spot;
        setMargin(new Insets(0, 0, 0, 0));
        setBackground(squareColor());
        drawPiece();
        addMouseListener(this);
    }

    public void setDragAndDropHandler(DragAndDropMoveHandler dragAndDropHandler) {
        this.dragAndDropMoveHandler = dragAndDropHandler;
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
            BufferedImage bi = resizeIcon(spot.getPiece().Image(), 64, 64);
            ImageIcon icon = new ImageIcon(bi);
            setIcon(icon);
        } else {
            ImageIcon icon = new ImageIcon(
                    new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
            setIcon(icon);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        dragAndDropMoveHandler.handlePress(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragAndDropMoveHandler.handleDrop();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        dragAndDropMoveHandler.handleEnter(this);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        dragAndDropMoveHandler.handleExit(this);
    }
}
