package Game.Undo;

import Game.Spot;

public class ClearSpot implements UndoAction {
    private Spot spot;
    public ClearSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public void execute() {
        spot.clearPiece();
    }
}
