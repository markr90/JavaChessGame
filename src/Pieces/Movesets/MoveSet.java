package Pieces.Movesets;

import Game.Move;
import Game.Board;
import Game.Coordinate;

import java.util.ArrayList;

public interface MoveSet {
    boolean isValidMove(Board board, Move move);
    ArrayList<Move> generateAllValidMoves(Board board, Coordinate coord, boolean isWhite);
}
