package Render;

import Game.Game;
import Render.Announcer.PlayerTurnAnnouncer;
import Render.Listeners.MoveClickListener;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel{
    private PanelFactory panelFactory;
    private GamePanel gamePanel;
    private PlayerTurnAnnouncer playerTurnAnnouncer;


    private IDisplay display;
    private Game game;


    public ContainerPanel(Game game, IDisplay display) {
        this.game = game;
        panelFactory = new PanelFactory(game, display);
        this.display = display;
        addPanels();
        addListeners();
    }

    @Override
    public void paint(Graphics g) {
        gamePanel.paint(g);
        playerTurnAnnouncer.paint(g);
    }

    private void addListeners() {
        MoveClickListener moveClickListener = new MoveClickListener(game, gamePanel.getBoardClickerChecker());
        moveClickListener.getDisplayUpdater().attachDisplay(display);
        addMouseListener(moveClickListener);
    }

    private void addPanels() {
        gamePanel = panelFactory.createGamePanel();
        playerTurnAnnouncer = panelFactory.createPlayerTurnAnnouncer();
    }
}
