package Pieces;

import Game.Board;
import Game.Coordinate;
import Game.Move;

import javax.swing.*;
import java.util.ArrayList;

public interface IPiece {
    boolean isMoveLegal(Board board, Move move);
    ArrayList<Move> generateAllValidMoves(Board board);
    void incrementMoveCounter();
    void reduceMoveCounter();
    void setCurrentCoordinate(Coordinate coordinate);
    Coordinate getCurrentCoordinate();
    int numberOfTimesMoved();
    String symbol();
    ImageIcon icon();
    boolean isWhite();
}
