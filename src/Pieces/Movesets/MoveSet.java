package Pieces.Movesets;

import Game.Move;
import Game.Board;

public interface MoveSet {
    boolean isValidMove(Board board, Move move);
}
