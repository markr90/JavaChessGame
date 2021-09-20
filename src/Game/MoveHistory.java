package Game;

import java.util.ArrayList;

public class MoveHistory {
    public ArrayList<HistoricMove> moveHistory;

    public MoveHistory() {
        moveHistory = new ArrayList<>();
    }

    public ArrayList<HistoricMove> moves() {
        return moveHistory;
    }

    public HistoricMove lastMove() {
        if (!moveHistory.isEmpty()) {
            return moveHistory.get(moveHistory.size() - 1);
        } else {
            return null;
        }
    }

    public void addMove(HistoricMove move) {
        moveHistory.add(move);
    }

    public void reset() {
        moveHistory = new ArrayList<>();
    }
}
