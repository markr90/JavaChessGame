package Game;

import Render.IDisplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private Board board;
    private boolean whitesMove;
    private GameState gameState;
    private IDisplay display;

    public Game(Player white, Player black) throws Exception {
        whitePlayer = white;
        whitePlayer.SetWhite();
        blackPlayer = black;
        board = new Board();
    }

    public void RegisterDisplay(IDisplay display) {
        this.display = display;
    }

    public void StartGame() throws Exception {
        board.Reset();
        whitesMove = true;
        gameState = GameState.ACTIVE;
        display.Show();
        PlayGame();
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

    private void PlayGame() throws IOException {
        while (gameState == GameState.ACTIVE) {
            ProcessPlayerMove();
            whitesMove = !whitesMove;
            display.Update();
        }
    }

    private void ProcessPlayerMove() throws IOException {
        // Enter data using BufferReader
        Player curPlayer = whitesMove ? whitePlayer : blackPlayer;
        boolean validMove = false;

        while (!validMove) {
            System.out.print(curPlayer.Name() + " enter move: ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String playerInput = reader.readLine();
            if (playerInput.equals("quit")) {
                gameState = GameState.INACTIVE;
                return;
            }
            Move move = new Move(playerInput);
            validMove = board.AcceptMove(curPlayer, move);
        }
    }
}
