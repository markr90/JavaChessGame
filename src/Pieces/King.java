package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.Castling;
import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class King extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular(1), new Diagonal(1), new Castling()};

    public King(boolean isWhite) {
        super("K", isWhite, moveSets);
    }

    @Override
    public String fileName() {
        return "king_" + colorSubstring() + ".png";
    }
}
