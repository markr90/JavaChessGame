package Pieces;

import Pieces.Movesets.KnightMoveSet;
import Pieces.Movesets.MoveSet;

public class Knight extends Piece {
    private static MoveSet[] moveSets = {new KnightMoveSet()};

    public Knight(boolean isWhite) {
        super("N", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "knight_" + colorSubstring() + ".png";
    }
}
