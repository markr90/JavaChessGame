package GameDisplay;

import Game.MoveHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MoveHistoryPanel extends JPanel {
    private MoveHistory moveHistory;
    private JTextArea textArea;

    public MoveHistoryPanel(MoveHistory moveHistory) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        this.moveHistory = moveHistory;
        setPreferredSize(new Dimension(150, 0));
        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane (textArea);
        textArea.setFont(new Font("sans-serif", Font.PLAIN, 16));
        textArea.setEditable(false);
        add(scroll);
    }

    public void paint(Graphics g) {
        String moves = "";
        for (int i = 0; i < 100; i++) {
            moves += "aaaa\n";
        }
        textArea.setText(moves);
        super.paint(g);
    }
}
