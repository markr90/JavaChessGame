package Render;

import Game.Game;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {
    private PanelFactory panelFactory;
    private GamePanel gamePanel;


    public ContainerPanel(Game game, IDisplay display) {
        panelFactory = new PanelFactory(game, display);
        gamePanel = panelFactory.createGamePanel();
    }

    @Override
    public void paint(Graphics g) {
        gamePanel.paint(g);
    }
}
