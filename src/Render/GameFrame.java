package Render;
import Game.Game;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements IDisplay {
    private Game game;
    private JFrame frame;

    private int width;
    private int height;

    public GameFrame(Game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
    }

    public void Show() {
        frame = CreateFrame();
        frame.setVisible(true);
    }

    public void Update() {
        frame.repaint();
    }

    public int GetHeight() {
        return this.height;
    }

    public int GetWidth() {
        return this.width;
    }

    private JFrame CreateFrame() {

        JFrame frame = new JFrame();
        frame.setSize(width, height);

        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ContainerPanel containerPanel = new ContainerPanel(game, this);
        frame.getContentPane().add(containerPanel);
        return frame;
    }
}
