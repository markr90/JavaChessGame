package Render.Board;

import Game.Board;

import java.awt.*;

public interface IBoardDrawer {
    void draw(Graphics g);
    int getBorderWidth();
    int getSquareWidth();
    Board getBoard();
}
