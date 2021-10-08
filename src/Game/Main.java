package Game;

import Game.Game;
import Game.Player;
import GameDisplay.Display;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game(new Player(), new Player());
        Display display = new Display(game, 1000,600);
        game.registerDisplay(display);
        game.startGame();
    }
}
