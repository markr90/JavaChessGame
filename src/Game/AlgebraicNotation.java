package Game;

import Pieces.IPiece;

public class AlgebraicNotation {
    private String pieceMoved;
    private String pieceCaptured;
    private Coordinate from;
    private Coordinate to;


    public AlgebraicNotation(Move move, IPiece pieceMoved, IPiece pieceCaptured) {
        this.from = move.from();
        this.to = move.to();
        this.pieceMoved = pieceMoved.symbol();
        this.pieceCaptured = pieceCaptured == null ? "" : pieceCaptured.symbol();
    }

    @Override
    public String toString() {
        String moveAlgebraic = "";

        if (!pieceMoved.equals("P")) {
            moveAlgebraic += pieceMoved + " ";
        }

        moveAlgebraic += new String(from.algebraic()).toLowerCase();

        if (!pieceCaptured.isEmpty()) {
            moveAlgebraic += "x";
        }

        moveAlgebraic += new String(to.algebraic()).toLowerCase();

        return moveAlgebraic;
    }
}
