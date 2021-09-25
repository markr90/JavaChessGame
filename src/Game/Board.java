package Game;

import Game.Undo.ClearSpot;
import Game.Undo.ReduceMoveCounter;
import Game.Undo.RestoreSpot;
import GameDisplay.PawnPromotionListener;
import Pieces.PieceFactory;
import Pieces.Pieces;
import Pieces.IPiece;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private Spot[][] currentState;
    private PawnPromotionListener pawnPromotionListener;
    private IPiece whiteKing;
    private IPiece blackKing;

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
        move.resetUndoInstructions();
        if (move.isCastlingMove()) {
            processCastlingMove(move);
        } else if (move.isPawnPromotionMove()) {
            processPawnPromotion(move);
        } else {
            processRegularMove(move);
        }
    }

    public ArrayList<Spot> getSpotsWithPiecesOfPlayer(boolean isWhite) {
        ArrayList<Spot> spots = new ArrayList<>();
        Spot spot;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spot = getSpot(i, j);
                if (spot.hasPiece() && spot.getPiece().isWhite() == isWhite) {
                    spots.add(spot);
                }
            }
        }

        return spots;
    }

    public void undoMove(Move move) {
        move.undo();
    }

    public IPiece getKing(boolean isWhite) {
        return isWhite ? whiteKing : blackKing;
    }

    public void Reset() throws Exception {
//        for (int j = 0; j < 8; j++) {
//            initPiece(6, j, Pieces.Pawn, true);
//        }
//        initPiece(7, 0, Pieces.Rook, true);
//        initPiece(7, 7, Pieces.Rook, true);
//        initPiece(7, 1, Pieces.Knight, true);
//        initPiece(7, 6, Pieces.Knight, true);
//        initPiece(7, 2, Pieces.Bishop, true);
//        initPiece(7, 5, Pieces.Bishop, true);
//        initPiece(7, 3, Pieces.Queen, true);
        initPiece(3, 4, Pieces.Queen, true);
        initPiece(4, 3, Pieces.Bishop, true);
        whiteKing = initPiece(7, 4, Pieces.King, true);


//        for (int j = 0; j < 8; j++) {
//            initPiece(1, j, Pieces.Pawn, false);
//        }
//        initPiece(0, 0, Pieces.Rook, false);
//        initPiece(0, 7, Pieces.Rook, false);
//        initPiece(0, 1, Pieces.Knight, false);
//        initPiece(0, 6, Pieces.Knight, false);
//        initPiece(0, 2, Pieces.Bishop, false);
//        initPiece(0, 5, Pieces.Bishop, false);
//        initPiece(0, 3, Pieces.Queen, false);
        blackKing = initPiece(0, 7, Pieces.King, false);
    }

    public Spot getSpot(Coordinate coordinate) {
        return currentState[coordinate.row()][coordinate.col()];
    }

    public Spot getSpot(int row, int col)  {
        return currentState[row][col];
    }

    private void processRegularMove(Move move) {
        Spot currentSpot = this.getSpot(move.from());
        Spot newSpot = this.getSpot(move.to());
        // build reverse move
        if (newSpot.hasPiece()) {
            move.addUndoInstruction(new RestoreSpot(newSpot, newSpot.getPiece()));
        } else {
            move.addUndoInstruction(new ClearSpot(newSpot));
        }
        move.addUndoInstruction(new RestoreSpot(currentSpot, currentSpot.getPiece()));
        move.addUndoInstruction(new ReduceMoveCounter(currentSpot.getPiece()));
        // execute move pieces
        newSpot.setPiece(currentSpot.getPiece());
        newSpot.getPiece().incrementMoveCounter();
        newSpot.getPiece().setCurrentCoordinate(newSpot.getCoordinate());
        currentSpot.clearPiece();
    }

    private void processPawnPromotion(Move move) {
        Spot currentSpot = this.getSpot(move.from());
        Spot newSpot = this.getSpot(move.to());
        move.addUndoInstruction(new RestoreSpot(currentSpot, currentSpot.getPiece()));
        move.addUndoInstruction(new ClearSpot(newSpot));

        boolean isWhite = currentSpot.getPiece().isWhite();
        newSpot.setPiece(currentSpot.getPiece());
        newSpot.getPiece().setCurrentCoordinate(null);
        currentSpot.clearPiece();
        IPiece piece = pawnPromotionListener.promptPromotion(isWhite);
        piece.setCurrentCoordinate(newSpot.getCoordinate());
        newSpot.setPiece(piece);

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

        int row = move.from().row();
        // queen side
        if (Math.abs(move.to().col() - move.from().col()) == 4) {
            move.addUndoInstruction(new ClearSpot(currentState[row][2]));
            move.addUndoInstruction(new ClearSpot(currentState[row][3]));
            currentState[row][2].setPiece(king);
            king.setCurrentCoordinate(new Coordinate(row, 2));
            currentState[row][3].setPiece(rook);
            rook.setCurrentCoordinate(new Coordinate(row, 3));
        } else {
            move.addUndoInstruction(new ClearSpot(currentState[row][5]));
            move.addUndoInstruction(new ClearSpot(currentState[row][6]));
            currentState[row][6].setPiece(king);
            king.setCurrentCoordinate(new Coordinate(row, 6));
            currentState[row][5].setPiece(rook);
            rook.setCurrentCoordinate(new Coordinate(row, 5));
        }

        move.addUndoInstruction(new RestoreSpot(a, a.getPiece()));
        move.addUndoInstruction(new ReduceMoveCounter(a.getPiece()));
        a.setPiece(null);
        move.addUndoInstruction(new RestoreSpot(b, b.getPiece()));
        move.addUndoInstruction(new ReduceMoveCounter(b.getPiece()));
        b.setPiece(null);
        king.incrementMoveCounter();
        rook.incrementMoveCounter();
    }

    private IPiece initPiece(int row, int col, Pieces pieceType, boolean isWhite) throws Exception {
        IPiece piece = PieceFactory.createPiece(pieceType, isWhite);
        getSpot(row, col).setPiece(piece);
        piece.setCurrentCoordinate(getSpot(row, col).getCoordinate());
        return piece;
    }
}
