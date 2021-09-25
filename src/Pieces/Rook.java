package Pieces;

import Pieces.Movesets.Castling;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class Rook extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular(), new Castling()};

    public Rook(boolean isWhite) {
        super("R", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "rook_" + colorSubstring() + ".png";
    }
}
