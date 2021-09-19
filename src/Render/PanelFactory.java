package Render;

import Game.Game;
import Render.Announcer.PlayerTurnAnnouncer;

public class PanelFactory {
    private int borderWidth;
    private int squareWidth;
    private Game game;
    private IDisplay display;

    public PanelFactory(Game game, IDisplay display) {
        this.display = display;
        int width = display.GetWidth();
        int height = display.GetHeight();
        this.game = game;
        borderWidth = (int) (0.3 * width / 2);
        squareWidth = (int) ((width - 2*borderWidth) / 8.0);
    }

    public GamePanel createGamePanel() {
        GamePanel gamePanel = new GamePanel(game, borderWidth, squareWidth);
        return gamePanel;
    }

    public PlayerTurnAnnouncer createPlayerTurnAnnouncer() {
        return new PlayerTurnAnnouncer(game, display.GetWidth(), borderWidth);
    }
}
