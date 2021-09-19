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
            default:
                throw new Exception("Fatal error: Unknown piece");
        }
    }
}
