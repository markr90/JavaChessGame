package com.company;

import Game.Game;
import Game.Player;
import Render.GameFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game(new Player(), new Player());
        GameFrame display = new GameFrame(game, 1000,1000);
        game.RegisterDisplay(display);
        game.StartGame();
    }
}
