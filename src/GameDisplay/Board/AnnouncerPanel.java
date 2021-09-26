package GameDisplay.Board;

import Game.Game;
import Game.IGameObserver;
import Game.GameState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AnnouncerPanel extends JPanel implements IGameObserver {
    private JLabel gameStatus;
    private JLabel infoMessage;
    private Game game;

    public AnnouncerPanel(Game game) {
        setMaximumSize(getMaximumSize());
        this.game = game;
        gameStatus = new JLabel(getMessage());
        gameStatus.setFont(new Font("Sans-serif", Font.PLAIN, 42));
        gameStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameStatus.setBorder(new EmptyBorder(10,0,0,0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        infoMessage = new JLabel(infoMessage());
        infoMessage.setFont(new Font("Sans-serif", Font.PLAIN, 20));
        infoMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoMessage.setBorder(new EmptyBorder(0,0,10,0));
        infoMessage.setPreferredSize(new Dimension(200, 40));
        infoMessage.setMinimumSize(new Dimension(200, 40));
        add(gameStatus);
        add(infoMessage);
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

    private String infoMessage() {
        return game.getInfoMessage();
    }

    public void update(Game game) {
        gameStatus.setText(getMessage());
        infoMessage.setText(infoMessage());
        this.repaint();
    }
}
