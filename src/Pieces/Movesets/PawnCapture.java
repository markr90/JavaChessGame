package Pieces.Movesets;

import Game.Board;
import Game.Coordinate;
import Game.Move;

public class PawnCapture implements MoveSet {
    @Override
    public boolean isValidMove(Board board, Move move) {
        boolean isWhite = board.getSpot(move.from()).getPiece().isWhite();

        if (!board.getSpot(move.to()).hasPiece()) {
            return false;
        }

        // only continue if target is opposite color
        if (board.getSpot(move.to()).hasPiece()) {
            if ( board.getSpot(move.to()).getPiece().isWhite() == isWhite) {
                return false;
            }
        }

        Coordinate t = move.to();
        Coordinate f = move.from();

        if (t.row() == 0 || t.row() == 7) {
            move.flagAsPawnPromotionMove();
        }

        if (isWhite) {
            return t.row() - f.row() == -1 && Math.abs(t.col() - f.col()) == 1;
        } else {
            return t.row() - f.row() == 1 && Math.abs(t.col() - f.col()) == 1;
        }
    }


}
