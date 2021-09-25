package Game;

import GameDisplay.IDisplay;
import Pieces.IPiece;

import java.util.Stack;

public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private boolean whitesMove;
    private GameState gameState;
    private IDisplay display;
    private MoveHistory moveHistory;
    private GamePublisher gamePublisher;
    private Stack<Move> movesDone = new Stack<>();

    public Game(Player white, Player black) throws Exception {
        whitePlayer = white;
        whitePlayer.SetWhite();
        blackPlayer = black;
        moveHistory = new MoveHistory();
        board = new Board();
        gamePublisher = new GamePublisher();
    }

    public GamePublisher getGamePublisher() {
        return gamePublisher;
    }

    public void registerDisplay(IDisplay display) {
        this.display = display;
    }

    public void startGame() throws Exception {
        board.Reset();
        whitesMove = true;
        gameState = GameState.ACTIVE;
        display.Show();
    }

    public MoveHistory getMoveHistory() {
        return moveHistory;
    }

    public Player curPlayer() {
        return whitesMove ? whitePlayer : blackPlayer;
    }

    public GameState gameState() {
        return gameState;
    }

    public Board board() {
        return board;
    }

    public void handleMove(Move move) {
        boolean validMove = MoveValidator.isMoveValid(this, move);
        if (validMove) {
            IPiece pieceMoved = this.board.getSpot(move.from()).getPiece();
            IPiece pieceCaptured = this.board.getSpot(move.to()).getPiece();
            moveHistory.addMove(new AlgebraicNotation(move, pieceMoved, pieceCaptured));
            this.board.MovePieces(move);
            whitesMove = !whitesMove;
            doCheckCheck();
            doCheckMateCheck();
            movesDone.add(move);
        }
        gamePublisher.update(this);
    }

    public void undoLastMove() {
        if (movesDone.empty()) {
            return;
        }
        Move lastMoveDone = movesDone.pop();
        board.undoMove(lastMoveDone);
        whitesMove = !whitesMove;
        moveHistory.pop();
        gamePublisher.update(this);
    }

    private void doCheckCheck() {
        boolean isChecked = MoveValidator.isUnderAttack(this, this.board.getKing(whitesMove).getCurrentCoordinate());
        curPlayer().setChecked(isChecked);
    }

    private void doCheckMateCheck() {
        if (!curPlayer().isChecked()) {
            System.out.println("Check mate!");
            // Finish game here
        }
    }
}
