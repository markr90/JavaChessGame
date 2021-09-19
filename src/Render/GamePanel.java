package Render;

import Game.Board;
import Render.Drawers.*;
import Render.Listeners.MouseClickListener;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private IBoardDrawer drawer;

    public GamePanel(Board board, int borderWidth, int squareWidth) {
        drawer = buildBoard(board, borderWidth, squareWidth);
        addMouseListener(new MouseClickListener());
    }

    public void paint(Graphics g) {
        drawer.draw(g);
    }

    private IBoardDrawer buildBoard(Board board, int borderWidth, int squareWidth) {
        BoardDrawer boardDrawer = new BoardDrawer(board, borderWidth, squareWidth);
        //addMouseListener(new MouseClickListener(boardDrawer));
        BoardDecorator squareLabelDecorator = new SquareLabelDecorator(boardDrawer);
        BoardDecorator pieceDecorator = new PieceDecorator(squareLabelDecorator);

        return pieceDecorator;
    }
}
