package Game;

import GameDisplay.IDisplay;
import Pieces.IPiece;

import java.util.ArrayList;
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
    private String infoMessage = "";

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
        whitePlayer.setChecked(false);
        blackPlayer.setChecked(false);
        whitesMove = true;
        gameState = GameState.ACTIVE;
        display.Show();
    }

    public void endGame(boolean winner) {
        gameState = winner ? GameState.BLACK_WINS : GameState.WHITE_WINS;
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

    public String getInfoMessage() {
        return infoMessage;
    }

    public Board board() {
        return board;
    }

    public void handleMove(Move move) {
        if (gameState != GameState.ACTIVE) {
            return;
        }

        MoveValidationResult validMove = MoveValidator.validateMove(this, move);
        infoMessage = validMove.message();
        if (validMove.isValid()) {
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
        if (movesDone.empty() || gameState != GameState.ACTIVE) {
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
            return;
        }

        // if one of the valid moves is validated by the validator
        ArrayList<Move> moves = generateAllPossibleMovesForPlayer(whitesMove);
        for (Move move: moves) {
            if (MoveValidator.validateMove(this, move).isValid()) {
                // a single valid move was found that removes the check so player is not checkmate
                return;
            }
        }

        // cur player is checkMate

        endGame(whitesMove);
    }

    private ArrayList<Move> generateAllPossibleMovesForPlayer(boolean isWhite) {
        ArrayList<Spot> spotsOfPlayer = board.getSpotsWithPiecesOfPlayer(isWhite);
        ArrayList<Move> validMoves = new ArrayList<>();

        for (Spot spot: spotsOfPlayer) {
            validMoves.addAll(spot.getPiece().generateAllValidMoves(board));
        }

        return validMoves;
    }
}
