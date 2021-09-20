package Game;

import Pieces.IPiece;

public class HistoricMove extends Move {
    private String pieceMoved;
    private String pieceCaptured;

    public HistoricMove(Move move, IPiece pieceMoved, IPiece pieceCaptured) {
        super(move.from(), move.to());

        this.pieceMoved = pieceMoved.Symbol();
        this.pieceCaptured = pieceCaptured == null ? "" : pieceCaptured.Symbol();
    }

    public String algebraic() {
        String moveAlgebraic = "";

        if (!pieceMoved.equals("P")) {
            moveAlgebraic += pieceMoved + " ";
        }

        moveAlgebraic += new String(from().algebraic()).toLowerCase();

        if (!pieceCaptured.isEmpty()) {
            moveAlgebraic += "x";
        }

        moveAlgebraic += new String(to().algebraic()).toLowerCase();

        return moveAlgebraic;
    }
}
