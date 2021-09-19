package Game;

public class MovePublisher {
    private MoveHandler moveHandler;

    public MovePublisher(MoveHandler moveHandler) {
        this.moveHandler = moveHandler;
    }

    public void sendMove(Move move) {
        moveHandler.handleMove(move);
    }
}
