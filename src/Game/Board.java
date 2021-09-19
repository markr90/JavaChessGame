package Game;

import Pieces.PieceFactory;
import Pieces.Pieces;
import Pieces.IPiece;

public class Board {
    private Spot[][] currentState;

    public Board() throws Exception {
        currentState = new Spot[8][8];

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {
                currentState[row][col] = new Spot(row,col);
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

        if (currentSpot.getPiece().IsWhite() != player.IsWhite()) {
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
        currentState[7][0].setPiece(PieceFactory.createPiece(Pieces.Rook,true));
        currentState[7][7].setPiece(PieceFactory.createPiece(Pieces.Rook,true));
        for (int j = 0; j < 8; j++) {
            currentState[6][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, true));
        }

        for (int j = 0; j < 8; j++) {
            currentState[1][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, false));
        }
        currentState[0][0].setPiece(PieceFactory.createPiece(Pieces.Rook,false));
        currentState[0][7].setPiece(PieceFactory.createPiece(Pieces.Rook,false));
    }

    public Spot getSpot(Coordinate coordinate) {
        return currentState[coordinate.row()][coordinate.col()];
    }

    public Spot getSpot(int row, int col)  {
        return currentState[row][col];
    }

    private boolean IsMoveInbounds(Move move) {
        return move.from().IsInbounds() && move.to().IsInbounds();
    }
}
