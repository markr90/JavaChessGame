package Render;

import Game.Game;
import Render.Announcer.PlayerTurnAnnouncer;
import Render.Board.*;
import Render.Listeners.IDisplayUpdater;
import Render.Listeners.MoveClickListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private IBoardDrawer drawer;

    private ArrayList<IDisplayUpdater> displayUpdaters = new ArrayList<>();

    public GamePanel(Game game, int borderWidth, int squareWidth) {
        drawer = buildBoard(game, borderWidth, squareWidth);
    }

    public void paint(Graphics g) {
        drawer.draw(g);
    }

    private IBoardDrawer buildBoard(Game game, int borderWidth, int squareWidth) {
        BoardDrawer boardDrawer = new BoardDrawer(game, borderWidth, squareWidth);

        MoveClickListener moveClickListener = new MoveClickListener(game, boardDrawer);
        displayUpdaters.add(moveClickListener.getDisplayUpdater());

        addMouseListener(moveClickListener);
        BoardDecorator squareLabelDecorator = new SquareLabelDecorator(boardDrawer);
        BoardDecorator pieceDecorator = new PieceDecorator(squareLabelDecorator);

        return pieceDecorator;
    }

    public void attachDisplay(IDisplay display) {
        for (IDisplayUpdater displayUpdater: displayUpdaters) {
            displayUpdater.attachDisplay(display);
        }
    }
}
