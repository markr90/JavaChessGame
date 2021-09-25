package Game;

import Pieces.IPiece;

import java.util.Set;

public class MoveValidator {

    public static boolean isMoveValid(Game game, Move move) {
        if (move.from() == move.to()) {
            return false;
        }

        if (!IsMoveInbounds(move)) {
            return false;
        }

        Spot currentSpot = game.board().getSpot(move.from());

        if (!currentSpot.hasPiece()) {
            return false;
        }

        if (currentSpot.getPiece().isWhite() != game.curPlayer().IsWhite()) {
            return false;
        }

        IPiece piece = currentSpot.getPiece();
        if (!piece.isMoveLegal(game.board(), move)) {
            return false;
        }

        if (willBeInCheck(game, move)) {
            return false;
        }

        if (move.isCastlingMove() && castlingMovePassesThroughCheck(game, move)) {
            return false;
        }

        return true;

    }

    private static boolean IsMoveInbounds(Move move) {
        return move.from().IsInbounds() && move.to().IsInbounds();
    }

    private static boolean willBeInCheck(Game game, Move move) {
        game.board().MovePieces(move);
        // Process the move
        // check if results in check
        // undo the move if check return false
        boolean isUnderAttack = false;
        Coordinate kingCoordinate = game.board().getKing(game.curPlayer().IsWhite()).getCurrentCoordinate();
        if (isUnderAttack(game, kingCoordinate)) {
            isUnderAttack = true;
        }
        game.board().undoMove(move);
        return isUnderAttack;
    }

    private static boolean castlingMovePassesThroughCheck(Game game, Move move) {
        boolean queenSide = move.to().col() == 0 || move.from().col() == 0;
        int row = move.to().row();

        if (queenSide) {
            return
                isUnderAttack(game, new Coordinate(row, 4))
                || isUnderAttack(game, new Coordinate(row, 3))
                || isUnderAttack(game, new Coordinate(row, 2));
        } else {
            return
                isUnderAttack(game, new Coordinate(row, 4))
                || isUnderAttack(game, new Coordinate(row, 5))
                || isUnderAttack(game, new Coordinate(row, 6));
        }
    }

    public static boolean isUnderAttack(Game game, Coordinate coord) {
        boolean color = game.curPlayer().IsWhite();

        IPiece potentialAttacker = findFirstPieceAbove(game, coord);
        if (isPerpAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceBelow(game, coord);
        if (isPerpAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceLeft(game, coord);
        if (isPerpAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceRight(game, coord);
        if (isPerpAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceLeftAbove(game, coord);
        if (isDiagAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceRightAbove(game, coord);
        if (isDiagAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceLeftBelow(game, coord);
        if (isDiagAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        potentialAttacker = findFirstPieceRightBelow(game, coord);
        if (isDiagAttacker(potentialAttacker, color, coord)) {
            return true;
        }

        if (hasHorseAttacker(game ,coord, color)){
            return true;
        }

        return false;
    }

    private static boolean isPerpAttacker(IPiece potentialAttacker, boolean color, Coordinate coord) {
        if (potentialAttacker == null) {
            return false;
        }

        int distance = Math.max(
                Math.abs(potentialAttacker.getCurrentCoordinate().col()-coord.col()),
                Math.abs(potentialAttacker.getCurrentCoordinate().row()-coord.row()));

        if (potentialAttacker.isWhite() != color) {
            if (distance == 1) {
                return Set.of("K", "R", "Q").contains(potentialAttacker.symbol());
            } else {
                return Set.of("R", "Q").contains(potentialAttacker.symbol());
            }
        }
        return false;
    }

    private static boolean isDiagAttacker(IPiece potentialAttacker, boolean color, Coordinate coord) {
        if (potentialAttacker == null) {
            return false;
        }

        int distance = Math.abs(potentialAttacker.getCurrentCoordinate().col()-coord.col());

        if (potentialAttacker.isWhite() != color) {
            if (distance == 1) {
                return Set.of("B", "Q", "P", "K").contains(potentialAttacker.symbol());
            } else {
                return Set.of("B", "Q").contains(potentialAttacker.symbol());
            }
        }
        return false;
    }

    private static IPiece findFirstPieceAbove(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int row = curRow - 1; row >= 0; row--) {
            nextSpot = game.board().getSpot(row, curCol);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceBelow(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int row = curRow + 1; row < 8; row++) {
            nextSpot = game.board().getSpot(row, curCol);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceLeft(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int col = curCol -1; col >= 0; col--) {
            nextSpot = game.board().getSpot(curRow, col);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceRight(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int col = curCol + 1; col < 8; col++) {
            nextSpot = game.board().getSpot(curRow, col);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceLeftAbove(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int i = 1; i <= Math.min(curRow, curCol); i++) {
            nextSpot = game.board().getSpot(curRow - i, curCol - i);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceRightAbove(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int i = 1; i <= Math.min(curRow, 7 - curCol); i++) {
            nextSpot = game.board().getSpot(curRow - i, curCol + i);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceLeftBelow(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int i = 1; i <= Math.min(7 - curRow, curCol); i++) {
            nextSpot = game.board().getSpot(curRow + i, curCol - i);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static IPiece findFirstPieceRightBelow(Game game, Coordinate coord) {
        int curRow = coord.row();
        int curCol = coord.col();

        Spot nextSpot;

        for (int i = 1; i <= Math.min(7 - curRow, 7 - curCol); i++) {
            nextSpot = game.board().getSpot(curRow + i, curCol + i);
            if (nextSpot.hasPiece()) {
                return nextSpot.getPiece();
            }
        }

        return null;
    }

    private static boolean hasHorseAttacker(Game game, Coordinate coord, boolean isWhite) {
        Coordinate[] potentialHorseCoords = new Coordinate[] {
                new Coordinate(coord.row() + 2, coord.col() + 1),
                new Coordinate(coord.row() - 2, coord.col() + 1),
                new Coordinate(coord.row() + 2, coord.col() - 1),
                new Coordinate(coord.row() - 2, coord.col() - 1),
                new Coordinate(coord.row() + 1, coord.col() + 2),
                new Coordinate(coord.row() - 1, coord.col() + 2),
                new Coordinate(coord.row() + 1, coord.col() - 2),
                new Coordinate(coord.row() - 1, coord.col() - 2)
        };

        for (Coordinate c: potentialHorseCoords) {
            if (c.IsInbounds()) {
                Spot spot = game.board().getSpot(c);
                if (spot.hasPiece() && spot.getPiece().isWhite() != isWhite && spot.getPiece().symbol() == "N") {
                    return true;
                }
            }
        }

        return false;
    }
}
