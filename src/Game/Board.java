package Game;

import Pieces.PieceFactory;
import Pieces.Pieces;
import Pieces.IPiece;

public class Board {
    private Spot[][] currentState;

    public Board() throws Exception {
        currentState = new Spot[8][8];

        for (int i = 0; i < 8; i++) {

            for (int j= 0; j < 8; j++) {
                currentState[i][j] = new Spot(j,7-i);
            }
        }

        this.Reset();
    }

    public Spot[][] getCurrentState() {
        return currentState;
    }

    public boolean AcceptMove(Player player, Move move) {
        if (move.from() == move.to()) {
            return false;
        }

        if (!IsMoveInbounds(move)) {
            System.out.println("Invalid move out oof bounds");
            return false;
        }

        Spot currentSpot = this.getSpot(move.from());
        Spot newSpot = this.getSpot(move.to());

        if (!currentSpot.hasPiece()) {
            System.out.println("Invalid move has no piece");
            return false;
        }

        if (currentSpot.getPiece().IsWhite() && !player.IsWhite()) {
            System.out.println("Invalid move not same color");
            return false;
        }

        IPiece piece = currentSpot.getPiece();
        if (!piece.IsMoveLegal(this, move)) {
            return false;
        }

        newSpot.setPiece(currentSpot.getPiece());
        currentSpot.clearPiece();

        return true;
    }

    public void Reset() throws Exception {
        for (int j = 0; j < 8; j++) {
            currentState[6][j].setPiece(PieceFactory.createPiece(Pieces.Rook, true));
        }

        for (int j = 0; j < 8; j++) {
            currentState[1][j].setPiece(PieceFactory.createPiece(Pieces.Rook, false));
        }
    }

    public Spot getSpot(Coordinate coordinate) {
        return currentState[coordinate.Y()][coordinate.X()];
    }

    public Spot getSpot(int row, int col)  {
        return currentState[row][col];
    }

    private boolean IsMoveInbounds(Move move) {
        return move.from().IsInbounds() && move.to().IsInbounds();
    }
}
