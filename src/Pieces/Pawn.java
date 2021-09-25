package Pieces;

import Pieces.Movesets.MoveSet;
import Pieces.Movesets.PawnForward;
import Pieces.Movesets.PawnCapture;

public class Pawn extends Piece {
    private static MoveSet[] moveSets = {new PawnForward(), new PawnCapture()};

    public Pawn(boolean isWhite) {
        super("P", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "pawn_" + colorSubstring() + ".png";
    }
}
