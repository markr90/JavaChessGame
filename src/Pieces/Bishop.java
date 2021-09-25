package Pieces;

import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;

public class Bishop extends Piece {
    private static MoveSet[] moveSets = {new Diagonal()};

    public Bishop(boolean isWhite) {
        super("B", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "bishop_" + colorSubstring() + ".png";
    }
}
