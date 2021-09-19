package Render;
import Game.Game;
import Game.MovePublisher;
import Render.Listeners.MoveClickListener;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements IDisplay {
    private Game game;
    private JFrame frame;
    private MovePublisher movePublisher;

    public GameFrame(Game game, int width, int height) {
        this.game = game;
        movePublisher = new MovePublisher(game);
        this.frame = CreateFrame(width, height);
    }

    public void Show() {
        frame.setVisible(true);
    }

    public void Update() {
        frame.repaint();
    }

    private JFrame CreateFrame(int width, int height) {
        int borderWidth = (int) (0.3 * width / 2);
        int squareWidth = (int) ((width - 2*borderWidth) / 8.0);

        JFrame frame = new JFrame();
        frame.setSize(width, height);

        JPanel gamePanel = createGamePanel(borderWidth, squareWidth);

        frame.getContentPane().add(gamePanel);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JPanel createGamePanel(int borderWidth, int squareWidth) {
        GamePanel gamePanel = new GamePanel(game.Board(), borderWidth, squareWidth);
        gamePanel.subscribeBoardClickListener(new MoveClickListener(movePublisher, this));
        return gamePanel;
    }
}
