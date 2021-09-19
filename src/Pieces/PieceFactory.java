package Pieces;

public final class PieceFactory {
    private PieceFactory() {

    }

    public static IPiece createPiece(Pieces piece, boolean isWhite) throws Exception {
        switch (piece) {
            case Rook:
                return new Pawn(isWhite);
            default:
                throw new Exception("Fatal error: Unknown piece");
        }
    }
}
