package Game;

import Pieces.PieceFactory;
import Pieces.Pieces;

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

    public void AcceptMove(Move move) {
        Spot currentSpot = this.getSpot(move.from());
        Spot newSpot = this.getSpot(move.to());

        newSpot.setPiece(currentSpot.getPiece());
        currentSpot.clearPiece();
    }

    public void Reset() throws Exception {
        currentState[7][0].setPiece(PieceFactory.createPiece(Pieces.Bishop,true));
        currentState[7][7].setPiece(PieceFactory.createPiece(Pieces.Bishop,true));
        for (int j = 0; j < 8; j++) {
            //currentState[6][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, true));
        }

        for (int j = 0; j < 8; j++) {
            //currentState[1][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, false));
        }
        currentState[0][0].setPiece(PieceFactory.createPiece(Pieces.Bishop,false));
        currentState[0][7].setPiece(PieceFactory.createPiece(Pieces.Bishop,false));
        currentState[2][2].setPiece(PieceFactory.createPiece(Pieces.King,true));
        currentState[4][4].setPiece(PieceFactory.createPiece(Pieces.King,false));
        currentState[2][4].setPiece(PieceFactory.createPiece(Pieces.Queen,true));
        currentState[4][2].setPiece(PieceFactory.createPiece(Pieces.Queen,false));
    }

    public Spot getSpot(Coordinate coordinate) {
        return currentState[coordinate.row()][coordinate.col()];
    }

    public Spot getSpot(int row, int col)  {
        return currentState[row][col];
    }
}
