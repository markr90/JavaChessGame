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
        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col++) {
                boardSquares[row][col].paintComponent(g);
            }
        }
    }

    private void initRectangles() {
        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col++) {
                Spot spot = board.getSpot(row, col);
                boardSquares[row][col] = new BoardSquare(spot, borderWidth, squareWidth);
            }
        }
    }

    @Override
    public BoardSquare getSquareClicked(MouseEvent mouseEvent) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (boardSquares[row][col].wasSpotClicked(mouseEvent)) {
                    return boardSquares[row][col];
                }
            }
        }

        return null;
    }
}
