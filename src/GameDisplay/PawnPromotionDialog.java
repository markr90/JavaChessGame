package GameDisplay;

import Pieces.IPiece;
import Pieces.PieceFactory;
import Pieces.Pieces;

import javax.swing.*;
import java.awt.*;

public class PawnPromotionDialog extends JDialog implements PawnPromotionListener {
    Pieces selectedPiece;

    public PawnPromotionDialog(JFrame parentFrame, String title, boolean what) {
        super(parentFrame, title, what);
        setLayout(new GridLayout(0, 1));
        setLocationRelativeTo(parentFrame);
        addButtons();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
    }

    @Override
    public IPiece promptPromotion(boolean isWhite) {
        setVisible(true);
        try {
            return PieceFactory.createPiece(selectedPiece, isWhite);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addButtons() {
        add(queenButton());
        add(rookButton());
        add(knightButton());
        add(bishopButton());
    }

    private JButton queenButton() {
        JButton button = new JButton("Queen");
        button.addActionListener((ae) -> setPieceChosen(Pieces.Queen));
        return button;
    }

    private JButton rookButton() {
        JButton button = new JButton("Rook");
        button.addActionListener((ae) -> setPieceChosen(Pieces.Rook));
        return button;
    }

    private JButton knightButton() {
        JButton button = new JButton("Knight");
        button.addActionListener((ae) -> setPieceChosen(Pieces.Knight));
        return button;
    }

    private JButton bishopButton() {
        JButton button = new JButton("Bishop");
        button.addActionListener((ae) -> setPieceChosen(Pieces.Bishop));
        return button;
    }

    private void closeDialog() {
        setVisible(false);
    }

    private void setPieceChosen(Pieces piece) {
        selectedPiece = piece;
        closeDialog();
    }
}
