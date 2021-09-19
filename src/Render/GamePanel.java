package Render;

import Game.Game;
import Render.Board.*;
import Render.Listeners.IBoardClickChecker;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private IBoardDrawer drawer;
    private IBoardClickChecker boardClickerChecker;

    public GamePanel(Game game, int borderWidth, int squareWidth) {
        drawer = buildBoard(game, borderWidth, squareWidth);

    }

    public IBoardClickChecker getBoardClickerChecker() {
        return boardClickerChecker;
    }

    public void paint(Graphics g) {
        drawer.draw(g);
    }

    private IBoardDrawer buildBoard(Game game, int borderWidth, int squareWidth) {
        BoardDrawer boardDrawer = new BoardDrawer(game, borderWidth, squareWidth);
        boardClickerChecker = boardDrawer;
        BoardDecorator squareLabelDecorator = new SquareLabelDecorator(boardDrawer);
        BoardDecorator pieceDecorator = new PieceDecorator(squareLabelDecorator);

        return pieceDecorator;
    }
}
