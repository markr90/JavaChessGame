package Pieces;

public final class PieceFactory {
    private PieceFactory() {

    }

    public static IPiece createPiece(Pieces piece, boolean isWhite) throws Exception {
        switch (piece) {
            case Pawn:
                return new Pawn(isWhite);
            case Rook:
                return new Rook(isWhite);
            case Bishop:
                return new Bishop(isWhite);
            case King:
                return new King(isWhite);
            case Queen:
                return new Queen(isWhite);
            case Knight:
                return new Knight(isWhite);
            default:
                throw new Exception("Fatal error: Unknown piece");
        }
    }
}
