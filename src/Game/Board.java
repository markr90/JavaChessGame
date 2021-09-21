package Game;

import GameDisplay.PawnPromotionListener;
import Pieces.PieceFactory;
import Pieces.Pieces;
import Pieces.IPiece;

import java.util.ArrayList;

public class Board {
    private Spot[][] currentState;
    private ArrayList<IPiece> piecesCaptured;
    private PawnPromotionListener pawnPromotionListener;

    public Board() throws Exception {
        currentState = new Spot[8][8];

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {
                currentState[row][col] = new Spot(row,col);
            }
        }
        this.Reset();
    }

    public void setPawnPromotionListener(PawnPromotionListener listener) {
        pawnPromotionListener = listener;
    }

    public void MovePieces(Move move) {
        Spot currentSpot = this.getSpot(move.from());
        Spot newSpot = this.getSpot(move.to());
        if (move.isCastlingMove()) {
            processCastlingMove(move);
        } else if (move.isPawnPromotionMove()) {
            boolean isWhite = currentSpot.getPiece().isWhite();
            newSpot.setPiece(currentSpot.getPiece());
            newSpot.getPiece().incrementMoveCounter();
            currentSpot.clearPiece();
            IPiece piece = pawnPromotionListener.promptPromotion(isWhite);
            newSpot.setPiece(piece);
        } else {
            if (newSpot.hasPiece()) {
                addPieceToCaptured(newSpot.getPiece());
            }
            newSpot.setPiece(currentSpot.getPiece());
            newSpot.getPiece().incrementMoveCounter();
            currentSpot.clearPiece();
        }
    }

    public ArrayList<IPiece> getPiecesCaptured() {
        return piecesCaptured;
    }

    private void addPieceToCaptured(IPiece piece) {
        piecesCaptured.add(piece);
    }

    public void Reset() throws Exception {
        piecesCaptured = new ArrayList<IPiece>();
//        currentState[7][0].setPiece(PieceFactory.createPiece(Pieces.Rook,true));
//        currentState[7][7].setPiece(PieceFactory.createPiece(Pieces.Rook,true));
//        currentState[7][1].setPiece(PieceFactory.createPiece(Pieces.Knight,true));
//        currentState[7][6].setPiece(PieceFactory.createPiece(Pieces.Knight,true));
//        currentState[7][2].setPiece(PieceFactory.createPiece(Pieces.Bishop,true));
//        currentState[7][5].setPiece(PieceFactory.createPiece(Pieces.Bishop,true));
//        currentState[7][3].setPiece(PieceFactory.createPiece(Pieces.Queen,true));
        currentState[7][4].setPiece(PieceFactory.createPiece(Pieces.King,true));
//        for (int j = 0; j < 8; j++) {
//            currentState[6][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, true));
//        }
//
//        for (int j = 0; j < 8; j++) {
//            currentState[1][j].setPiece(PieceFactory.createPiece(Pieces.Pawn, false));
//        }
//        currentState[0][0].setPiece(PieceFactory.createPiece(Pieces.Rook,false));
        currentState[0][7].setPiece(PieceFactory.createPiece(Pieces.Rook,false));
//        currentState[0][1].setPiece(PieceFactory.createPiece(Pieces.Knight,false));
//        currentState[0][6].setPiece(PieceFactory.createPiece(Pieces.Knight,false));
//        currentState[0][2].setPiece(PieceFactory.createPiece(Pieces.Bishop,false));
//        currentState[0][5].setPiece(PieceFactory.createPiece(Pieces.Bishop,false));
//        currentState[0][3].setPiece(PieceFactory.createPiece(Pieces.Queen,false));
//        currentState[0][4].setPiece(PieceFactory.createPiece(Pieces.King,false));

        currentState[1][0].setPiece(PieceFactory.createPiece(Pieces.Pawn, true));
    }

    public Spot getSpot(Coordinate coordinate) {
        return currentState[coordinate.row()][coordinate.col()];
    }

    public Spot getSpot(int row, int col)  {
        return currentState[row][col];
    }

    private void processCastlingMove(Move move) {
        Spot a = this.getSpot(move.from());
        Spot b = this.getSpot(move.to());

        IPiece king;
        IPiece rook;
        if (a.getPiece().symbol() == "K") {
            king = a.getPiece();
            rook = b.getPiece();
        } else {
            king = b.getPiece();
            rook = a.getPiece();
        }

        // queen side
        if (Math.abs(move.to().col() - move.from().col()) == 4) {
            if (king.isWhite()) {
                currentState[7][2].setPiece(king);
                currentState[7][3].setPiece(rook);
            } else {
                currentState[0][2].setPiece(king);
                currentState[0][3].setPiece(rook);
            }
        } else {
            if (king.isWhite()) {
                currentState[7][6].setPiece(king);
                currentState[7][5].setPiece(rook);
            } else {
                currentState[0][6].setPiece(king);
                currentState[0][5].setPiece(rook);
            }
        }

        a.setPiece(null);
        b.setPiece(null);
        king.incrementMoveCounter();
        rook.incrementMoveCounter();
    }
}
