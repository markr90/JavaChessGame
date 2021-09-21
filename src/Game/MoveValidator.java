package Game;

import Pieces.IPiece;

public class MoveValidator {

    public static boolean isMoveValid(Game game, Move move) {
        if (move.from() == move.to()) {
            return false;
        }

        if (!IsMoveInbounds(move)) {
            return false;
        }

        Spot currentSpot = game.Board().getSpot(move.from());

        if (!currentSpot.hasPiece()) {
            return false;
        }

        if (currentSpot.getPiece().isWhite() != game.CurPlayer().IsWhite()) {
            return false;
        }

        IPiece piece = currentSpot.getPiece();
        if (!piece.isMoveLegal(game.Board(), move)) {
            return false;
        }

        return true;

    }

    private static boolean IsMoveInbounds(Move move) {
        return move.from().IsInbounds() && move.to().IsInbounds();
    }
}
