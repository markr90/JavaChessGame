package Game;

import java.util.Stack;

public class MoveHistory {
    public Stack<AlgebraicNotation> history;

    public MoveHistory() {
        history = new Stack<>();
    }

    public Stack<AlgebraicNotation> moves() {
        return history;
    }

    public AlgebraicNotation lastMove() {
        if (!history.isEmpty()) {
            return history.get(history.size() - 1);
        } else {
            return null;
        }
    }

    public void addMove(AlgebraicNotation move) {
        history.add(move);
    }

    public AlgebraicNotation pop() {
        return history.pop();
    }

    public void reset() {
        history = new Stack<>();
    }
}
