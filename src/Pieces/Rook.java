package Pieces;

import Game.Board;
import Game.Move;

import java.io.IOException;

public class Rook extends Piece {
    public Rook(boolean isWhite) throws IOException {
        super("R", isWhite);
    }

    @Override
    public boolean IsMoveLegal(Board board, Move move) {
        return false;
    }
}
