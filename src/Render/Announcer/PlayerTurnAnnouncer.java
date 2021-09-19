package Render.Announcer;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerTurnAnnouncer extends JPanel {
    private Game game;
    private int posX;

    public PlayerTurnAnnouncer(Game game, JFrame frame) {
        int width = frame.getWidth();
        posX = width / 2;
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(posX, 0, 50 ,50);
    }
}
