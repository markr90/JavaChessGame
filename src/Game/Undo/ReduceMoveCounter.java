package Game.Undo;

import Pieces.IPiece;

public class ReduceMoveCounter implements UndoAction {
    private IPiece piece;
    
    public ReduceMoveCounter(IPiece piece) {
        this.piece = piece;
    }

    public void execute() {
        piece.reduceMoveCounter();
    }
}
