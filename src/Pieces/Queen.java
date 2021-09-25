package Pieces;

import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class Queen extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular(), new Diagonal()};

    public Queen(boolean isWhite) {
        super("K", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "queen_" + colorSubstring() + ".png";
    }
}
