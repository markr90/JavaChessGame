package Game;

import GameDisplay.IDisplay;
import Pieces.IPiece;

public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private boolean whitesMove;
    private GameState gameState;
    private IDisplay display;
    private MoveHistory moveHistory;
    private GamePublisher gamePublisher;

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

    public void RegisterDisplay(IDisplay display) {
        this.display = display;
    }

    public void StartGame() throws Exception {
        board.Reset();
        whitesMove = true;
        gameState = GameState.ACTIVE;
        display.Show();
    }

    public MoveHistory getMoveHistory() {
        return moveHistory;
    }

    public Player CurPlayer() {
        return whitesMove ? whitePlayer : blackPlayer;
    }

    public GameState GameState() {
        return gameState;
    }

    public Board Board() {
        return board;
    }

    public void handleMove(Move move) {
        boolean validMove = MoveValidator.isMoveValid(this, move);
        if (validMove) {
            IPiece pieceMoved = this.board.getSpot(move.from()).getPiece();
            IPiece pieceCaptured = this.board.getSpot(move.to()).getPiece();
            moveHistory.addMove(new HistoricMove(move, pieceMoved, pieceCaptured));
            this.board.MovePieces(move);
            whitesMove = !whitesMove;
            gamePublisher.update(this);
        }
    }
}
