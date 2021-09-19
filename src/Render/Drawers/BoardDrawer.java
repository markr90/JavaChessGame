package Render.Drawers;

import Game.Board;
import Game.Spot;
import Render.Listeners.IBoardClickChecker;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BoardDrawer implements IBoardDrawer, IBoardClickChecker {
    private Board board;
    private int squareWidth;
    private int borderWidth;

    private static Color BROWN = new Color(143, 103, 66);
    private static Color LIGHT = new Color(255,248,220);

    private BoardSquare[][] boardSquares = new BoardSquare[8][8];


    public BoardDrawer(Board board, int borderWidth, int squareWidth) {
        this.board = board;
        this.squareWidth = squareWidth;
        this.borderWidth = borderWidth;
        initRectangles();
    }

    public void draw(Graphics g) {
        paintBoard(g);
    }

    @Override
    public int getBorderWidth() {
        return borderWidth;
    }

    @Override
    public int getSquareWidth() {
        return squareWidth;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    private void paintBoard(Graphics g) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(isBrownSquare(i, j) ? BROWN : LIGHT);
                boardSquares[i][j].paintComponent(g);
            }
        }
    }

    private void initRectangles() {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = board.getSpot(i, j);
                boardSquares[i][j] = new BoardSquare(spot, borderWidth, squareWidth);
            }
        }
    }

    private boolean isBrownSquare(int x, int y) {
        return x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1;
    }

    @Override
    public Spot getSpotClicked(MouseEvent mouseEvent) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (boardSquares[row][col].wasSpotClicked(mouseEvent)) {
                    return board.getSpot(row, col);
                }
            }
        }

        return null;
    }
}
