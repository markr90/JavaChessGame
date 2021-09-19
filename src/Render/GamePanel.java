package Render;

import Game.Board;
import Render.Drawers.*;
import Render.Listeners.MoveClickListener;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private IBoardDrawer drawer;
    private BoardDrawer boardDrawer;

    public GamePanel(Board board, int borderWidth, int squareWidth) {
        drawer = buildBoard(board, borderWidth, squareWidth);
    }

    public void paint(Graphics g) {
        drawer.draw(g);
    }

    public void subscribeBoardClickListener(MoveClickListener moveClickListener) {
        moveClickListener.registerBoard(boardDrawer);
        addMouseListener(moveClickListener);
    }

    private IBoardDrawer buildBoard(Board board, int borderWidth, int squareWidth) {
        boardDrawer = new BoardDrawer(board, borderWidth, squareWidth);
        BoardDecorator squareLabelDecorator = new SquareLabelDecorator(boardDrawer);
        BoardDecorator pieceDecorator = new PieceDecorator(squareLabelDecorator);

        return pieceDecorator;
    }
}
