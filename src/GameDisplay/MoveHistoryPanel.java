package GameDisplay;

import Game.Game;
import Game.HistoricMove;
import Game.IGameObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MoveHistoryPanel extends JPanel implements IGameObserver {
    private JTextArea textArea;

    public MoveHistoryPanel(Game game) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(150, 0));
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane (textArea);
        textArea.setFont(new Font("sans-serif", Font.PLAIN, 16));
        textArea.setEditable(false);
        add(scroll);
        game.getGamePublisher().subscribe(this);
    }

    public void update(Game game) {
        String text = "";
        for (HistoricMove move : game.getMoveHistory().moves()) {
            text += move.algebraic() + "\n";
        }
        textArea.setText(text);
        this.repaint();
    }
}
