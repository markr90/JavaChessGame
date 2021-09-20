package Game;

import java.util.ArrayList;

public class MoveHistory {
    public ArrayList<Move> moveHistory;

    public MoveHistory() {
        moveHistory = new ArrayList<>();
    }

    public ArrayList<Move> getMoveHistory() {
        return moveHistory;
    }

    public void addMove(Move move) {
        moveHistory.add(move);
    }

    public void reset() {
        moveHistory = new ArrayList<>();
    }
}
