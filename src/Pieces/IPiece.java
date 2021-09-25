package Pieces;

import Game.Board;
import Game.Coordinate;
import Game.Move;

import javax.swing.*;

public interface IPiece {
    boolean isMoveLegal(Board board, Move move);
    void incrementMoveCounter();
    void reduceMoveCounter();
    void setCurrentCoordinate(Coordinate coordinate);
    Coordinate getCurrentCoordinate();
    int numberOfTimesMoved();
    String symbol();
    ImageIcon icon();
    boolean isWhite();
}
