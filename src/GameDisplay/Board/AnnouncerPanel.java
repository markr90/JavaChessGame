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
        label.setFont(new Font("Sans-serif", Font.PLAIN, 42));
        add(label);
    }

    private String getMessage() {
        String checkedMessage = game.curPlayer().isChecked() ? ", Check!" : "";
        if (game.gameState() == GameState.ACTIVE) {
            return game.curPlayer().Name() + "'s turn" + checkedMessage;
        } else if (game.gameState() == GameState.WHITE_WINS) {
            return "Checkmate! WHITE WINS";
        } else if (game.gameState() == GameState.BLACK_WINS) {
            return "Checkmate! BLACK WINS";
        } else {
            return "INACTIVE";
        }
    }

    public void update(Game game) {
        label.setText(getMessage());
        this.repaint();
    }
}
