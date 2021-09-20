package GameDisplay.Board;

import Game.Game;
import Game.Coordinate;
import GameDisplay.MouseClicks.MouseMoveHandler;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JPanel {
    private BoardSquare[][] boardSquares = new BoardSquare[8][8];
    private Game game;

    public ChessBoard(Game game) {
        this.game = game;
        setLayout(new GridLayout(0, 9));
        initBoardSquares();
        addBoardSquares();
    }

    private void initBoardSquares() {
        MouseMoveHandler mouseMoveHandler = new MouseMoveHandler(game);
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                boardSquares[r][c] = new BoardSquare(game.Board().getSpot(r, c));
                boardSquares[r][c].setBackground(boardSquares[r][c].squareColor());
                add(boardSquares[r][c]);
                boardSquares[r][c].addMouseListener(mouseMoveHandler);
            }
        }
    }

    private void addBoardSquares() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 9; c++) {
                if (c == 0) {
                    add(new JLabel("" + (8 - r), SwingConstants.CENTER));
                } else {
                    add(boardSquares[r][c-1]);
                }
            }
        }
        // last label row
        add(new JLabel(""));
        for (int c = 0; c < 8; c++) {
            char label = Coordinate.columnIndices[c];
            add(new JLabel("" + label, SwingConstants.CENTER));
        }
    }

    /**
     * Override the preferred size to return the largest it can, in
     * a square shape.  Must (must, must) be added to a GridBagLayout
     * as the only component (it uses the parent as a guide to size)
     * with no GridBagConstraint (so it is centered).
     */
    @Override
    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Dimension prefSize = null;
        Component c = getParent();
        if (c == null) {
            prefSize = new Dimension(
                    (int)d.getWidth(),(int)d.getHeight());
        } else if (c!=null &&
                c.getWidth()>d.getWidth() &&
                c.getHeight()>d.getHeight()) {
            prefSize = c.getSize();
        } else {
            prefSize = d;
        }
        int w = (int) prefSize.getWidth();
        int h = (int) prefSize.getHeight();
        // the smaller of the two sizes
        int s = (w>h ? h : w);
        return new Dimension(s,s);
    }
}
