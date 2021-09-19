package Render.Board;

import Game.Board;
import Game.Game;
import Game.Spot;
import Render.Listeners.IBoardClickChecker;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BoardDrawer implements IBoardDrawer, IBoardClickChecker {
    private Game game;
    private int squareWidth;
    private int borderWidth;

    private BoardSquare[][] boardSquares = new BoardSquare[8][8];


    public BoardDrawer(Game game, int borderWidth, int squareWidth) {
        this.game = game;
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
        return game.Board();
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
                Spot spot = game.Board().getSpot(row, col);
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
