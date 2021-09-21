package GameDisplay.Board;

import Game.Game;
import Game.IGameObserver;
import Game.GameState;

import javax.swing.*;
import java.awt.*;

public class AnnouncerPanel extends JPanel implements IGameObserver {
    private JLabel label;
    private Game game;

    public AnnouncerPanel(Game game) {
        this.game = game;
        label = new JLabel(getMessage());
        label.setFont(new Font("Sans-serif", Font.PLAIN, 50));
        add(label);
    }

    private String getMessage() {
        if (game.GameState() == GameState.ACTIVE) {
            return game.CurPlayer().Name() + "'s turn";
        } else {
            return "INACTIVE";
        }
    }

    public void update(Game game) {
        label.setText(getMessage());
        this.repaint();
    }
}
