package Game.Undo;

import Game.Spot;
import Pieces.IPiece;

public class RestoreSpot implements UndoAction {
    private Spot spot;
    private IPiece pieceToRestore;

    public RestoreSpot(Spot spot, IPiece pieceToRestore) {
        this.spot = spot;
        this.pieceToRestore = pieceToRestore;
    }

    @Override
    public void execute() {
        spot.setPiece(pieceToRestore);
        pieceToRestore.setCurrentCoordinate(spot.getCoordinate());
    }
}
