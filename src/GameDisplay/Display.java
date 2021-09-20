package GameDisplay;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class Display implements IDisplay {
    private Game game;
    private JFrame frame;
    private int width;
    private int height;
    private PanelFactory panelFactory;

    public Display(Game game, int width, int height) {
        this.game = game;
        this.width = width;
        this.height = height;
        panelFactory = new PanelFactory(game, width, height);
    }

    public void Show() {
        frame = CreateFrame();
        frame.setVisible(true);
    }

    public int GetHeight() {
        return this.height;
    }

    public int GetWidth() {
        return this.width;
    }

    public void Update() {
        frame.repaint();
    }

    private JFrame CreateFrame() {

        JFrame frame = new JFrame();
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = panelFactory.createMenuBar();
        frame.setJMenuBar(menu);

        JPanel mainPanel = panelFactory.createMainPanel();
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setMinimumSize(frame.getSize());

        frame.setLocationRelativeTo(null);

        return frame;
    }

}
